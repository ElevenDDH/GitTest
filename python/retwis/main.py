import bottle, time
import settings, session
from convert import to_dict, to_list, to_set, to_string
from model import User, Post, Timeline

r = settings.r

def islogin():
	sess = session.Session(bottle.request, bottle.response)
	if sess.is_new():
		return False
	else:
		return User(sess['id'])

@bottle.get('/')
@bottle.view('index')
def index():
	user = islogin()
	if user:
		res = {
			'username':user.username,
			'posts':user.timeline(),
			'followers':user.followers(),
			'following':user.following(),
			'followers_num':user.followers_num(),
			'following_num':user.following_num()
		}

		return res
	else:
		bottle.redirect('/signup')

@bottle.get('/timeline')
@bottle.view('timeline')
def timeline():
	user = islogin()
	if user:
		res = {
			'username':user.username, 
			'posts':Timeline.posts(), 
			'users':User.users()
		}
		return res
	else:
		bottle.redirect('/')

@bottle.get('/<username>')
@bottle.view('profile')
def profile(username):
	login_user = islogin()
	user = User.find_by_username(username)
	if login_user and user:
		res = {
			'username':user.username,
			'loginname':login_user.username,
			'followers':user.followers(),
			'following':user.following(),
			'followers_num':user.followers_num(),
			'following_num':user.following_num(),
			'posts':user.posts(),
			'isfollowing':login_user.isfollowing(user)
		}
		return res
	bottle.redirect('/')

# 关注
@bottle.get('/<loginname>/following/<username>')
def following(loginname, username):
	login_user = User.find_by_username(loginname)
	user = User.find_by_username(username)
	if login_user and user:
		login_user.add_following(user)
		bottle.redirect('/{}'.format(user.username))
	else:
		bottle.redirect('/')

# 取关
@bottle.get('/<loginname>/unfollowing/<username>')
def unfollowing(loginname, username):
	login_user = User.find_by_username(loginname)
	user = User.find_by_username(username)
	if login_user and user:
		login_user.remove_following(user)
		bottle.redirect('/{}'.format(user.username))
	else:
		bottle.redirect('/')

@bottle.get('/mentions/<username>')
@bottle.view('mentions')
def mentions(username):
	login_user = islogin()
	user = User.find_by_username(username)
	if login_user and user:
		res = {
			'loginname':login_user.username,
			'username':user.username,
			'isfollowing':login_user.isfollowing(user),
			'posts':user.mentions()
		}
		return res
	return bottle.redirect('/')

@bottle.post('/post')
def post():
	user = islogin()
	if user:
		content = bottle.request.POST['content']
		Post.create(user, content)
		bottle.redirect('/')

	else:
		bottle.redirect('/signup')


@bottle.get('/signup')
@bottle.view('signup')
def signup():
	return dict()

@bottle.post('/login')
@bottle.view('signup')
def login():
	username = bottle.request.POST['username']
	password = bottle.request.POST['password']

	user = User.find_by_username(username)
	if user:
		if user.password == password:
			sess = session.Session(bottle.request, bottle.response)
			sess['id'] = user.id
			sess.save()

			bottle.redirect('/')
	return dict()

@bottle.get('/logout')
def logout():
	sess = session.Session(bottle.request, bottle.response)
	sess.invalided()
	bottle.redirect('/')


@bottle.post('/signup')
@bottle.view('signup')
def signup():
	username = bottle.request.POST['username']
	password = bottle.request.POST['password']

	user = User.create(username, password)
	if user:
		sess = session.Session(bottle.request, bottle.response)
		sess['id'] = user.id
		sess.save()

		bottle.redirect('/')

	return dict()

# 设置引用静态文件路由
@bottle.get('/public/<filename:path>')
def send_static(filename):
	return bottle.static_file(filename, root='public/')


bottle.run(reloader=True, host='0.0.0.0', port=1212)
