<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/28
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>待审核邮差</title>
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
    <h2>待审核邮差</h2>
    <table border="0" cellpadding="10">
        <tr>
            <th style="text-align: center">邮差编号</th>
            <th style="text-align: center">邮差账户</th>
            <th style="text-align: center">邮差所属地</th>
            <th style="text-align: center">操作</th>
        </tr>

        <c:forEach items="${sessionScope.postmen}" var="postmen">
            <c:if test="${postmen.active eq '0'}">
                <tr>
                    <form name="form1" action="/back/post_agree" method="post">
                        <td style="text-align: center"><input type="text" name="postman_id" value="${postmen.postman_id}" readonly="readonly"></td>
                        <td style="text-align: center"><input type="text" name="postman_account" value="${postmen.postman_account}" readonly="readonly"></td>
                        <td style="text-align: center"><input type="text" name="postman_area" value="${postmen.postman_area}" readonly="readonly"></td>
                            <%--<td style="text-align: center">${postmen.active}</td>--%>
                        <td style="text-align: center">
                            <input type="submit" value="同意"><br><br>
                            <input type="button" onclick="search('/back/post_refuse')" value="拒绝">
                        </td>

                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}">
                    </form>
                </tr>
            </c:if>
        </c:forEach>

    </table>
</center>
</body>
</html>
