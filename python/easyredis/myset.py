data = {}

def sadd(key, member):
	if key not in data:
		data[key]  = set()
	if member in data[key]:
		return str(0)
	data[key].add(member)
	return str(1)

def smembers(key):
	# key = set(("Google", "Runoob", "Taobao"))
	result =''
	num = 1
	for k in data[key]:
		result= result + (str(num)+ ' ' + k + '\n')
		num = num + 1
	return result

def save(key):
	try:
		f = open('./data/data.txt', 'a+')
		f.write(key+':'+str(data[key])+"\n")
	except Exception as e:
		return None

