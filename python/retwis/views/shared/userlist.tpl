<ul class="user-list">
	% for user in users:
	<li>
		<a href="/{{user.username}}" title="">
			{{user.username}}
		</a>
		
	</li>
	% end
</ul>