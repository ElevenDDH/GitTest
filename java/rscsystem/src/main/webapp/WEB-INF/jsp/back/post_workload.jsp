<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/2
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮差工作量</title>
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
        <form action="/back/inquirePostWorkload" method="post">
            月份：<input type="text" id="time" name="time"/><br>
            区域地址：<input type="text" id="area" name="area"/><br>
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
                    <script>

                    </script>
                    <script type="text/javascript">
                        var dom = document.getElementById("main");
                        var myChart = echarts.init(dom);
                        var app = {};
                        option = null;
                        app.title = '多 Y 轴示例';
                        var postName=[];
                        var receiveNum=[];
                        var sendNum=[];
                        var workload=[];
                        var maxW=${sessionScope.maxReceive}+${sessionScope.maxSend};

                        for (var i = 1; i <= ${sessionScope.count}; i++){
                            if (i == 1){
                                postName.push("${sessionScope.name1}");
                                receiveNum.push(${sessionScope.receiveNum1});
                                sendNum.push(${sessionScope.sendNum1});
                            }else if (i == 2){
                                postName.push("${sessionScope.name2}");
                                receiveNum.push(${sessionScope.receiveNum2});
                                sendNum.push(${sessionScope.sendNum2});
                            }else if (i == 3) {
                                postName.push("${sessionScope.name3}");
                                receiveNum.push(${sessionScope.receiveNum3});
                                sendNum.push(${sessionScope.sendNum3});
                            }else if (i == 4){
                                postName.push("${sessionScope.name4}");
                                receiveNum.push(${sessionScope.receiveNum4});
                                sendNum.push(${sessionScope.sendNum4});
                            }else if (i == 5){
                                postName.push("${sessionScope.name5}");
                                receiveNum.push(${sessionScope.receiveNum5});
                                sendNum.push(${sessionScope.sendNum5});
                            }else if (i == 6){
                                postName.push("${sessionScope.name6}");
                                receiveNum.push(${sessionScope.receiveNum6});
                                sendNum.push(${sessionScope.sendNum6});
                            }else if (i == 7){
                                postName.push("${sessionScope.name7}");
                                receiveNum.push(${sessionScope.receiveNum7});
                                sendNum.push(${sessionScope.sendNum7});
                            }

                        }

                        for (var i = 0; i < ${sessionScope.count}; i++) {
                            workload[i] = receiveNum[i]+sendNum[i];
                        }

                        <%--for (var i = 1; i <= ${sessionScope.count}; i++) {--%>
                            <%--var nameUse = "name"+i;--%>
                            <%--postName.push(${sessionScope.nameUse});--%>
                            <%--receiveNum.push("${sessionScope.receiveNum}i");--%>
                            <%--sendNum.push(${sessionScope.sendNum}i);--%>
                            <%--console.log("postName", ${sessionScope.name}i);--%>
                        <%--}--%>
                        <%--console.log("postName", postName);--%>

                        var colors = ['#20ba19', '#2bb8d1', '#ba2d04'];

                        option = {
                            color: colors,

                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'cross'
                                }
                            },
                            grid: {
                                right: '20%'
                            },
                            toolbox: {
                                feature: {
                                    dataView: {show: true, readOnly: false},
                                    restore: {show: true},
                                    saveAsImage: {show: true}
                                }
                            },
                            legend: {
                                data:['收件数','派件数','工作量']
                            },
                            xAxis: [
                                {
                                    type: 'category',
                                    axisTick: {
                                        alignWithLabel: true
                                    },
                                    data: postName
                                }
                            ],
                            yAxis: [
                                {
                                    type: 'value',
                                    name: '收件数',
                                    min: 0,
                                    max: ${sessionScope.maxReceive},
                                    position: 'right',
                                    axisLine: {
                                        lineStyle: {
                                            color: colors[0]
                                        }
                                    },
                                    axisLabel: {
                                        formatter: '{value} 件'
                                    }
                                },
                                {
                                    type: 'value',
                                    name: '派件数',
                                    min: 0,
                                    max: ${sessionScope.maxSend},
                                    position: 'right',
                                    offset: 80,
                                    axisLine: {
                                        lineStyle: {
                                            color: colors[1]
                                        }
                                    },
                                    axisLabel: {
                                        formatter: '{value} 件'
                                    }
                                },
                                {
                                    type: 'value',
                                    name: '工作量',
                                    min: 0,
                                    max: maxW,
                                    position: 'left',
                                    axisLine: {
                                        lineStyle: {
                                            color: colors[2]
                                        }
                                    },
                                    axisLabel: {
                                        formatter: '{value} 件'
                                    }
                                }
                            ],
                            series: [
                                {
                                    name:'收件数',
                                    type:'bar',
                                    data:receiveNum
                                },
                                {
                                    name:'派件数',
                                    type:'bar',
                                    yAxisIndex: 1,
                                    data:sendNum
                                },
                                {
                                    name:'工作量',
                                    type:'line',
                                    yAxisIndex: 2,
                                    data:workload
                                }
                            ]
                        };
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
