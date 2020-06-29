dict = {}

def set(key, value):
	dict[key] = value
	return "ok"

def get(key):
	return str(dict.get(key, "nil"))

def exist(key):
	try:
		if dict[key]:
			return str(1)
	except Exception as e:
		return str(0)

def incr(key):
	try:
		if dict[key]:
			dict[key] = int(dict[key]) + 1
		return str(dict[key])
	except Exception as e:
		dict[key] = 1
		return str(dict[key])

def save(key):
	try:
		f = open('./data/data.txt', 'a+')
		f.write(key+':'+str(dict[key])+"\n")
	except Exception as e:
		return None

