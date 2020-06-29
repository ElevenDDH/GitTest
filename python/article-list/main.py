# -*- coding: utf-8 -*-

from bottle import ( 
	route, 
	run, 
	template, 
	view, 
	request, 
	post, 
	get,
	redirect,
	abort
)

import redis, random
import datetime, time
import json
import func, mail, mail_deal


r = redis.StrictRedis()

@route('/')
@view('templates/test')
def main():
	# 判断是否按访问量排序，0否
	order_by = request.query.order or '0'
	# 当前页数
	page = int(request.query.page or '1')
	# 总页数
	pages = 5

	start = (page - 1) * pages
	end = page * pages - 1
	if order_by == '0':
		pages_id_bytes = r.lrange('posts:list', start, end)
	else:
		pages_id_bytes = r.zrevrange('posts:visited', start, end)
	pages_id = func.bytes_to_string_list(pages_id_bytes)

	post_list = {}
	visit_time = {}
	for id in pages_id:
		# 根据每个页数获取的数据
		d = r.hgetall("{}".format(id))
		data = func.bytes_to_string_dict(d)
		# 字典无序
		post_list[id] = data
		visit_time[id] = r.zscore('posts:visited', id)
	# 排序
	new_post_list = sorted(post_list.items(), reverse=True)

	
	return dict(posts=new_post_list, page=page, visit_time=visit_time)

@route('/send')
@view('templates/send')
def send():
	return dict()

@post('/result')
@view('templates/send')
def deal():
	article_id = r.hincrby("myarticle", 'id', 1)
	title = request.forms.getunicode("title")
	articles = request.forms.getunicode("articles")
	slug = request.forms.getunicode("slug")
	name = request.forms.getunicode("name")
	submit_time = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

	# 建立slug和post_id的对应关系
	if not r.hset('slug.to.id', slug, article_id):
		return dict(tips='slug already exists')

	r.hmset("{}".format(article_id), {'title':title, 
	'articles':articles, 'name':name, 'time':submit_time, 'slug':slug
	})
	# 文章列表分页
	r.lpush('posts:list', article_id)

	subscribe_users = func.bytes_to_string_list(r.smembers('subscribe'))
	url = 'http://192.168.1.130:1213/{}'.format(slug)
	for user in subscribe_users:
		# mail.send_email(user, url, 'post')
		email_info = {
			'email':user,
			'url':url,
			'tpl':'post'
		}
		r.lpush('queue:notify.email', json.dumps(email_info))

	# 重定向到对应id的文章
	redirect('/{}'.format(slug))

@route('/<slug>')
@view('templates/result')
def result(slug):
	id = r.hget('slug.to.id', slug)
	if not id:
		abort(404, '文章不存在')
	id = id.decode('utf-8')

	# visit_time = r.hincrby("myarticle", 'p{}'.format(id), 1)
	visit_time = int(r.zincrby('posts:visited', 1, id))

	title = r.hget("{}".format(id), 'title')
	articles = r.hget("{}".format(id), 'articles')
	name = r.hget("{}".format(id), 'name')
	time = r.hget("{}".format(id), 'time')

	tags = func.bytes_to_string_list(r.smembers('post:{}:tags'.format(id)))

	return dict(title=title, articles=articles, name=name
		, time=time, visit_time=visit_time, id=id, tags=tags)

@post('/tags/add')
def add_tag():
	pid = request.forms.getunicode("p_id")
	tag = request.forms.getunicode('tag')

	r.sadd('post:{}:tags'.format(pid), tag)
	r.sadd('tag:{}:posts'.format(tag), pid)

	# 用list方便遍历
	tags = func.bytes_to_string_list(r.smembers('post:{}:tags'.format(pid)))
	# 返json
	return {'tags':tags}

@route('/tag/list/<tag>')
@view('templates/showtag')
def tag_list(tag):
	pid = func.bytes_to_string_list(r.smembers('tag:{}:posts'.format(tag)))

	post_list = {}
	for id in pid:
		d = r.hgetall("{}".format(id))
		data = func.bytes_to_string_dict(d)
		post_list[id] = data
	# 排序
	new_post_list = sorted(post_list.items(), reverse=True)
	return dict(posts=new_post_list, tag=tag)

@post('/pubsub')
@view('active')
def pubsub():
	email = request.forms.getunicode('email').strip()
	# 判断邮件是否重复
	# ...
	

	samples = "qwertyuioplkjhgfdsazxcvbnm0123456789"
	postfix = ''.join([random.choice(samples) for i in range(10)])
	url = 'http://192.168.1.130:1213/active/{}'.format(postfix)

	# send email
	# mail.send_email(email, url,'subscribe')
	
	email_info = {
		'email':email,
		'url':url,
		'tpl':'subscribe'
	}
	r.lpush('queue:cofirm.email', json.dumps(email_info))

	pipe = r.pipeline()
	pipe.set(postfix, email)
	pipe.expire(postfix, 7200)
	pipe.execute()

	return dict(email = email)

@get('/active/<postfix>')
@view('subscribe')
def subscribe(postfix):
	if not r.exists(postfix):
		return dict(result = '激活链接不存在或已过期，请重新订阅')
	email = r.get(postfix).decode('utf-8')
	r.sadd('subscribe', email)
	return dict(result='用户订阅地址：{}激活成功，感谢订阅'.format(email))

# 多线程执行
back_mail = mail_deal.MailSend()
back_mail.start()

run(host='0.0.0.0', port=1213, reloader=True)
