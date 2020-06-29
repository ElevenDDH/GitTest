<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="head.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>电商网站</title>
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
</script>			
</head>
<body> 
	<!--bottom-header-->
	<!--横幅-starts-->
	<div class="bnr" id="home">
		<div  id="top" class="callbacks_container">
			<ul class="rslides" id="slider4">
			    <li>
					<div class="banner">
					</div>
				</li>
				<li>
					<div class="banner1">
					</div>
				</li>
				<li>
					<div class="banner2">
					</div>
				</li>
			</ul>
		</div>
		<div class="clearfix"> </div>
	</div>
	<!--横幅-ends-->
    
	<!--轮播-Starts--->
				<script src="js/responsiveslides.min.js"></script>
			 <script>
			    // You can also use "$(window).load(function() {"
			    $(function () {
			      // Slideshow 4
			      $("#slider4").responsiveSlides({
			        auto: true,
			        pager: true,
			        nav: true,
			        speed: 500,
			        namespace: "callbacks",
			        before: function () {
			          $('.events').append("<li>before event fired.</li>");
			        },
			        after: function () {
			          $('.events').append("<li>after event fired.</li>");
			        }
			      });
			
			    });
			  </script>
			<!--End-轮播-script-->
	<!--about-starts-->
	<div class="about"> 
	<div class="top-header">
	<div class="container">
		<span>
		<font color="#fff"><b>热销商品&nbsp;&nbsp;&nbsp;&nbsp;</b>
		<span class="xx"><b>|&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
		<b>TOP 3</b></font>
		</span>
		<span style="float:right;"><font color="#fff"><a href="#">更多 >></a></font></span>
		</div>
		</div>
<!-- 		销售排行 -->
		<div class="container">
			<div class="about-top grid-1">
			<c:forEach items="${salelist }" var="sg" varStatus="status">
				<div class="col-md-4 about-left">
					<figure class="effect-bubba">
						<a href="goodsDetail?id=${sg.id }"> 
						<img class="img-responsive" src="images/logos/${sg.gpicture}" alt=""/>
						<figcaption>
							<h2>${sg.gname }</h2>
							<p>售价：￥${sg.grprice }元</p>	
						</figcaption>			
						</a>
					</figure>
				</div>
			</c:forEach>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--about-end-->
	
	<!--product-starts-->
	<div class="product">
	<div class="about"> 
		<div class="top-header">
		<div class="container">
			<span>
		<font color="#fff"><b>最新商品&nbsp;&nbsp;&nbsp;&nbsp;</b>
		<span class="xx"><b>|&nbsp;&nbsp;&nbsp;&nbsp;</b></span>
		<b>TOP 9</b></font>
		</span>
		<span style="float:right;"><font color="#fff"><a href="#">更多 &gt;&gt;</a></font></span>
			</div>
			</div>
			</div> 	
			<div class="container">
			<div class="product-top">
				<div class="product-one">
				<c:forEach items="${lastedlist }" var="sg" varStatus="status">
					<div class="col-md-3 product-left">
						<div class="product-main simpleCart_shelfItem" style="width: 210px; height: 400px;">
							<br>
							<a href="goodsDetail?id=${sg.id }" class="mask">
							<img class="img-responsive zoom-img" src="images/logos/${sg.gpicture}" alt="" />
							</a>
							<div class="product-bottom">
								<h3>${sg.gname }</h3>
								<h4><a class="item_add" href="goodsDetail?id=${sg.id }"><i></i></a> 
								<span class="item_price">￥${sg.grprice}</span></h4>
							</div>
							<div class="srch">
								<span>New</span>
							</div>
						</div>
					</div>
					<c:if test="${status.count } == 4">
					<div class="clearfix"></div>
					</c:if>
					</c:forEach>
				</div>					
			</div>
		</div>
	</div>
	<!--product-end-->
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