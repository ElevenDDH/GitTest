<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>addNotice.jsp</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/fresh-bootstrap-table.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
</head>    
<body>
<div class="wrapper">
    <div class="fresh-table full-color-orange full-screen-table" style="height: 760px;">
	<form:form action="adminNotice/addNotice" method="post" modelAttribute="notice" enctype="multipart/form-data">
		<table border=0 style="border-collapse: collapse" class="table">
			<caption>
				<font size=4 face=华文新魏 color="#000">添加公告</font>
			</caption>
			<tr>
				<td>标题<font color="red">*</font></td>
				<td>
					<font color="black"><form:input path="ntitle"/></font>
				</td>
			</tr>
			<tr>
				<td>内容<font color="red">*</font></td>
				<td>
					<font color="black"><form:textarea path="ncontent"/></font>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="submit" value="提交" class="btn btn-default"/>
				</td>
				<td align="left">
					<input type="reset" value="重置" class="btn btn-default"/>
				</td>
			</tr>
		</table>
	</form:form>
	</div>
	</div>
</body>
</html>
