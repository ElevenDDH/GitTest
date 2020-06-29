<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Retwis</title>
	<link rel="stylesheet" type="text/css" href="/public/css/custom.css">
	<link rel="stylesheet" type="text/css" href="/public/css/screen.css">
</head>
<body>
	<div class="container">
		<!-- 头部 -->
		<div id="header" class="span-24">
			<div class="span-12">
				<br>
				<h1>Retwis</h1>
			</div>

			% if defined('username'):
			<div class="span-12 last right-align">
				<br>
				<br>
				<a href="/">home</a> |
				<a href="/mentions/{{username}}">mentions</a> |
				<a href="/{{username}}">{{username}}</a> |
				<a href="/timeline">timeline</a> |
				<a href="/logout">logout</a> |
			</div>
			% end
			<hr>

		</div>