<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>主页</title>
	<link rel="stylesheet" href="">
</head>
<body>
	<h2>Welcome!</h2>
	<hr>
	<form action="/pubsub" method="post" accept-charset="utf-8">
		订阅用户邮箱:
		<input type="email" name="email" value="" placeholder="">
		<input type="submit" name="" value="订阅">
	</form>
	<hr>
	<table>
		<tr>
			<th>id</th>
			<th>标题</th>
			<th>作者</th>
			<th>发表时间</th>
			<th>访问量</th>
		</tr>
		%for id, post in posts:
			<tr>
					<td>{{id}}</td>
					<td><a href="/{{post['slug']}}">
					{{post['title']}}</a></td>
					<td>{{post['name']}}</td>
					<td>{{post['time']}}</td>
					<td>
						{{visit_time[id]}}
					</td>
			</tr>
		%end
	</table>
	<div>
		<button><a href="/?page={{page - 1}}">上一页</a></button>
		{{page}}
		<button><a href="/?page={{page + 1}}">下一页</a></button>
	</div>
	<button>
		<a href="/send">发布文章</a>
	</button>
</body>
</html>