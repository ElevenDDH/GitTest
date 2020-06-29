from bottle import run, get, request
import redis
from luaScript import script

r = redis.StrictRedis()

@get('/')
def index():
	key = 'rate:limit'
	ratelimit = r.register_script(script)
	if ratelimit(keys=[key], args=[60, 10]):
		return "hello redis"
	else:
		return '访问频率超出限制'


run(host = '0.0.0.0', port = 1212, reloader = True)
