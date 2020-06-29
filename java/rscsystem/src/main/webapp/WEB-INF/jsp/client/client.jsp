<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/21
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户页面</title>
</head>
<body>
<center>
    <h2>欢迎${sessionScope.client.name}</h2>
    <a href="/client/send"><button>寄件</button></a><br><br>
    <a href="/client/mailState"><button>查看邮件状态</button></a><br><br>
</center>
</body>
</html>
