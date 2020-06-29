<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>发布文章</title>
	<link rel="stylesheet" href="">
</head>
<body>
	<center>
		<h1>发布文章</h1>
		<div>
			{{get('tips', '')}}
			<form action="/result" name="form" method="post">
				<input name="title" type="text" style="width: 40%" placeholder="put down your article title" /><br><br>

				<textarea name="articles" style="width: 40%; height: 200px" placeholder="put down your articles"></textarea><br><br>

				<input type="text" name="slug" style="width: 40%" placeholder="put down your english slug"><br><br>

    			<input name="name" type="text" style="width: 40%" placeholder="put down your name" /><br><br>
    			<hr>
    			<input value="submit" type="submit" />
			</form>
		</div>
	</center>
</body>
</html>