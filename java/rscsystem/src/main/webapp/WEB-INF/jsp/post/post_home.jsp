<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/29
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2>欢迎${sessionScope.post.postman_account}</h2>
    <c:if test="${sessionScope.post.leave == 0}">
        <a href="/post/post_receive"><button>收件</button></a><br><br>
        <a href="/post/post_send"><button>派件</button></a><br><br>
        <a href="/post/leave"><button>请假</button></a><br><br>
    </c:if>
    <c:if test="${sessionScope.post.leave == 1}">
        <a href="/post/cancelLeave"><button>取消请假</button></a><br><br>
    </c:if>

</center>
</body>
</html>
