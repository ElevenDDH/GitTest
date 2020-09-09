# 爬取迁木网QS世界大学排行榜中，各学校页面内详细信息表格，使用多线程

import requests
import lxml.etree
import threading
from queue import Queue

# 用于多线程的队列
link_queue = Queue()
# 线程池
threads = []
thread_num = 10


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


def deal_with_thread():
    while True:
        # 阻塞直到从队列里获取一条消息
        link = link_queue.get()
        if link is None:
            break
        data = parse_university(link)
        save_data(data)
        # 删除队列中取出来的url
        link_queue.task_done()
        print('剩余队列：%s' % link_queue.qsize())


if __name__ == '__main__':
    # 请求并下载页面（入口）
    response = requests.get('http://www.qianmu.org/ranking/1528.htm')
    selector = lxml.etree.HTML(response.text)
    # 提取列表链接
    links = selector.xpath(
        '//*[@id="page-wrapper"]/div/div[2]/div/div/div/div[2]/div/div[5]/table/tbody/tr/td[2]/a/@href')

    for link in links:
        # 创建多线程队列
        link_queue.put(link)

    # 创建线程池实现多线程
    for i in range(thread_num):
        # 启动线程并保存到列表
        t = threading.Thread(target=deal_with_thread)
        t.start()
        threads.append(t)
    # 阻塞队列直到队列被清空
    link_queue.join()
    # 向队列发送N个None，以通知线程退出
    for i in range(thread_num):
        link_queue.put(None)
    # 退出线程
    for t in threads:
        t.join()
    print('完成')
