<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/29
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>邮件状态</title>
</head>
<body>
<h2>已发送：</h2>
<table border="0" cellpadding="10">
    <tr>
        <th style="text-align: center">包裹名称</th>
        <th style="text-align: center">发送人名称</th>
        <th style="text-align: center">收件人名称</th>
        <th style="text-align: center">收件地址</th>
        <th style="text-align: center">收件人电话</th>
        <th style="text-align: center">包裹状态</th>
        <th style="text-align: center">邮差</th>
    </tr>
    <c:forEach items="${sessionScope.mails}" var="mails">
        <tr>
            <td style="text-align: center">${mails.mail_name}</td>
            <td style="text-align: center">${mails.sender_name}</td>
            <td style="text-align: center">${mails.receiver_name}</td>
            <td style="text-align: center">${mails.receive_address}</td>
            <td style="text-align: center">${mails.receiver_phone}</td>
            <td style="text-align: center">${mails.mail_state}</td>
            <td style="text-align: center">${mails.postman_name}</td>
        </tr>
    </c:forEach>
</table>
<h2>待接收：</h2>
<table border="0" cellpadding="10">
    <tr>
        <th style="text-align: center">包裹名称</th>
        <th style="text-align: center">发送人名称</th>
        <th style="text-align: center">收件人名称</th>
        <th style="text-align: center">收件地址</th>
        <th style="text-align: center">收件人电话</th>
        <th style="text-align: center">包裹状态</th>
        <th style="text-align: center">邮差</th>
    </tr>
    <c:forEach items="${sessionScope.getmails}" var="getmails">
        <tr>
            <td style="text-align: center">${getmails.mail_name}</td>
            <td style="text-align: center">${getmails.sender_name}</td>
            <td style="text-align: center">${getmails.receiver_name}</td>
            <td style="text-align: center">${getmails.receive_address}</td>
            <td style="text-align: center">${getmails.receiver_phone}</td>
            <td style="text-align: center">${getmails.mail_state}</td>
            <td style="text-align: center">${getmails.postman_name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
