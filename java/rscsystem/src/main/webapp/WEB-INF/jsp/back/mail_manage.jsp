<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/26
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>包裹管理</title>
</head>
<body>
<center>
<h2>包裹管理</h2>
<table border="0" cellpadding="10">
    <tr>
        <th style="text-align: center">包裹编号</th>
        <th style="text-align: center">包裹名称</th>
        <th style="text-align: center">发送人名称</th>
        <th style="text-align: center">收件人名称</th>
        <th style="text-align: center">收件地址</th>
        <th style="text-align: center">收件人电话</th>
        <th style="text-align: center">负责人</th>
        <th style="text-align: center">包裹状态</th>
        <th style="text-align: center">管理</th>
    </tr>
    <c:forEach items="${sessionScope.mail}" var="mail">
        <tr>
            <td style="text-align: center">${mail.mail_id}</td>
            <td style="text-align: center">${mail.mail_name}</td>
            <td style="text-align: center">${mail.sender_name}</td>
            <td style="text-align: center">${mail.receiver_name}</td>
            <td style="text-align: center">${mail.receive_address}</td>
            <td style="text-align: center">${mail.receiver_phone}</td>
            <td style="text-align: center">${mail.postman_name}</td>
            <td style="text-align: center">${mail.mail_state}
                <c:if test="${mail.mail_state eq '派件失败'}">
                    ，原因：${mail.fail_reason}
                </c:if>
                <c:if test="${mail.mail_state eq '收件失败'}">
                    ，原因：${mail.fail_reason}
                </c:if>
            </td>
            <td style="text-align: center">
                <form action="/back/reManage">
                    <input type="hidden" value="${mail.mail_id}" name="mail_id">
                    <input type="submit" value="重新分配">
                </form>

            </td>

        </tr>
    </c:forEach>
</table>
</center>
</body>
</html>
