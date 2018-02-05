<%--
  Created by IntelliJ IDEA.
  User: PANZHENG
  Date: 2018/1/22
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成像任务</title>
    <script>
        $(document).ready(function () {
            $('.ui.accordion').accordion();
            $('.ui.sticky').sticky({
                context: '#example1',
            });
        });
        $(window).load(function() {
            $('.ui .sticky').sticky('refresh');
        });
    </script>
</head>
<body>

<h2 class="ui header">成像任务</h2>
<div class="ui divider"></div>
<div class="ui internally celled grid" id="example1">
    <div class="twelve wide column">
        <h3 class="ui header">Imaging Task</h3>
        <div class="ui divider"></div>
        <h4 class="ui header">Imaging Parameter</h4>
        <table class="ui olive celled table">
            <thead >
            <tr>
                <th width="30%">Parameter</th>
                <th width="70%">Value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>成像任务ID</td>
                <td>IMG_TASK_20180124</td>
            </tr>
            <tr>
                <td>成像任务名称</td>
                <td>成像任务名称</td>
            </tr>
            <tr>
                <td>卫星名称</td>
                <td>光学A星</td>
            </tr>
            <tr>
                <td>成像模式</td>
                <td>常规推扫</td>
            </tr>
            <tr>
                <td>成像开始时间</td>
                <td>2018-01-24 10:10:10</td>
            </tr>
            <tr>
                <td>成像结束时间</td>
                <td>2018-01-24 10:10:10</td>
            </tr>
            </tbody>
        </table>
        <h4 class="ui header">Target Point</h4>
        <img class="ui image" src="./images/map.png">
        <h3 class="ui header">Receiving Plan</h3>
        <div class="ui divider"></div>
        <h4 class="ui header" id="receivingPlanList">Receiving Plan List</h4>
        <table class="ui selectable blue celled table">
            <thead>
            <tr>
                <th width="5%">No.</th>
                <th width="10%">TrPlanID</th>
                <th width="10%">地面站</th>
                <th width="15%">开始时间</th>
                <th>任务点</th>
                <th width="10%">status</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>1000000123</td>
                <td>长春站</td>
                <td>2018-01-24 10:10:10</td>
                <td>长春站</td>
                <td>等待数传</td>
            </tr>
            </tbody>
        </table>
        <h4 class="ui header">Target Point and Ground Station</h4>
        <img class="ui image" src="./images/map.png">
    </div>
    <div class="four wide column">
        <div class="ui sticky top">
            <h4 class="ui header">成像任务</h4>
            <div class="ui vertical following fluid accordion text menu">
                <div class="item active">
                    <a class="active title"><i class="dropdown icon"></i>
                    <b>Imaging Task</b></a>
                    <div class="active content menu">
                        <a class="item" href="#grids">Imaging Parameter</a>
                        <a class="item" href="#grids">Target Point</a>
                    </div>
                </div>
                <div class="item">
                    <a class="title"><i class="dropdown icon"></i> <b>Receiving Plan</b></a>
                    <div class="content menu">
                        <a class="item" href="#receivingPlanList">Receiving Plan List</a>
                        <a class="item" href="#column-content">Target Point and Ground Station</a>
                </div>
                <div class="item">
                    <a class="title"><i class="dropdown icon"></i> <b>Production</b></a>
                    <div class="content menu">
                        <a class="item" href="#automatic-flow">Production List</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
