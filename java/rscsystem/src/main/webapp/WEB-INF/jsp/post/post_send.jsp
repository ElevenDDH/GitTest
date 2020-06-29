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
    <title>待派件</title>
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
    <h2>待派件</h2>
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
        <c:forEach items="${sessionScope.sendMails}" var="mails">
            <tr>
                <form name="form1" action="/post/sendSuccess" method="post">
                    <td style="text-align: center">
                        <input type="hidden" name="mail_id" value="${mails.mail_id}">
                            ${mails.mail_id}
                    </td>
                    <td style="text-align: center">${mails.mail_name}</td>
                    <td style="text-align: center">${mails.sender_name}</td>
                    <td style="text-align: center">${mails.receiver_name}</td>
                    <td style="text-align: center">${mails.receive_address}</td>
                    <td style="text-align: center">${mails.receiver_phone}</td>
                    <td style="text-align: center">
                        <c:if test="${mails.sendFailNum == 0 || mails.sendFailNum == null }">
                            ${mails.mail_state}
                        </c:if>
                        <c:if test="${mails.sendFailNum == 3}">
                            ${mails.mail_state}<br>
                            注意：已取消<input type="hidden" name="sendFailNum" value="${mails.sendFailNum}">
                            ${mails.sendFailNum}次,再取消将派件失败
                        </c:if>
                        <c:if test="${mails.sendFailNum > 0 && mails.sendFailNum < 3}">
                            ${mails.mail_state}<br>
                            注意：已取消<input type="hidden" name="sendFailNum" value="${mails.sendFailNum}">
                            ${mails.sendFailNum}次
                        </c:if>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${mails.mail_state eq '收件成功，准备派件'}">
                            <input type="button" onclick="search('/post/start_send')" value="开始派件"><br><br>
                        </c:if>
                        <c:if test="${mails.mail_state eq '正在派件（路上）'}">
                            <input type="submit" value="派件签收"><br><br>
                        </c:if>
                        <input type="button" onclick="search('/post/sendFail')" value="取消派件">
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
