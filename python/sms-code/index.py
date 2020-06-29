import bottle, redis, random


r = redis.StrictRedis()

@bottle.get('/')
@bottle.view('view/signup')
def main():
	return dict()

@bottle.post('/getcode')
def getcode():
	mobile = bottle.request.forms['mobile']
	code = random.randint(100000, 999999)

	key = 'sms:{}'.format(mobile)
	pipe = r.pipeline()
	pipe.set(key, code)
	pipe.expire(key, 180)
	pipe.execute()

	return "success"

@bottle.post('/signup')
def signup():
	mobile = bottle.request.forms['mobile']
	code = bottle.request.forms['code']
	key = 'sms:{}'.format(mobile)

	if r.exists(key):
		sms_code = r.get(key).decode('utf-8')
		if sms_code == code:
			r.delete(key)
			return "注册成功"
		return "验证码不一致"
	return '验证码以过期'


bottle.run(host = '0.0.0.0', port = 1212, reloader = True)
