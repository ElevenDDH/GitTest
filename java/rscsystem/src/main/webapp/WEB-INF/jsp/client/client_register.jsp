<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/18
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成为顾客</title>
</head>
<body>
<center>
<h2>客户注册</h2>
<form action="/rsc/clientRegister" method="post">
    用户名：<input type="text" name="name" id="name"><br><br>
    账号：<input type="text" name="account" id="account"><br><br>
    密码：<input type="password" name="password" id="password"><br><br>
    <input type="submit" value="注册">
    <%--只要添加这个token,后台就会验证这个token的正确性,如果正确,则接受post访问。--%>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}">
</form>
</center>
</body>
</html>
