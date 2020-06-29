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
    <meta charset="utf-8" />
    <title>Backstage management</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="css/style-metro.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/style-responsive.css" rel="stylesheet" type="text/css" />
    <link href="css/default.css" rel="stylesheet" type="text/css" id="style_color" />
    <link href="css/uniform.default.css" rel="stylesheet" type="text/css" />
    <!-- mask alert -->
    <link href="css/jquery-ui-1.10.1.custom.min.css" rel="stylesheet" type="text/css" />
    <link href="css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
    <!-- mask alert -->
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- END COPYRIGHT -->
    <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
    <!-- BEGIN CORE PLUGINS -->
    <script src="js/jquery-1.10.1.min.js" type="text/javascript"></script>
    <script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
    <script src="js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <!--[if lt IE 9]>

    <script src="js/excanvas.min.js"></script>

    <script src="js/respond.min.js"></script>  

    <![endif]-->
    <script src="js/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="js/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="js/jquery.cookie.min.js" type="text/javascript"></script>
    <script src="js/jquery.uniform.min.js" type="text/javascript"></script>
    <!-- END CORE PLUGINS -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="js/jquery.backstretch.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="js/app.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL SCRIPTS -->
    <script src="js/bootstrap-modal.js" type="text/javascript"></script>
    <script src="js/bootstrap-modalmanager.js" type="text/javascript"></script>
    <script src="js/ui-modals.js"></script>
</head>
<!-- END HEAD -->
<!-- BEGIN PAGE LEVEL STYLES -->
<!-- color引用 -->
<link rel="stylesheet" href="css/colpick.css" type="text/css" />
<link rel="stylesheet" href="css/website.css" type="text/css" />
<link rel="stylesheet" href="css/swiper.min.css" type="text/css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN BODY -->
<style>
.setbg input[type="text"],
.setcol .yu_bgcol {
    background-color: initial!important;
}
</style>

<body class="page-header-fixed">
    <!-- BEGIN HEADER -->
    <div class="header navbar navbar-inverse navbar-fixed-top">
        <!-- BEGIN TOP NAVIGATION BAR -->
        <div class="navbar-inner">
            <div class="container-fluid">
                <!-- BEGIN LOGO -->
<!--                 <a class="brand" href="index.html"> -->
<!--                     <img src="images/logo.png" alt="logo" /> -->
<!--                 </a> -->
                <h3 class="brand"><b><center>欢迎您，${auser.aname}</center></b></h3>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                    <img src="images/menu-toggler.png" alt="" />
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <ul class="nav pull-right">
                    <!-- BEGIN NOTIFICATION DROPDOWN -->
                    <li class="dropdown" id="header_notification_bar">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-warning-sign"></i>
<!--                             <span class="badge">0</span> -->
                        </a>
                        <ul class="dropdown-menu extended notification">
                            <li>
                                <p>其他服务</p>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-success"><i class="icon-plus"></i></span> 功能介绍
                                    <span class="time"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-important"><i class="icon-bolt"></i></span> 使用指南
                                    <span class="time"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!-- END NOTIFICATION DROPDOWN -->
                    <!-- BEGIN USER LOGIN DROPDOWN -->
                    <li class="dropdown user">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img alt="" src="images/timg.jpg" />
                            <span class="username"></span>
                            <i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
