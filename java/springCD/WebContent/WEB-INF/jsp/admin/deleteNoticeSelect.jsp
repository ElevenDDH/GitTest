<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  <base href="<%=basePath%>">
    <title>selectGoods.jsp</title>
	<link href="css/admin/common.css" type="text/css" rel="stylesheet">
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
  		function checkDel(id){
  			if(window.confirm("是否删除该公告？")){
  				window.location.href = "/springCD/adminNotice/deleteNotice?id="+id;
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
	<c:if test="${allNotices.size() == 0 }">
		您还没有公告。
	</c:if>
	<c:if test="${allNotices.size() != 0 }">
	<div class="wrapper">
    <div class="fresh-table full-color-orange full-screen-table" style="height: 760px;">
		<table border=0 style="border-collapse: collapse" class="table">
			<tr>
				<th width="200px" style="text-align:center;">ID</th>
				<th width="200px" style="text-align:center;">标题</th>
				<th width="200px" style="text-align:center;">时间</th>
				<th width="100px" style="text-align:center;">详情</th>
				<th width="100px" style="text-align:center;">操作</th>
			</tr>
			<c:forEach items="${allNotices }" var="notice">
				<tr onmousemove="changeColor(this)" onmouseout="changeColor1(this)">
					<td>${notice.id }</td>
					<td>${notice.ntitle }</td>
					<td>${notice.ntime }</td>
					<td><a href="adminNotice/selectANotice?id=${notice.id }" target="center">详情</a></td>
					<td>
						<a href="javascript:checkDel('${notice.id }')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		</div>
	</c:if>
</body>
</html>