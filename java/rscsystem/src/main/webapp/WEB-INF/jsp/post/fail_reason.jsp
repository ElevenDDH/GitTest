<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/30
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>失败原因</title>
</head>
<body>
<center>
    <h3>请填写好取消的原因</h3>
    <%--post方法会被Security过滤掉，导致403无权限访问--%>
    <form action="/post/fail_reason" method="post">
        包裹编号:<input type="text" name="mail_id" value="${sessionScope.mails.mail_id}" readonly="readonly"><br>
        原因：<textarea cols="40" rows="9" name="fail_reason"></textarea><br><br>
        <input type="submit" value="提交">
        <%--只要添加这个token,后台就会验证这个token的正确性,如果正确,则接受post访问。--%>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}">
    </form>
</center>
</body>
</html>
