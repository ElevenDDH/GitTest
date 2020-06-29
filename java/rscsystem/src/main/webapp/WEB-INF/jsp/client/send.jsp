<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/25
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>寄件页面</title>
</head>
<body>
<center>
<h3>请填写好以下邮件信息</h3>
<%--post方法会被Security过滤掉，导致403无权限访问--%>
<form action="/client/sendSuccess" method="post">
    寄件人：<input type="text" name="sender_name"><br>
    邮件名称：<input type="text" name="mail_name"><br>
    收件人：<input type="text" name="receiver_name"><br>
    <%--注意receive没r--%>
    收件地址：<input type="text" name="receive_address" list="addresslist">
    <datalist id="addresslist">
        <option>从化区太平镇</option>
        <option>从化区良口镇</option>
    </datalist>
    <br>
    收件人电话：<input type="text" name="receiver_phone"><br>
    <input type="submit" value="提交">
    <%--只要添加这个token,后台就会验证这个token的正确性,如果正确,则接受post访问。--%>
    <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}">
</form>
</center>
</body>
</html>
