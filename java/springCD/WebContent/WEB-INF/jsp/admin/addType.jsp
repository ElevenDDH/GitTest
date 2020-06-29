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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		table{
			text-align: center;
			border-collapse: collapse;
		}
		.bgcolor{
		  	background-color: #F08080;
		}
	</style>
	<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/fresh-bootstrap-table.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="wrapper">
    <div class="fresh-table full-color-orange full-screen-table" style="height: 760px;">
	<c:if test="${allTypes.size() == 0 }">
		您还没有类型。
	</c:if>
	<c:if test="${allTypes.size() != 0 }">
		<table border=0 style="border-collapse: collapse" class="table">
			<tr>
				<th width="200px" style="text-align:center;">类型ID</th>
				<th width="600px" style="text-align:center;">类型名称</th>
			</tr>
			<c:forEach items="${allTypes }" var="goodsType">
				<tr>
					<td>${goodsType.id }</td>
					<td>${goodsType.typename }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<form:form action="adminType/addType" method="post" enctype="multipart/form-data">
		<font color="#fff"><b>类型名称：</b></font>
		<input type="text" name="typename" class="btn btn-default"/>
		<input type="submit" value="添加" class="btn btn-default"/>
	</form:form>
	</div>
	</div>
</body>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.js"></script>
</html>