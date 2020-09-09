# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy import Field


# 保存视频信息的item
class PostItem(scrapy.Item):
    # 多张表，所以表明不能写死，自定义字段
    table_name = 'posts'

    # 数据字段
    pid = Field()
    title = Field()
    thumbnail = Field()
    preview_picture = Field()
    video = Field()
    video_format = Field()
    duration = Field()
    category = Field()
    updated_time = Field()
    play_counts = Field()
    like_counts = Field()
    description = Field()


class ComposerItem(scrapy.Item):
    table_name = 'composers'

    cid = Field()
    bg_img = Field()
    avatar = Field()
    verified = Field()
    name = Field()
    intro = Field()
    like_counts = Field()
    fans_counts = Field()
    follow_counts = Field()
    location = Field()
    career = Field()


class CopyrightItem(scrapy.Item):
    table_name = 'copyrights'

    pcid = Field()
    pid = Field()
    cid = Field()
    roles = Field()


class CommentItem(scrapy.Item):
    table_name = 'comments'

    userid = Field()
    name = Field()
    avatar = Field()
    commentid = Field()
    content = Field()
    addtime = Field()
    count_approve = Field()
