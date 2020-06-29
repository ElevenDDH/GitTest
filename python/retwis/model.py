import settings, time, re
from convert import to_dict, to_list, to_set, to_string

r = settings.r

class Timeline:
	# 拿到10个最新得posts对象
	@staticmethod
	def posts(page = 1, num = 10):
		start = (page - 1) * num
		end = page * num - 1
		posts_id = to_list(r.lrange('timeline', start, end))
		return [Post(pid) for pid in posts_id]


class User:
	def __init__(self, id : int):
		self.id = int(id)
		udata = to_dict(r.hgetall('user:{}'.format(self.id)))
		self.username = udata['username']
		self.password = udata['password']

	@staticmethod
	def find_by_id(id : int):
		if r.exists('user:{}'.format(int(id))):
			return User(int(id))
		return None


	@staticmethod
	def find_by_username(username : str):
		# 判断用户名是否存在
		uid = r.hget('users', username)
		if uid:
			return User(int(uid))
		return None


	@staticmethod
	def create(username : str, password : str):
		uid = r.hget('users', username)
		if not uid:
			uid = r.incr('user:uid')
			udata = {
			'username':username,
			'password':password
			}
			r.hmset('user:{}'.format(uid), udata)
			r.hset('users', username, uid)
			return User(int(uid))
		return None

	@staticmethod
	def users():
		users = to_dict(r.hgetall('users'))
		return [User(uid) for username, uid in users.items()]

	def posts(self):
		posts_id = to_list(r.lrange('user:{}:posts'.format(self.id), 0, 9))
		return [Post(pid) for pid in posts_id]

	def timeline(self):
		posts_id = to_list(r.lrange('user:{}:timeline'.format(self.id), 0, 9))
		return [Post(pid) for pid in posts_id]

	def followers(self):
		followers = to_list(r.smembers('user:{}:followers'.format(self.id)))
		return [User(uid) for uid in followers]

	def following(self):
		following = to_list(r.smembers('user:{}:following'.format(self.id)))
		return [User(uid) for uid in following]

	def followers_num(self):
		return r.scard('user:{}:followers'.format(self.id))

	def following_num(self):
		return r.scard('user:{}:following'.format(self.id))

	def isfollowing(self, user):
		if self.id != user.id:
			print(r.sismember('user:{}:following'.format(self.id), user.id))
			
			return r.sismember('user:{}:following'.format(self.id), user.id)
		return False

	def add_following(self, user):
		if self.id != user.id:
			r.sadd('user:{}:following'.format(self.id), user.id)
			r.sadd('user:{}:followers'.format(user.id), self.id)
			return True
		return False

	def remove_following(self, user):
		if self.id != user.id:
			r.srem('user:{}:following'.format(self.id), user.id)
			r.srem('user:{}:followers'.format(user.id), self.id)
			return True
		return False

	def add_mention(self, pid:int):
		r.lpush('user:{}:mentions'.format(self.id), pid)

	def mentions(self):
		posts_id = to_list(r.lrange('user:{}:mentions'.format(self.id), 0, 9))
		return [Post(pid) for pid in posts_id]

	def add_timeline(self, pid:int):
		r.lpush('user:{}:timeline'.format(self.id), pid)

class Post:
	def __init__(self, id : int):
		self.id = id
		pdata = to_dict(r.hgetall('post:{}'.format(self.id)))
		self.userid = pdata['userid']
		self.content = pdata['content']
		self.posttime = pdata['posttime']

	@staticmethod
	def find_by_id(id : int):
		if r.sismember('posts:id', int(id)):
			return Post(int(id))
		return None

	@staticmethod
	def create(user : User, content : str):
		pid = r.incr('post:uid')
		pdata = {
			'userid':user.id,
			'content':content,
			'posttime':time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
		}
		r.hmset('post:{}'.format(pid), pdata)
		r.lpush('user:{}:timeline'.format(user.id), pid)
		r.lpush('user:{}:posts'.format(user.id), pid)
		r.lpush('timeline', pid)
		r.sadd('posts:id', pid)

		followers = user.followers()
		for follow in followers:
			follow.add_timeline(int(pid))

		mentions = re.findall('@\w+', content)
		for mention in mentions:
			u = User.find_by_username(mention[1:])
			if u:
				u.add_mention(int(pid))

		return Post(int(pid))

	@property
	def username(self):
		return to_string(r.hget('user:{}'.format(self.userid), 'username'))
