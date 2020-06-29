<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>updateAgoods.jsp</title>
<link href="css/admin/common.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/fresh-bootstrap-table.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
</head>    
<body>
<div class="wrapper">
    <div class="fresh-table full-color-orange full-screen-table" style="height: 760px;">
	<form:form action="adminGoods/addGoods?updateAct=update" method="post" modelAttribute="goods" enctype="multipart/form-data">
		<table border=0 style="border-collapse: collapse" class="table">
			<caption>
				<font size=6 face=华文新魏 color="#000">修改商品</font>
				<form:hidden path="id"/>
			</caption>
			<tr>
				<td>名称<font color="red">*</font></td>
				<td>
					<font color="#000"><form:input path="gname"/></font>
				</td>
			</tr>
			<tr>
				<td>原价<font color="red">*</font></td>
				<td>
					<font color="#000"><form:input path="goprice"/></font>
				</td>
			</tr>
			<tr>
				<td>折扣价</td>
				<td>
					<font color="#000"><form:input path="grprice"/></font>
				</td>
			</tr>
			<tr>
				<td>库存</td>
				<td>
					<font color="#000"><form:input path="gstore"/></font>
				</td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<input type="file" name="logoImage"/>
					<br>
					<!-- 从数据库取出的文件名 -->
					<c:if test="${goods.gpicture != ''}">
						<img alt="" width="50" height="50"
						src="logos/${goods.gpicture}"/>
					</c:if>	
				</td>
			</tr>
			<tr>
				<td>类型</td>
				<td>
				<font color="#000">
					<form:select path="goodstype_id">
         				<form:options items="${goodsType }" itemLabel="typename" itemValue="id"/>
   					</form:select>
   					</font>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="submit" value="提交" class="btn btn-default"/>
				</td>
				<td align="left">
					<input type="reset" value="重置" class="btn btn-default"/>
				</td>
			</tr>
		</table>
	</form:form>
	${msg }
	</div>
	</div>
</body>
</html>
