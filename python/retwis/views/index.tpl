% include('shared/header.tpl')
<!-- 主体 -->
<div class="span-24">
	<div class="span-16">
		<!-- 内容填写 -->
		<div id="updateform" class="box">
			<form action="/post" method="post" accept-charset="utf-8">
				{{username}}, 写下你的想法吧~<br>
				<textarea name="content" cols="70" rows="3"></textarea><br>
				<input type="submit" value="提交"/>
			</form>
		</div>

		<!-- 文章列表 -->
		% include('shared/posts.tpl', posts=posts)

	</div>
	<!-- 右两栏 -->
	<div class="span-7 last">
		<div class="box">
			<h4>Follower:{{followers_num}}</h4>

			% include('shared/userlist.tpl', users=followers)

		</div>
		<div class="box">
			<h4>Following:{{following_num}}</h4>
			
			% include('shared/userlist.tpl', users=following)
		</div>
	</div>
</div>
% include('shared/footer.tpl')