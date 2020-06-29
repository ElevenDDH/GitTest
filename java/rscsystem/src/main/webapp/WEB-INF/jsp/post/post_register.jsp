<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/28
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成为邮差</title>
</head>
<body>
<center>
<form action="/rsc/postRegister" method="post">
    用户名：<input type="text" name="name" id="name"><br>
    账号：<input type="text" name="account" id="account"><br>
    密码：<input type="password" name="password" id="password"><br>
    所属地：<input type="radio" name="postman_area" value="从化区太平镇">从化区太平镇
    <input type="radio" name="postman_area" value="从化区良口镇">从化区良口镇<br>

    <input type="submit" value="注册">
    <%--只要添加这个token,后台就会验证这个token的正确性,如果正确,则接受post访问。--%>
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}">
</form>
</center>
</body>
</html>
