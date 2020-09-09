# 爬取迁木网QS世界大学排行榜中，各学校页面内详细信息表格，使用分布式
import time

import requests
import lxml.etree
import threading
from queue import Queue
import redis
import signal

# 用于多线程的队列
link_queue = Queue()
# 线程池
threads = []
thread_num = 10
thread_on = True
r = redis.Redis()


# 处理数据
def save_data(data):
    if data:
        print(data)


# 解析详情页
def parse_university(link):
    # 请求到详情页后，分析具体详情页
    r = requests.get(link)
    selector = lxml.etree.HTML(r.text)
    data = {'校名': selector.xpath('//*[@id="wikiContent"]/h1/text()')[0]}

    # 按列读，防止一行多个数据
    k_cols = selector.xpath('//*[@id="wikiContent"]/div[1]/table/tbody/tr/td[1]')
    keys = [' '.join(col.xpath('.//text()')) for col in k_cols]
    v_cols = selector.xpath('//*[@id="wikiContent"]/div[1]/table/tbody/tr/td[2]')
    values = [''.join(col.xpath('.//text()')) for col in v_cols]

    # 把列表信息加入到data字典中
    data.update(zip(keys, values))
    return data


def deal_with_thread(i):
    while thread_on:
        # 阻塞直到从队列里获取一条消息
        # link = link_queue.get()
        link = r.lpop('qianmu.queue')

        if link:
            data = parse_university(link)
            save_data(data)
            print('剩余队列：%s' % r.llen('qianmu.queue'))
        # 防止请求太快
        time.sleep(0.2)
    print('线程%s退出' % i)


def sigint_handle(signnum, frame):
    print('接收到Ctrl+C，等待退出')
    # 停止线程循环
    global thread_on
    thread_on = False


if __name__ == '__main__':
    # 请求并下载页面（入口）
    response = requests.get('http://www.qianmu.org/ranking/1528.htm')
    selector = lxml.etree.HTML(response.text)
    # 提取列表链接
    links = selector.xpath(
        '//*[@id="page-wrapper"]/div/div[2]/div/div/div/div[2]/div/div[5]/table/tbody/tr/td[2]/a/@href')

    for link in links:
        # 创建多线程队列
        # link_queue.put(link)

        # 使用redis实现分布式，考虑是否已爬过和去重，使用set
        if r.sadd('qianmu.seen', link):
            r.rpush('qianmu.queue', link)

    # 创建线程池实现多线程
    for i in range(thread_num):
        # 启动线程并保存到列表
        t = threading.Thread(target=deal_with_thread, args=(i+1,))
        t.start()
        threads.append(t)

    signal.signal(signal.SIGINT, sigint_handle)
    # 阻塞队列直到队列被清空
    link_queue.join()
    # 退出线程
    for t in threads:
        t.join()
    print('完成')
