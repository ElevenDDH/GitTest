<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>addGoods.jsp</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/fresh-bootstrap-table.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
</head>    
<body>
<div class="wrapper">
    <div class="fresh-table full-color-orange full-screen-table" style="height: 760px;">
	<form:form action="adminGoods/addGoods" method="post" modelAttribute="goods" enctype="multipart/form-data">
		<table border=0 style="border-collapse: collapse" class="table">
			<caption>
				<font color="#fff">添加商品</font>
			</caption>
			<tr>
				<td>名称<font color="red">*</font></td>
				<td>
					<font color="black"><form:input path="gname"/></font>
				</td>
			</tr>
			<tr>
				<td>原价<font color="red">*</font></td>
				<td>
					<font color="black"><form:input path="goprice"/></font>
				</td>
			</tr>
			<tr>
				<td>折扣价</td>
				<td>
					<font color="black"><form:input path="grprice"/></font>
				</td>
			</tr>
			<tr>
				<td>库存</td>
				<td>
					<font color="black"><form:input path="gstore"/></font>
				</td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<input type="file" name="logoImage"/>
				</td>
			</tr>
			<tr>
				<td>类型</td>
				<td>
					<font color="black"><form:select path="goodstype_id">
         				<form:options items="${goodsType }" itemLabel="typename" itemValue="id"/>
   					</form:select></font>
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
	</div>
	</div>
</body>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap-table.js"></script>
</html>
