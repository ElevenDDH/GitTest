from bottle import run, get, request
import redis

r = redis.StrictRedis()

# 设置装饰器
def limit_rate(func):
	def api(*args, **kwagrs):
		ip = request.remote_addr
		# 不同接口不能用同一个key
		name = func.__name__
		key = 'limit:rate:{}：{}'.format(ip, name)
		if r.exists(key):
			rate = r.incr(key)
			if rate > 10:
				return "IP:{}，访问频率过高".format(ip)
			# 返回被修饰函数
			return func(*args, **kwagrs)

		pipe = r.pipeline()
		pipe.incr(key)
		pipe.expire(key, 60)
		pipe.execute()
		return func(*args, **kwagrs)
	return api

@get('/')
@limit_rate
def index():
	return "hello redis"


run(host = '0.0.0.0', port = 1212, reloader = True)
