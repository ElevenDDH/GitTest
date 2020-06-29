<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>查看</title>
	<link rel="stylesheet" href="">
	<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
	<style type="text/css">
		.tag-list{
			margin-right: 20px; 
		}
	</style>
</head>
<body>
	<h2>suucess!!</h2>
	<hr>
	
	<h3>Id:</h3><br>
	<span id="p-id">{{id}}</span><br>
	<h3>title:</h3><br>
	{{title}}<br>
	<h3>article:</h3><br>
	{{articles}}<br>
	<h3>name:</h3><br>
	{{name}}<br>
	<h3>send time:</h3><br>
	{{time}}<br>
	<h3>visit time:</h3><br>
	{{visit_time}}<br>

	<hr>
	<div>
		<h4>Tags:</h4>
		<div id="show-tags">
			%for tag in tags:
				<a class='tag-list' href='/tag/list/{{tag}}'>{{tag}}</a>
			%end
		</div>
	</div>

	<div>
		<input type="text" id="tag" name="" value="" placeholder="">
		<button id="add-tag">add tag</button>
	</div>

	<script type="text/javascript">
		$('#add-tag').click(() =>{
			let p_id = $('#p-id').text()
			let tag = $('#tag').val()
			let data = {
				'p_id':p_id,
				'tag':tag
			}

			$.post("/tags/add", data, (result) =>{
				$('#show-tags').empty()
				let tags = result.tags
				for(const tag of tags){
					$('#show-tags').append(`<a class='tag-list' href='/tag/list/${tag}'>${tag}</a>`)
				}
			})
		})
	</script>
</body>
</html>