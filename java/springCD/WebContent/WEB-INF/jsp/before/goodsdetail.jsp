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
<title>商品详情</title>
<script type="text/javascript">
	function goCart() {
		document.putcartform.submit();
	}
	function gofocus(gno) {
		window.location.href = "/springCD/cart/focus?id=" + gno;
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
	$(function() {
	    var menu_ul = $('.menu_drop > li > ul'),
	           menu_a  = $('.menu_drop > li > a');
	    
	    menu_ul.hide();
	
	    menu_a.click(function(e) {
	        e.preventDefault();
	        if(!$(this).hasClass('active')) {
	            menu_a.removeClass('active');
	            menu_ul.filter(':visible').slideUp('normal');
	            $(this).addClass('active').next().stop(true,true).slideDown('normal');
	        } else {
	            $(this).removeClass('active');
	            $(this).next().stop(true,true).slideUp('normal');
	        }
	    });
	
	});
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
					<li class="active">商品详情</li>
				</ol>
			</div>
		</div>
	</div>
	<!--end-breadcrumbs-->
	
	
	<form action="cart/putCart" name="putcartform" method="post">
		<!--start-single-->
	<div class="single contact">
		<div class="container">
			<div class="single-main">
				<div class="col-md-9 single-main-left">
				<div class="sngl-top">
					<div class="col-md-5 single-top-left">	
						<div class="flexslider">
							  <ul class="slides">
								<li data-thumb="images/s-1.jpg">
									<div class="thumb-image"> 
									<input type="hidden" name="id" value="${goods.id }"/>
									<img src="images/logos/${goods.gpicture}" data-imagezoom="true" class="img-responsive" alt=""/>
									 </div>
								</li>
							  </ul>
						</div>
						<!-- FlexSlider -->
						<script src="js/imagezoom.js"></script>
						<script defer src="js/jquery.flexslider.js"></script>
						<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />

						<script>
						// Can also be used with $(document).ready()
						$(window).load(function() {
						  $('.flexslider').flexslider({
							animation: "slide",
							controlNav: "thumbnails"
						  });
						});
						</script>
					</div>	
					<div class="col-md-7 single-top-right">
						<div class="single-para simpleCart_shelfItem">
						<h2>${goods.gname }</h2>
							<div class="star-on">
							<div class="clearfix"> </div>
							</div>
							<ul>
										<li class="item_price" style="padding:4px 8px;border-top:5px;"><span>价格:</span> <strong class="yj">${goods.goprice }元</strong></li>
										<li style="padding:4px 8px;border-top:5px;"><span>折扣价:</span><strong>${goods.grprice }元</strong></li>
										<li style="padding:4px 8px;border-top:5px;"><span>类型:</span> ${goods.typename }</li>
										<li style="padding:4px 8px;border-top:5px;"><span>购买数量:</span><input type="text" name="shoppingnum"
											 value="1" /> (库存${goods.gstore }件)</li>
									</ul>
							
								<a onclick="goCart()" class="add-cart item_add">加入购物车</a>
								&nbsp;&nbsp;
								<a onclick="gofocus('${goods.id }')" class="add-cart item_add">关注</a>
								${msg }
						</div>
					</div>
					<div class="clearfix"> </div>
				</div>
				
				<div class="latestproducts">
					<div class="product-one">
						<div class="col-md-4 product-left p-left"> 
							<div class="product-main simpleCart_shelfItem">
								<a href="single.html" class="mask"><img class="img-responsive zoom-img" src="images/p-1.png" alt="" /></a>
								<div class="product-bottom">
									<h3>Smart Watches</h3>
									<p>Explore Now</p>
									<h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">$ 329</span></h4>
								</div>
								<div class="srch">
									<span>-50%</span>
								</div>
							</div>
						</div>
						<div class="col-md-4 product-left p-left"> 
							<div class="product-main simpleCart_shelfItem">
								<a href="single.html" class="mask"><img class="img-responsive zoom-img" src="images/p-2.png" alt="" /></a>
								<div class="product-bottom">
									<h3>Smart Watches</h3>
									<p>Explore Now</p>
									<h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">$ 329</span></h4>
								</div>
								<div class="srch">
									<span>-50%</span>
								</div>
							</div>
						</div>
						<div class="col-md-4 product-left p-left"> 
							<div class="product-main simpleCart_shelfItem">
								<a href="single.html" class="mask"><img class="img-responsive zoom-img" src="images/p-3.png" alt="" /></a>
								<div class="product-bottom">
									<h3>Smart Watches</h3>
									<p>Explore Now</p>
									<h4><a class="item_add" href="#"><i></i></a> <span class=" item_price">$ 329</span></h4>
								</div>
								<div class="srch">
									<span>-50%</span>
								</div>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!--end-single--> 
	</form>
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
