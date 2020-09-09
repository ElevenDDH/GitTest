import json
import random
import string

import scrapy
import re

from scrapy import Request
from XPC.items import *

# 解决20页以后需要登陆问题
cookies = dict(
    Authorization='A37AB29030DC8844A30DC843CB30DC8B2D630DC861E78138BA33'
)


# 此方法防止取到的信息有些不存在或为空，导致strip报错
def my_strip(s):
    if s:
        return s.strip()
    return ''


# 随机生成session id
def gen_sessionid():
    return ''.join(random.choices(string.ascii_lowercase + string.digits, key=26))


def convert_int(s):
    if isinstance(s, str):
        # 把数据中的','去掉
        return int(s.replace(',', ''))
    return 0


class DiscoverySpider(scrapy.Spider):
    name = 'discovery'
    # 请求额外的域名需要在此添加
    allowed_domains = ['xinpianchang.com', 'openapi-vtom.vmovier.com']
    start_urls = ['https://www.xinpianchang.com/channel/index/sort-like?from=navigator']
    page_count = 0

    # # 防止假如开头就需要登录，并根据情况需要添加其他字段
    # # 要使用需要全手动
    # def start_requests(self):
    #     for url in self.start_urls:
    #         # 复制cookies以添加跳转需要的验证
    #         c = cookies.copy()
    #         # channel_page控制页面一次性跳转指定范围页面
    #         c.update(PHPSESSID=gen_sessionid(),
    #                  SERVER_ID='b52601c8-0ff66ef8',
    #                  # channel_page='apU%3D'
    #                  )
    #         # dont_filter不参与去重过滤
    #         request = Request(url, cookies=c, dont_filter=True)
    #         # 不合并cookies
    #         # request.meta['dont_merge_cookies'] = True
    #         yield request

    def parse(self, response):
        # 解决规定时间内100页后封session
        self.page_count += 1
        if self.page_count >= 100:
            cookies.update(PHPSESSID=gen_sessionid())
            self.page_count = 0

        # 分析得到id在url跳转，获取id构造url
        post_list = response \
            .xpath('//ul[@class="video-list"]/li')
        url = 'https://www.xinpianchang.com/a%s?from=ArticleList'
        for post in post_list:
            pid = post.xpath('./@data-articleid').get()
            request = response.follow(url % pid, self.parse_post)
            request.meta['pid'] = pid
            request.meta['thumbnail'] = post.xpath('./a/img/@_src').get()
            yield request

        # 翻页，自动过滤重复链接
        # ？？？？getall() == extract()？？？？
        pages = response.xpath('//div[@class="page"]/a/@href').getall()
        for page in pages:
            yield response.follow(page, self.parse, cookies=cookies)

    # 处理视屏页面的相关信息
    def parse_post(self, response):
        pid = response.meta['pid']
        # post = {'pid': pid,
        #         'title': response \
        #             .xpath('//div[@class="title-wrap"]/h3/text()').get(),
        #         'thumbnail': response.meta['thumbnail']}

        post = PostItem()
        post['pid'] = pid
        post['title'] = response \
                    .xpath('//div[@class="title-wrap"]/h3/text()').get()
        post['thumbnail'] = response.meta['thumbnail']
        # 有动态加载，点播放后网页中的此链接无法产生
        # post['video'] = response.xpath('//video[@id="xpc_video"]/@src').get()

        category = response.xpath('//span[contains(@class, "cate")]//text()').extract()
        # 去空格换行并连接list列表所有文字信息
        post['category'] = ''.join([cate.strip() for cate in category])
        post['updated_time'] = response.xpath('//span[contains(@class, "updata-time")]/i/text()').get()
        post['play_counts'] = convert_int(response.xpath('//i[contains(@class, "play-counts")]/@data-curplaycounts').get())
        post['like_counts'] = convert_int(response.xpath('//span[contains(@class, "like-counts")]/@data-counts').get())
        post['description'] = my_strip(response.xpath('//p[contains(@class, "desc")]/text()').get())
        # post[''] = response.xpath('').get()

        # 处理动态加载
        # 分析ajax（XHR）异步请求，找到产生链接的请求url
        vid, = re.findall('var vid = \"(\w+)\"\;', response.text)
        video_url = 'https://openapi-vtom.vmovier.com/v3/video/%s?expand=resource&usage=xpc_web&appKey=61a2f329348b3bf77'
        request = Request(video_url % vid, callback=self.parse_video)
        request.meta['post'] = post
        yield request

        # 处理评论
        comment_url = 'https://app.xinpianchang.com/comments?resource_id=%s&type=article&page=1&per_page=24'
        request = Request(comment_url % pid, callback=self.parse_comment)
        request.meta['pid'] = pid
        yield request

        # 处理创作人
        creator_list = response.xpath('//div[@class="user-team"]//ul[@class="creator-list"]/li')
        composer_url = 'https://www.xinpianchang.com/u%s?from=articleList'
        cid_list = creator_list.xpath('./a/@data-userid').get()

        for creator in creator_list:
            # print('===========================')
            # print(creator)
            cid = creator.xpath('./a/@data-userid').get()
            request = response.follow(composer_url % cid, self.parse_creator)
            request.meta['cid'] = cid
            request.meta['dont_merge_cookies'] = True
            yield request

            # cr = {
            #     'pid': pid,
            #     'cid': cid,
            #     'roles': creator.xpath('./div[@class="creator-info"]/span/text()').get()
            # }
            cr = CopyrightItem()
            cr['pcid'] = '%s_%s' % (pid, cid)
            cr['pid'] = pid
            cr['cid'] = cid
            cr['roles'] = creator.xpath('./div[@class="creator-info"]/span/text()').get()
            yield cr

    def parse_video(self, response):
        post = response.meta['post']
        # 把response转json好提取里面包含的信息链接
        result = json.loads(response.text)
        data = result['data']
        # 在此找到了视频链接，即可继续实现下载
        if 'resource' in data:
            post['video'] = data['resource']['progressive'][1]['url']
        else:
            # post['video'] = data['third']['data']['iframe_url']
            d = data['third']['data']
            post['video'] = d.get('iframe_url', d.get('swf', ''))
        post['preview_picture'] = result['data']['video']['cover']
        post['duration'] = result['data']['video']['duration']
        yield post

    def parse_comment(self, response):
        results = json.loads(response.text)
        next_page = results['data']['next_page_url']

        results = results['data']['list']
        for i in range(len(results)):
            # comment = {'userid': results[i]['userInfo']['id'],
            #            'name': results[i]['userInfo']['username'],
            #            'avatar': results[i]['userInfo']['avatar'],
            #            # 'location': results[i]['userInfo']['location'],
            #            'commentid': results[i]['id'],
            #            'content': results[i]['content'],
            #            'addtime': results[i]['addtime'],
            #            'count_approve': results[i]['count_approve']}
            comment = CommentItem()
            comment['userid'] = results[i]['userInfo']['id']
            comment['name'] = results[i]['userInfo']['username']
            comment['avatar'] = results[i]['userInfo']['avatar']
            comment['commentid'] = results[i]['id']
            comment['content'] = results[i]['content']
            comment['addtime'] = results[i]['addtime']
            comment['count_approve'] = results[i]['count_approve']
            yield comment

        if next_page:
            yield response.follow('https://app.xinpianchang.com' + next_page,
                                  self.parse_comment)

    def parse_creator(self, response):
        bg_img = response.xpath('//div[@class="banner-wrap"]/@style').get()
        # creator = {
        #     'bg_img': re.findall('background-image:url\((.+?)\)', bg_img),
        #     'name': response.xpath('//p[contains(@class, "creator-name")]/text()').get(),
        #     'intro': response.xpath('//p[contains(@class, "creator-desc")]/text()').get(),
        #     'like_counts': convert_int(response.xpath('//span[contains(@class, "like-counts")]/text()').get()),
        #     'fans_counts': response.xpath('//span[contains(@class, "")]/text()').get(),
        #     'follow_counts': response.xpath('//span[@class=""]').get(),
        #     'location': response.xpath('//span[contains(@class, "icon-location")]/following-sibling::span[1]/text()').get(),
        #     'career': response.xpath('//span[contains(@class, "icon-career")]/following-sibling::span[1]/text()').get()
        # }
        creator = ComposerItem()
        creator['cid'] = response.meta['cid']
        creator['bg_img'] = re.findall('background-image:url\((.+?)\)', bg_img)
        creator['avatar'] = response.xpath('//span[@class="avator-wrap-s"]/img/@src').get()
        creator['name'] = response.xpath('//p[contains(@class, "creator-name")]/text()').get()
        creator['intro'] = response.xpath('//p[contains(@class, "creator-desc")]/text()').get()
        creator['like_counts'] = convert_int(response.xpath('//span[contains(@class, "like-counts")]/text()').get())
        creator['fans_counts'] = convert_int(response.xpath('//span[contains(@class, "fans-counts")]/text()').get())
        creator['follow_counts'] = convert_int(response.xpath('//span[@class="follow-wrap"]/span[2]/text()').get())
        creator['location'] = response.xpath('//span[contains(@class, "icon-location")]/following-sibling::span[1]/text()').get() or ''
        creator['career'] = response.xpath('//span[contains(@class, "icon-career")]/following-sibling::span[1]/text()').get() or ''

        yield creator



