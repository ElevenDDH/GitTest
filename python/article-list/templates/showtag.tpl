<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>show tag</title>
	<link rel="stylesheet" href="">
</head>
<body>
	<h4>Tag:{{tag}}</h4>
	<hr>
	<table>
		<tr>
			<th>id</th>
			<th>标题</th>
			<th>作者</th>
			<th>发表时间</th>
		</tr>
		%for id, post in posts:
			<tr>
					<td>{{id}}</td>
					<td><a href="/{{post['slug']}}">
					{{post['title']}}</a></td>
					<td>{{post['name']}}</td>
					<td>{{post['time']}}</td>
			</tr>
		%end
	</table>
</body>
</html>