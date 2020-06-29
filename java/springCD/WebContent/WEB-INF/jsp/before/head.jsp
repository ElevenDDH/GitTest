<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>首页</title>
<script type="text/javascript">
	function clearValue(){
		document.myForm.mykey.value = "";
	}
</script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
<!--jQuery(necessary for Bootstrap's JavaScript plugins)-->
<script src="js/jquery-1.11.0.min.js"></script>
<!--Custom-Theme-files-->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css"/>	
<link href="css/styleFront.css" rel="stylesheet" type="text/css"/>
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Luxury Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--start-menu-->
<script src="js/simpleCart.min.js"> </script>
<link href="css/memenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/memenu.js"></script>
<script>$(document).ready(function(){$(".memenu").memenu();});</script>	
<!--dropdown-->
<script src="js/jquery.easydropdown.js"></script>
<script type="text/javascript">
		function openNotice(url){
			window.open (url, '站内公告', 'height=400, width=400, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
		}
		function clearValue(){
			document.myForm.mykey.value = "";
		}
		function godelete(){
			if(window.confirm("真的清空购物车吗？")){
				window.location.href="/springCD/cart/clear";
				return true;
			}
			return false;
		}
		
</script>
</head>
<body>
	<!--top-header-->
	<div class="top-header">
		<div class="container">
			<div class="top-header-main">
				<div class="col-md-6 top-header-left">
					<div class="drop">
						<div class="clearfix">
						<table border="0" cellspacing="0" cellpadding="0" class="main_login">
					<tr>		
						<c:if test="${bruser!=null}">
							<td width="130"><font color="#fff">欢迎 ${bruser.bemail }</font></td>
						</c:if>
						<c:if test="${bruser==null}">
							<td width="50"><font color="#fff"><a href="toLogin">登录</font></a></td>
						</c:if>
								
						<c:if test="${bruser!=null}">
							<td width="50"><font color="#fff"><a href="user/exit">退出</a></font></td>
						</c:if>
						<c:if test="${bruser==null}">
							<td width="50"><font color="#fff"><a href="toRegister">注册</a></font></td>
						</c:if>
						
						<td width="20"><font color="#fff">
						<span class="xx">|</span>
						</font></td>
						<td width="70"><font color="#fff">
						<a href="userCenter">用户中心</a>
						</font></td>
					</tr>
				</table>
						</div>
					</div>
				</div>
				<div class="col-md-6 top-header-left">
					<div class="cart box_1">
						<a href="cart/selectCart">
							 <div class="total">
								<span>购物车</span>
								</div>
								<img src="images/cart-1.png" alt="" />
						</a>
						<p><a href="javascript:godelete();" class="simpleCart_empty">清空购物车</a></p>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--top-header-->
	<!--start-logo-->
	<div class="logo" style="overflow: auto;">
		<span><a href="before?id=0"><h1>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<font size="9" style="font-family: 华文行楷">电商网站</font></h1></a></span>
		<span style="float: right; border:2px solid #404040;">
		<div>
		<font style="font-family: 华文行楷" size="6">公告栏&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
		</div>
		<div class="post_list ared">
		<ul>
		<c:forEach items="${noticelist}" var="nt">
		<li><a href="/springCD/selectANotice?id=${nt.id }">
		<font size="8">.</font>
		${nt.ntitle }
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</a></li>
		</c:forEach>
		</ul>
		</div>					
		</span>
		
	</div>
	<!--start-logo-->
	<!--bottom-header-->
	<div class="header-bottom">
		<div class="container">
			<div class="header">
				<div class="col-md-9 header-left">
				<div class="top-nav">
					<ul class="memenu skyblue">
					<li class="active"><a href="before?id=0">首页</a></li>
					<!-- 显示商品类型 -->
							<c:forEach items="${goodsType}" var="g">
								<li class="grid">
								<a href="selectGoodsByType?id=${g.id }">${g.typename }</a>
								<div class="mepanel">
								<div class="row">
									<div class="col1 me-one">
										<h4>店家</h4>
										<ul>
											<li><a href="products.html">New Arrivals</a></li>
											<li><a href="products.html">Blazers</a></li>
											<li><a href="products.html">Swem Wear</a></li>
											<li><a href="products.html">Accessories</a></li>
											<li><a href="products.html">Handbags</a></li>
											<li><a href="products.html">T-Shirts</a></li>
											<li><a href="products.html">Watches</a></li>
											<li><a href="products.html">My Shopping Bag</a></li>
										</ul>
									</div>
									<div class="col1 me-one">
										<h4>风格</h4>
										<ul>
											<li><a href="products.html">Shoes</a></li>
											<li><a href="products.html">Watches</a></li>
											<li><a href="products.html">Brands</a></li>
											<li><a href="products.html">Coats</a></li>
											<li><a href="products.html">Accessories</a></li>
											<li><a href="products.html">Trousers</a></li>
										</ul>	
									</div>
									<div class="col1 me-one">
										<h4>热门品牌</h4>
										<ul>
											<li><a href="products.html">499 Store</a></li>
											<li><a href="products.html">Fastrack</a></li>
											<li><a href="products.html">Casio</a></li>
											<li><a href="products.html">Fossil</a></li>
											<li><a href="products.html">Maxima</a></li>
											<li><a href="products.html">Timex</a></li>
											<li><a href="products.html">TomTom</a></li>
											<li><a href="products.html">Titan</a></li>
										</ul>		
									</div>
								</div>
							</div>
								</li>
							</c:forEach>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="col-md-3 header-right"> 
				<div class="search-bar">
				<form action="search" name="myForm" method="post">
				
					<input type="text" name="mykey" value="请输入您要查询的内容" 
					onfocus="clearValue()" 
					onBlur="if (this.value == '') {this.value = 'Search';}">
					
					<input type="submit" value="">
					</form>
				</div>
			</div>
			<div class="clearfix"> </div>
			</div>
		</div>
	</div>
</body>
</html>
