% include('shared/header.tpl', username=loginname)
<!-- % include('shared/header.tpl') -->

<div class="span-24">
	<div class="span-16">
		<h2>Mentions of {{username}}</h2>
		% if username != loginname:
		<div class="box">
			% if not isfollowing:
			<a href="/{{loginname}}/following/{{username}}" title="">Following</a>
			% else:
			<a href="/{{loginname}}/unfollowing/{{username}}" title="">Stop Following</a>
			% end
		</div>
		% end

		% include('shared/posts.tpl', posts=posts)
	</div>
</div>

<!-- % include('shared/footer.tpl') -->
% include('shared/footer.tpl')