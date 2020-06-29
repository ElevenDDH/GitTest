from mystring import set, get, exist, incr, save
from mylist import lpush, lrange, rpop, save
from myset import sadd, smembers, save
from myhash import hget, hset, save
import mystring, mylist, myset, myhash


def handle(command):
	command = str(command).replace(" ", "")

	if 'hget' in command:
		key = command[command.find('(')+1:command.find(',')]
		field = command[command.find(',')+1:command.rfind(')')]
		return hget(key, field)
	elif 'hset' in command:
		key = command[command.find('(')+1:command.find(',')]
		field = command[command.find(',')+1:command.rfind(',')]
		value = command[command.rfind(',')+1:command.rfind(')')]
		return hset(key, field, value)
	elif 'set' in command:
		key = command[command.find('(')+1:command.find(',')]
		value = command[command.find(',')+1:command.rfind(')')]
		return set(key, value)
	elif 'get' in command:
		key = command[command.find('(')+1:command.rfind(')')]
		return get(key)
	elif 'exist' in command:
		key = command[command.find('(')+1:command.rfind(')')]
		return exist(key)
	elif 'incr' in command:
		key = command[command.find('(')+1:command.rfind(')')]
		return incr(key)
	elif 'lpush' in command:
		key = command[command.find('(')+1:command.find(',')]
		value = command[command.find(',')+1:command.rfind(')')]
		return lpush(key, value)
	elif 'lrange' in command:
		key = command[command.find('(')+1:command.find(',')]
		start = command[command.find(',')+1:command.rfind(',')]
		stop = command[command.rfind(',')+1:command.rfind(')')]
		return lrange(key, start, stop)
	elif 'rpop' in command:
		key = command[command.find('(')+1:command.rfind(')')]
		return rpop(key)
	elif 'sadd' in command:
		key = command[command.find('(')+1:command.find(',')]
		member = command[command.find(',')+1:command.rfind(')')]
		return sadd(key, member)
	elif 'smembers' in command:
		key = command[command.find('(')+1:command.rfind(')')]
		return smembers(key)
	elif 'save' in command:
		key = command[command.find('(')+1:command.rfind(')')]
		mystring.save(key)
		mylist.save(key)
		myset.save(key)
		myhash.save(key)
		return 'OK'
	return "其他功能还在开发中，请耐心等待"

