from flask import (
	Flask,
	render_template,
	request,
	session,
	redirect,
	Response
)
import redis, time

app = Flask(__name__)
app.secret_key = '123456'

r = redis.StrictRedis(charset='utf-8', decode_responses=True)


@app.route('/')
def index():
	# 渲染模板
	return render_template('login.html')


@app.route('/login', methods=['GET', 'POST'])
def login():
	if request.method == 'POST':
		session['user'] = request.form['user']
		return redirect('/chat')
	else:
		return render_template('login.html')


@app.route('/chat', methods=['GET', 'POST'])
def chat():
	if request.method == 'POST':
		msg = request.form['msg']
		ctime = time.strftime('%H:%M:%S')
		user = session['user']
		data = '[{}] {} : {}'.format(ctime, user, msg)
		r.publish('chat', data)
		return Response(status=203)
	else:
		if 'user' in session:
			user = session['user']
			return render_template('chat.html', user=user)
		return redirect('/login')


# 生成器
def stream():
	p = r.pubsub(ignore_subscribe_messages=True)
	p.subscribe('chat')
	for message in p.listen():
		print(message)
		# 转换为SSE格式
		data = 'data:{}\n\n'.format(message['data'])
		yield(data)


# 不断从订阅中取值
@app.route('/msg')
def msg():
	return Response(stream(), mimetype='text/event-stream')
