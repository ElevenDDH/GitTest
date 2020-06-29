data = {}

def hset(key, field, value):
	if key not in data:
		data[key] = {field:value}
		return str(1)
	if field in data[key]:
		data[key][field] = value
		return str(0)
	data[key][field] = value
	return str(1)

def hget(key, field):
	if field in data[key]:
		return data[key][field]
	return '(nil)'

def save(key):
	try:
		f = open('./data/data.txt', 'a+')
		f.write(key+':'+str(data[key])+"\n")
	except Exception as e:
		return None

