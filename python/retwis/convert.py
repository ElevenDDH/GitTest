def to_string(bytes_data: bytes) -> str:
	return bytes_data.decode('utf-8')

def to_dict(d: dict):
	new_dict = {k.decode('utf-8'): v.decode('utf-8') for k, v in d.items()}
	return new_dict

def to_list(l):
	new_list = [item.decode("utf-8") for item in l]
	return new_list

def to_set(set):
	new_set = {item.decode("utf-8") for item in set}
	return new_set
