# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter
import pymysql


class MysqlPipeline:
    # 开始爬虫调用
    def open_spider(self, spider):
        self.conn = pymysql.connect(
            host='127.0.0.1',
            port=33068,
            db='xpc',
            user='root',
            password='123456',
            charset='utf8mb4'
        )
        # 获取游标
        self.cur = self.conn.cursor()

    # 结束爬虫调用
    def close_spider(self, spider):
        pass

    # 每产生一个item调用一次
    def process_item(self, item, spider):
        # 执行数据库插入操作
        # keys = item.keys()
        # values = list(item.values())

        # *解封，把list里面每一个参数传出
        keys, values = zip(*item.items())

        # 更改sql语句为有key就更新
        sql = "insert into `{}` ({}) values ({})" \
            "ON DUPLICATE KEY UPDATE {}".format(
            # 数据库名
            item.table_name,
            ','.join(keys),
            ','.join(['%s'] * len(values)),
            ','.join(['`{}`=%s'.format(k) for k in keys])
        )
        self.cur.execute(sql, values * 2)
        self.conn.commit()
        # 打印上一次执行的sql语句
        print(self.cur._last_executed)
        return item
