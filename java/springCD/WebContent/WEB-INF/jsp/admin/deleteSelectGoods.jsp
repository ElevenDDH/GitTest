<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <base href="<%=basePath%>">
  <meta charset="utf-8" />
	<link rel="icon" type="image/png" href="images/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>selectGoods.jsp</title>
	<style type="text/css">
		table{
			text-align: center;
			border-collapse: collapse;
		}
		.bgcolor{
		  	background-color: #F08080;
		}
	</style>
	<script type="text/javascript">
		function changeColor(obj){
			obj.className = "bgcolor";
		}
		function changeColor1(obj){
			obj.className = "";
		}
	</script>
	<script type="text/javascript">
  		function confirmDelete(){
  			var n = document.deleteForm.ids.length;
  			var count = 0;//统计没有选中的个数
  			for(var i = 0; i < n; i++){
  				if(!document.deleteForm.ids[i].checked){
  					count++;
  				}else{
  					break;
  				}
  			}
  			if(n > 1){
  				if(count == n){
  					alert("请选择被删除的商品！");
  	  				count = 0;
  	  				return false;
  				}
  			}else{
  	  			if(!document.deleteForm.ids.checked){
  	  					alert("请选择被删除的商品！");
  	  					return false;
  	  			}
  			}	
  		
  			if(window.confirm("真的删除吗？really?")){
  				document.deleteForm.submit();
  				return true;
  			}
  			return false;
  		}
  		function checkDel(id){
  			if(window.confirm("是否删除该商品？")){
  				window.location.href = "/springCD/adminGoods/deleteAGoods?id="+id;
  			}
  		}
  </script>
  <link href="css/bootstrap.css" rel="stylesheet" />
    <link href="css/fresh-bootstrap-table.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
</head>
<body>
	<c:if test="${allGoods.size() == 0 }">
		您还没有商品。
	</c:if>
	<div class="wrapper">
		<div class="fresh-table full-color-orange full-screen-table">
	<c:if test="${allGoods.size() != 0 }">
		<form:form action="adminGoods/deleteGoods" name="deleteForm" enctype="multipart/form-data">
		<table border=0 class="table" id="fresh-table">
			<thead>
				<th width="100px">ID</th>
				<th width="200px">名称</th>
				<th width="200px">价格</th>
				<th width="100px">库存</th>
				<th width="100px">详情</th>
				<th width="100px">操作</th>
			</thead>
			<tbody>
			<c:forEach items="${allGoods }" var="goods">
				<tr onmousemove="changeColor(this)" onmouseout="changeColor1(this)">
					<td><input type="checkbox" name="ids" value="${goods.id }"/>${goods.id }</td>
					<td>${goods.gname }</td>
					<td>${goods.grprice }</td>
					<td>${goods.gstore }</td>
					<td><a href="adminGoods/selectAGoods?id=${goods.id }" target="center">详情</a></td>
					<td>
						<a href="javascript:checkDel('${goods.id }')">删除</a>
					</td>
				</tr>	
			</c:forEach>
			
			</tbody>
		</table>
		</form:form>	
	</c:if>
	<form:form  style="text-align:right;">
		<input type="button" value="删除" onclick="confirmDelete()" class="btn btn-default"><br>
		${msg }
	</form:form>
	</div>
		</div>	
</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.js"></script>
        
    <script type="text/javascript">
        var $table = $('#fresh-table'),
            $alertBtn = $('#alertBtn'), 
            full_screen = true,
            window_height;
            
        $().ready(function(){
            
            window_height = $(window).height();
            table_height = window_height - 20;
            
            
            $table.bootstrapTable({
                toolbar: ".toolbar",

                showRefresh: true,
                search: true,
                showToggle: true,
                showColumns: true,
                pagination: true,//分页
                striped: true,//条纹
                sortable: true,//排序
                height: 737,
                pageSize: 10,
                pageList: [5,10,20],
                
                formatShowingRows: function(pageFrom, pageTo, totalRows){
                    //do nothing here, we don't want to show the text "showing x of y from..." 
                },
                formatRecordsPerPage: function(pageNumber){
                    return pageNumber + " rows visible";
                },
                icons: {
                    refresh: 'fa fa-refresh',
                    toggle: 'fa fa-th-list',
                    columns: 'fa fa-columns',
                    detailOpen: 'fa fa-plus-circle',
                    detailClose: 'fa fa-minus-circle'
                }
            });
            
            window.operateEvents = {
                'click .like': function (e, value, row, index) {
                    alert('You click like icon, row: ' + JSON.stringify(row));
                    console.log(value, row, index);
                },
                'click .edit': function (e, value, row, index) {
                    alert('You click edit icon, row: ' + JSON.stringify(row));
                    console.log(value, row, index);    
                },
                'click .remove': function (e, value, row, index) {
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    });
            
                }
            };
            
            $alertBtn.click(function () {
                alert("You pressed on Alert");
            });
        
            
            $(window).resize(function () {
                $table.bootstrapTable('resetView');
            });    
        });
        

        function operateFormatter(value, row, index) {
            return [
                '<a rel="tooltip" title="Like" class="table-action like" href="javascript:void(0)" title="Like">',
                    '<i class="fa fa-heart"></i>',
                '</a>',
                '<a rel="tooltip" title="Edit" class="table-action edit" href="javascript:void(0)" title="Edit">',
                    '<i class="fa fa-edit"></i>',
                '</a>',
                '<a rel="tooltip" title="Remove" class="table-action remove" href="javascript:void(0)" title="Remove">',
                    '<i class="fa fa-remove"></i>',
                '</a>'
            ].join('');
        }
       
    </script>

</html>