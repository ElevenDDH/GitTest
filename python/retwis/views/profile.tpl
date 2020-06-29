% include('shared/header.tpl', username=loginname)
<!-- % include('shared/header.tpl') -->

<div class="span-24">
	<div class="span-16">
		<h2>{{username}}</h2>
		% if username != loginname:
			<div class="box">

				% if not isfollowing:
					<a href="/{{loginname}}/following/{{username}}" title="">Following</a>
				% else:
					<a href="/{{loginname}}/unfollowing/{{username}}" title="">Stop Following</a> | 
				% end

				<a href="/mentions/{{username}}" title="">See mentions</a>
			</div>
		% end

		% include('shared/posts.tpl', posts=posts)
	</div>

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

<!-- % include('shared/footer.tpl') -->
% include('shared/footer.tpl')