<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>验证页面</title>
	<link rel="stylesheet" href="">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h2>验证</h2>
	<hr>
	<form action="/signup" method="post" accept-charset="utf-8">
		手机号码：
		<input type="text" name="mobile" id="mobile" value="" placeholder="">
		验证码：
		<input type="text" name="code" id="code" value="" placeholder="">
		<input type="button" name="" value="获取验证码" id="getcode">
		<input type="submit" name="" value="注册">
	</form>
	
</body>
<script type="text/javascript">
	$('#getcode').click(()=>{
		let mobile = $('#mobile').val()
		let sec = 60
		$('#getcode').attr('disabled', true)
		$('#getcode').val(`${sec}s后重新获取`)
		timer = setInterval(setcode, 1000)
		function setcode(){
			if (sec <= 0) {
				clearInterval(timer)
				$('#getcode').attr('disabled', false)
				$('#getcode').val('重新获取验证码')
			}else{
				sec--
				$('#getcode').val(`${sec}s后重新获取`)
			}
		}
		data = {
			'mobile' : mobile
		}
		// ajax异步请求
		$.post('/getcode', data, (result)=>{
			console.log(result)
		})
	})
</script>
</html>