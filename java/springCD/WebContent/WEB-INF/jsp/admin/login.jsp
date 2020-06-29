<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<base href="<%=basePath%>">
        <meta charset="utf-8">
        <title>Backstage landing</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/login.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
    </head>

    <!-- <body style="
    background: url(images/backgrounds/2.jpg) no-repeat;
    background-size:100% 100%;
    background-attachment: fixed; 
    "> -->
    <body>

        <div class="page-container">
            <h1>Login</h1>
            <form:form action="admin/login" modelAttribute="auser" method="post">
                <form:input path="aname" placeholder="Username" />
                <form:password path="apwd" placeholder="Password" />
                <button type="submit">登录</button>
                <div class="error"><span>+</span></div>
            </form:form>
            
        </div>
		${msg }
		<!-- Javascript -->
        <script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
        <script src="js/supersized.3.2.7.min.js" type="text/javascript"></script>
        <script src="js/supersized-init.js" type="text/javascript"></script>
        <script src="js/scripts.js" type="text/javascript"></script>
    </body>

</html>

