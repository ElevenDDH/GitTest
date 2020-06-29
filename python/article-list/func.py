def bytes_to_string_dict(d):
	new_dict = {k.decode('utf-8'): v.decode('utf-8') for k, v in d.items()}
	return new_dict

def bytes_to_string_list(l):
	new_list = [item.decode("utf-8") for item in l]
	return new_list

def bytes_to_string_set(set):
	new_set = {item.decode("utf-8") for item in set}
	return new_set
