% include('shared/header.tpl')

<div class="span-24">
	<div class="span-11 box">
		<form action="/login" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" value="" placeholder=""></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" value="" placeholder=""></td>
				</tr>
				<tr>
					<td><button type="submit">登录</button></td>
				</tr>
			</table>
		</form>
	</div>

	<div class="span-11 box last">
		<form action="/signup" method="post">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" value="" placeholder=""></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" value="" placeholder=""></td>
				</tr>
				<tr>
					<td><button type="submit">注册</button></td>
				</tr>
			</table>
		</form>
	</div>
</div>

% include('shared/footer.tpl')
