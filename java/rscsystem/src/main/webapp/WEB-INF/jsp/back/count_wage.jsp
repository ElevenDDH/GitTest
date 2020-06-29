<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/2
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>工资结算</title>
</head>
<body>
<center>
    <h2>结算工资</h2>
    <a href="/back/countAllWageSuccess"><button>全部结算</button></a>
    <table border="0" cellpadding="10">
        <tr>
            <th style="text-align: center">邮差编号</th>
            <th style="text-align: center">邮差</th>
            <th style="text-align: center">邮差所属地</th>
            <th style="text-align: center">工资</th>
            <th style="text-align: center">操作</th>
        </tr>
        <c:forEach items="${sessionScope.postmans}" var="postmans">
            <tr>
                <form name="form1" action="/back/countWageSuccess" method="post">
                    <td style="text-align: center">
                        <input type="hidden" name="postman_id" value="${postmans.postman_id}">
                            ${postmans.postman_id}
                    </td>
                    <td style="text-align: center">
                        <input type="hidden" name="postman_account" value="${postmans.postman_account}">
                            ${postmans.postman_account}
                    </td>
                    <td style="text-align: center">${postmans.postman_area}</td>
                    <td style="text-align: center">${postmans.wage}</td>
                    <td style="text-align: center">
                        <input type="submit" value="结算工资"><br><br>
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