<!--                             <li><a href=""><i class="icon-lock"></i> 修改密码</a></li> -->
                            <li><a href="exit"><i class="icon-key"></i> 安全退出</a></li>
                        </ul>
                    </li>
                    <!-- END USER LOGIN DROPDOWN -->
                </ul>
                <!-- END TOP NAVIGATION MENU -->
            </div>
        </div>
        <!-- END TOP NAVIGATION BAR -->
    </div>
    <!-- End HEADER -->
    <!-- BEGIN CONTAINER -->
    <div class="page-container row-fluid">
        <!-- BEGIN SIDEBAR -->
        <div class="page-sidebar nav-collapse collapse">
            <!-- Begin SIDEBAR MENU -->
            <!-- BEGIN SIDEBAR MENU -->
            <ul class="page-sidebar-menu">
                <li>
                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                    <div class="sidebar-toggler hidden-phone"><img src="images/sidebar-toggler.jpg"/></div>
                    <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                </li>
                <li class="start">
                    <a href="">
                        <i class=" icon-reorder"></i>
                        <span class="title">菜单</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-comments"></i>
                        <span class="title">商品管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="adminGoods/toAddGoods" target="center">添加商品</a>
                            <a href="adminGoods/selectGoods?act=deleteSelect" target="center">删除商品</a>
                            <a href="adminGoods/selectGoods?act=updateSelect" target="center">修改商品</a>
                            <a href="adminGoods/selectGoods" target="center">查询商品</a>
                        </li>
                    </ul>
                </li>
                <!--  <li><a href="">360全景</a></li> -->
                <li>
                    <a href="javascript:;">
                        <i class="icon-bullhorn"></i>
                        <span class="title">类型管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="adminType/toAddType" target="center">添加类型</a>
                            <a href="adminType/toDeleteType" target="center">删除类型</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-picture"></i>
                        <span class="title">用户管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="adminUser/userInfo" target="center">删除用户</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-credit-card"></i>
                        <span class="title">订单管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="adminOrder/orderInfo" target="center">删除订单</a> 
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:;">
                        <i class="icon-book"></i>
                        <span class="title">公告管理</span>
                        <span class="arrow "></span>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="adminNotice/toAddNotice" target="center">添加公告</a>
                            <a href="adminNotice/deleteNoticeSelect" target="center">删除公告</a>
                        </li>
                    </ul>
                </li>
            </ul>
            
            <!-- END SIDEBAR MENU -->
        </div>
        <!-- END SIDEBAR -->
        <!-- BEGIN PAGE -->
        <div class="page-content">
            <div style="width: 100%;height:760px;">
				<iframe  src="adminGoods/selectGoods" name="center" frameborder="0"
				 allowfullscreen="true"  style="width: 100%;height: 100%;">
				</iframe>
			</div>
        </div>
        <!-- end PAGE -->
        <!-- **** -->
        <!-- **** -->
        <!-- === -->
        
        <!-- === -->
    </div>
    <!-- END CONTAINER -->
    <!-- BEGIN FOOTER -->
    <!-- END FOOTER -->
    <script type="text/javascript" src="js/yy_card.js"></script>
    <script src="js/colpick.js"></script>
    <script src="js/plugin.js"></script>
    <script src="js/website.js"></script>
    <script src="js/jquery.upload.js"></script>
    <!--     <script src="js/jquery.rotate.min.js"></script> -->
    <script src="js/swiper.min.js"></script>
    <script>
    jQuery(document).ready(function() {
        App.init();
        UIModals.init();
        // var swiper = new Swiper('.swiper-container');
        $('.datainfo .span12,.loading').hide();
    });


    
    //保存

    function savewebsite() {

        var content = $(".dome_exp").html(); //后台元素    
        $("#website .maininfo img").attr('ondblclick', '');
        $('.ui-widget-header,.ui-resizable-handle').remove();
        $('.datahref :text').attr("disabled", "disabled");

        var web = $("#website").html(); //前台展示
        $.ajax({
            url: "{:url('user/website/savewebsite')}",
            type: 'POST',
            data: {
                "web": web,
                'con': content
            },
            dataType: "json",
            success: function(data) {
                if (data == 1) {
                    alert("提示", "保存成功！")
                } else {
                    alert("提示", "保存失败！")
                }
                setTimeout(function() {
                    location = location;
                }, 8000);
            }
        });
    }

    function keepdomafter() {
        play();

        $('.loading').hide();
        $('.ui-resizable-se').nextAll().remove();
        var swiper = new Swiper('.swiper-container', {
            // pagination: '.swiper-pagination',
            // paginationClickable: true,
            // nextButton: '.swiper-button-next',
            // prevButton: '.swiper-button-prev',
            // spaceBetween: 30,
            // effect: 'fade',
            // grabCursor: true,
            // autoplay: 2000,
        });

    }
    </script>
    <!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->

</html>
