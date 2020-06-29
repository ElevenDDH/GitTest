<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/4
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>邮差工作情况</title>
    <meta name="_csrf" th:content="${_csrf.token}"/>
    <meta name="_csrf_header" th:content="${_csrf.headerName}"/>

    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="/easyui/demo/demo.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.0.js"></script>
    <script src="/js/echarts.js"></script>
    <script type="text/javascript" src="/echarts/echarts.common.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>

    <script>
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    </script>

    <script>
        $(function(){
            $('#tt1').tabs('resize',{
                plain : false,
                boder : false,
                width:$(window).width()-18,
                height: $(window).height()-18,
            });
        });
    </script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="width: 100%;height: 14%">
        <form action="/back/findByPostState" method="post">
            月份：<input type="text" id="savetime" name="savetime"/><br>
            邮差：<input type="text" name="postman_account" list="postmanlist"/>
            <datalist id="postmanlist">
                <c:forEach items="${sessionScope.postmen}" var="p">
                    <option>${p.postman_account}</option>
                </c:forEach>
            </datalist>
            <br>
            <input type="submit" value="查询">

            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>
    </div>

    <div data-options="region:'center'" style="width: 100%;height: 60%">
        <div id="tt1" class="easyui-tabs" tabHeight="35">

            <div title="统计" >
                <table>
                    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                    <div id="main" style="height: 500px"></div>
                    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
                    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
                    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
                    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
                    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
                    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
                    <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=xfhhaTThl11qYVrqLZii6w8qE5ggnhrY&__ec_v__=20190126"></script>
                    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
                    <script type="text/javascript">
                        var dom = document.getElementById("main");
                        var myChart = echarts.init(dom);
                        var app = {};
                        var textName = "${sessionScope.postmanWorkstate.postman_account} ${sessionScope.month} 月的工作情况";
                        option = null;
                        option = {
                            title: {
                                text: textName,
                                x:'center',
                                textStyle: {
                                    color: '#707070'
                                }
                            },

                            tooltip : {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            color:['#0dcc00', '#ebe80a','#f5321a'],
                            legend: {
                                orient: 'vertical',
                                left: 'left',
                                textStyle:{
                                    color: '#707070'
                                },

                                data: ['在岗天数','请假天数','加班天数']
                            },
                            series : [
                                {
                                    name:'访问来源',
                                    type:'pie',
                                    radius : '55%',
                                    center: ['50%', '50%'],
                                    data:[
                                        {value:${sessionScope.postmanWorkstate.worktime}, name:'在岗天数'},
                                        {value:${sessionScope.postmanWorkstate.leavetime}, name:'请假天数'},
                                        {value:${sessionScope.postmanWorkstate.overtime}, name:'加班天数'}
                                    ],
                                    itemStyle: {
                                        emphasis: {
                                            shadowBlur: 200,
                                            shadowOffsetX: 0,
                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                    },

                                    animationType: 'scale',
                                    animationEasing: 'elasticOut',
                                    animationDelay: function (idx) {
                                        return Math.random() * 200;
                                    }
                                }
                            ]
                        };;
                        if (option && typeof option === "object") {
                            myChart.setOption(option, true);
                        }
                    </script>
                    <!-- ECharts单文件引入 -->
                </table>

            </div>
        </div>
    </div>
</div>

</body>
</html>
