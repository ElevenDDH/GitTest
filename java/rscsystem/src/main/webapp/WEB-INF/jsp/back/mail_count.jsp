<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/26
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>统计邮件</title>
</head>
<body>
<center>
    <h2>统计邮件</h2>
    <h3>按月统计</h3>
    <form action="/back/mail_count" method="post">
        <input type="text" name="currentMonth" list="currentMonthlist">
        <datalist id="currentMonthlist">
            <option>10</option>
            <option>11</option>
            <option>12</option>
        </datalist>
        月
        <input type="submit" value="查询">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}">
    </form>
    <table border="0" cellpadding="10">
        <tr>
            <th style="text-align: center">派件量</th>
            <th style="text-align: center">收件量</th>
            <th style="text-align: center">故障件数</th>
        </tr>

        <tr>
            <%--根据时间判断后再加处理--%>
            <td style="text-align: center">${sessionScope.sendCountMonth}</td>
            <td style="text-align: center">${sessionScope.receiveCountMonth}</td>
            <td style="text-align: center">${sessionScope.failCountMonth}</td>
        </tr>

    </table>

    <h3>按天统计</h3>
    <form action="/back/mail_count" method="post">
        <input type="text" name="currentDay" list="currentDaylist">
        <datalist id="currentDaylist">
            <option>26</option>
            <option>27</option>
            <option>30</option>
        </datalist>
        日
        <input type="submit" value="查询">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}">
    </form>
    <table border="0" cellpadding="10">
        <tr>
            <th style="text-align: center">派件量</th>
            <th style="text-align: center">收件量</th>
            <th style="text-align: center">故障件数</th>
        </tr>

        <tr>
        <%--根据时间判断后再加处理--%>
        <td style="text-align: center">${sessionScope.sendCountDay}</td>
        <td style="text-align: center">${sessionScope.receiveCountDay}</td>
        <td style="text-align: center">${sessionScope.failCountDay}</td>
        </tr>

    </table>

    <h3>详细统计（年月日）</h3>
    <form action="/back/mail_count" method="post">
        <input type="text" name="currentTime" list="currentlist">年
        <datalist id="currentlist">
            <option>2019</option>
            <option>2018</option>
        </datalist>

        <input type="text" name="currentMonth" list="currentMonthlist">月
        <input type="text" name="currentDay" list="currentDaylist">日

        <input type="submit" value="查询">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}">
    </form>
    <table border="0" cellpadding="10">
        <tr>
            <th style="text-align: center">派件量</th>
            <th style="text-align: center">收件量</th>
            <th style="text-align: center">故障件数</th>
        </tr>

        <tr>
            <%--根据时间判断后再加处理--%>
            <td style="text-align: center">${sessionScope.sendCount}</td>
            <td style="text-align: center">${sessionScope.receiveCount}</td>
            <td style="text-align: center">${sessionScope.failCount}</td>
        </tr>

    </table>
</center>
</body>
</html>
