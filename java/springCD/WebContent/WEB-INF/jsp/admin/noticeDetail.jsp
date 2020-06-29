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
<link href="css/admin/common.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/fresh-bootstrap-table.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="wrapper">
    <div class="fresh-table full-color-orange full-screen-table" style="height: 760px;">
	<font size=6 face=华文新魏 color="#000">${notice.ntitle }</font>
	<p>&nbsp;&nbsp;&nbsp;&nbsp;<font size=5 face=华文新魏 color="#000">${notice.ncontent }</font></p>		
	<p align="right"><font size=5 face=华文新魏 color="#000">${notice.ntime }</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>				
	<form:form action="adminNotice/deleteNoticeSelect" method="post" enctype="multipart/form-data" target="center" style="text-align:right;">
		<input type="submit" value="返回" class="btn btn-default"/>
	</form:form>
</div>
</div>
</body>
</html>