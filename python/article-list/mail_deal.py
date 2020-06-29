import redis, json, time, mail, threading

r = redis.StrictRedis()

class MailSend(threading.Thread):
	"""docstring for MailSend"""
	def __init__(self):
		threading.Thread.__init__(self)

	def run(self):
		while True:
			email = r.brpop(['queue:confirm.email', 'queue:notify.email'], 0)
			email = email[1]
			email_info = json.loads(email.decode('utf-8'))
			mail.send_email(email_info['email'], email_info['url'], email_info['tpl'])

