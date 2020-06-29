data = {}

def lpush(key, value):
	# 由于list要在方法中创建，所以借用字典保存到全局
	if key not in data:
		data[key] = []
	data[key].insert(0, value)
	return str(len(data[key]))

def lrange(key, start, stop):
	if int(stop) >= 0:
		return str(data[key][int(start):int(stop) + 1])
	return str(data[key][int(start):int(stop)])

def rpop(key):
	return str(data[key].pop())

def save(key):
	try:
		f = open('./data/data.txt', 'a+')
		f.write(key+':'+str(data[key])+"\n")
	except Exception as e:
		return None
