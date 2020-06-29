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
<title>购物车</title>
<script type="text/javascript">
	//确认
	function  goOrderConfirm(){
		window.location.href="/springCD/cart/orderConfirm";
	}
	//清空
	function godelete(){
		if(window.confirm("真的清空购物车吗？")){
			window.location.href="/springCD/cart/clear";
			return true;
		}
		return false;
	}
	//删除
	function deleteAgoods(gno){
		if(window.confirm("真的删除该商品吗？")){
			window.location.href="/springCD/cart/deleteAgoods?id=" + gno;
			return true;
		}
		return false;
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
						<!-- 没有登录 -->
						
					</tr>
				</table>
						</div>
					</div>
				</div>
				<div class="col-md-6 top-header-left">
					<div class="cart box_1">
						<a href="cart/selectCart">
							 <div class="total">
								<span>购物车</span></div>
								<img src="images/cart-1.png" alt="" />
						</a>
						<p><a href="javascript:;" class="simpleCart_empty">清空购物车</a></p>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--top-header-->
	<div class="header-bottom">
		<div class="container">
			<div class="header">
				<div class="col-md-9 header-left">
				<div class="clearfix"> </div>
			</div>
			<div class="col-md-3 header-right"> 	
			</div>
			<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	
	<!--start-breadcrumbs-->
	<div class="breadcrumbs">
		<div class="container">
			<div class="breadcrumbs-main">
				<ol class="breadcrumb">
					<li><a href="before?id=0">首页</a></li>
					<li class="active">购物车</li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	
	<!--start-ckeckout-->
	<div class="ckeckout">
		<div class="container">
			<div class="ckeck-top heading">
				<h2>购物车</h2>
			</div>
			<div class="ckeckout-top">
			<div class="cart-items">
			 <h3>我的购物车</h3>
				
			<div class="in-check" >
				<ul class="unit">
					<li style="padding-left:100px;"><span>商品信息</span></li>
					<li style="padding-left:100px;"><span>单价（元）</span></li>		
					<li style="padding-left:100px;"><span>数量</span></li>
					<li style="padding-left:100px;"><span>操作</span></li>
					
					<div class="clearfix"> </div>
				</ul>
				<c:forEach var="ce" items="${cartlist}" varStatus="status"> 
				<ul class="cart-header">
						<li class="ring-in">
						<a href="goodsDetail?id=${ce.id}" >
						<img src="images/logos/${ce.gpicture}" title="${ce.gname}" class="img-responsive" alt="">
						</a><br/>
						<a href="goodsDetail?id=${ce.id}">
						${ce.gname}
						</a>
						</li>
						<li style="padding-left:100px;"><span class="cost">${ce.grprice}</span></li>
						<li style="padding-left:100px;"><span class="cost">
						<input type="text" name="goods_number" value="${ce.shoppingnum}" size="4" />
						</span></li>
						
						<li style="padding-left:100px;">
						<a style="text-decoration: none;" href="javaScript:deleteAgoods('${ce.id}')"
								 title="删除">
								<span class="name">删除</span>
						</a>
						</li>
					<div class="clearfix"> </div>
				</ul>
				</c:forEach>
				<span>
				<font style="color: #404040; 
				font-size: 20px; font-weight: bold; letter-spacing: 0px;">
				购物金额总计(不含运费) ￥&nbsp;
				<span id="stotal"></span>${total}元
				</font>
				</span>
				
				<span style="float: right;">
				<font style="color: #fff; 
				font-size: 16px; font-weight: bold; letter-spacing: 0px;">
				<a class="add-cart item_add" onclick="godelete()">
				清空购物车
				</a>
				</font>
				</span><br>
				
				<div>
				<font style="color: #fff; 
				font-size: 16px; font-weight: bold; letter-spacing: 0px;">
				<a class="add-cart item_add" onclick="goOrderConfirm()">
				结算
				</a>
				</font>
				</div>
				<div class="clearfix"> </div>
			</div>
			</div>  
		 </div>
		</div>
	</div>
	<!--end-ckeckout-->
	
	<!--information-starts-->
	<div class="information">
		<div class="container">
			<div class="infor-top">
				<div class="col-md-3 infor-left">
					<h3>关注</h3>
					<ul>
						<li><a href="#"><span class="fb"></span><h6>Facebook</h6></a></li>
						<li><a href="#"><span class="twit"></span><h6>Twitter</h6></a></li>
						<li><a href="#"><span class="google"></span><h6>Google+</h6></a></li>
					</ul>
				</div>
				<div class="col-md-3 infor-left">
					<h3>信息</h3>
					<ul>
						<li><a href="#"><p>特价</p></a></li>
						<li><a href="#"><p>新产品</p></a></li>
						<li><a href="#"><p>商店</p></a></li>
						<li><a href="contact.html"><p>联系我们</p></a></li>
						<li><a href="#"><p>畅销产品</p></a></li>
					</ul>
				</div>
				<div class="col-md-3 infor-left">
					<h3>我的帐户</h3>
					<ul>
						<li><a href="userCenter"><p>帐户信息</p></a></li>
						<li><a href="#"><p>信用卡</p></a></li>
						<li><a href="#"><p>退货</p></a></li>
						<li><a href="#"><p>个人信息</p></a></li>
						<li><a href="#"><p>地址</p></a></li>
					</ul>
				</div>
				<div class="col-md-3 infor-left">
					<h3>储存信息</h3>
					<h4>公司名称为：
						<span>Hua Rua Spring</span>
					</h4>
					<h4>电话：</h4>
					<h5>+135 5613 9292</h5>	
					<h4>邮箱：</h4>
					<p><a href="mailto:example@email.com">1010373667@qq.com</a></p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--information-end-->
	<!--footer-starts-->
	<div class="footer">
		<div class="container">
			<div class="footer-top">
				<div class="col-md-6 footer-left">
					<form>
						<input type="text" value="输入您的邮箱即可完成订阅" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Enter Your Email';}">
						<input type="submit" value="订阅">
					</form>
				</div>
				<div class="col-md-6 footer-right">					
					<p>Copyright &copy; 2019.Company name All rights reserved.<a target="_blank" href="http://www.cssmoban.com/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--footer-end-->	
</body>
</html>
