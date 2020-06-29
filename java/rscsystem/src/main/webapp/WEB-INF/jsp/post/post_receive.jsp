<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/29
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>待收件</title>
</head>
<script>
    function search(href) {
        var form = document.form1;
        form.action = href;
        form.method="post";
        form.submit();
    }
</script>
<body>
<center>
    <h2>待收件</h2>
    <table border="0" cellpadding="10">
        <tr>
            <th style="text-align: center">包裹编号</th>
            <th style="text-align: center">包裹名称</th>
            <th style="text-align: center">发送人名称</th>
            <th style="text-align: center">收件人名称</th>
            <th style="text-align: center">收件地址</th>
            <th style="text-align: center">收件人电话</th>
            <th style="text-align: center">包裹状态</th>
            <th style="text-align: center">操作</th>
        </tr>
        <c:forEach items="${sessionScope.mails}" var="mails">
            <tr>
                <form name="form1" action="/post/receive_success" method="post">
                    <td style="text-align: center">
                        <input type="text" name="mail_id" value="${mails.mail_id}" readonly="readonly">
                    </td>
                    <td style="text-align: center">${mails.mail_name}</td>
                    <td style="text-align: center">${mails.sender_name}</td>
                    <td style="text-align: center">${mails.receiver_name}</td>
                    <td style="text-align: center">
                        <input type="hidden" name="receive_address" value="${mails.receive_address}">
                            ${mails.receive_address}
                    </td>
                    <td style="text-align: center">${mails.receiver_phone}</td>
                    <td style="text-align: center">${mails.mail_state}</td>
                    <td style="text-align: center">
                        <input type="submit" value="收件"><br><br>
                        <input type="button" onclick="search('/post/receive_fail')" value="取消收件">
                    </td>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}">
                </form>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
