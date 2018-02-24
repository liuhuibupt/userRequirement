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
    <title>跟踪接收计划</title>
</head>
<body>

<h2 class="ui header">跟踪接收计划</h2>
<div class="ui divider"></div>
<div class="ui internally celled grid" id="example1">
    <div class="twelve wide column">
        <h4 class="ui header">Receiving Plan Info</h4>
        <table class="ui olive celled table">
            <thead >
            <tr>
                <th width="30%">Parameter</th>
                <th width="70%">Value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>TrPlanId</td>
                <td>${receivingPlan.trPlanId}</td>
            </tr>
            <tr>
                <td>Satellite Name</td>
                <td>${receivingPlan.satelliteName}</td>
            </tr>
            <tr>
                <td>Station Name</td>
                <td>${receivingPlan.stationName}</td>
            </tr>
            <tr>
                <td>成像开始时间</td>
                <td>${receivingPlan.receiveStartTime}</td>
            </tr>
            <tr>
                <td>成像结束时间</td>
                <td>${receivingPlan.receiveStopTime}</td>
            </tr>
            <tr>
                <td>捕获开始时间</td>
                <td>${receivingPlan.satelliteCaptureStartTime}</td>
            </tr>
            <tr>
                <td>捕获结束时间</td>
                <td>${receivingPlan.satelliteCaptureStopTime}</td>
            </tr>
            <tr>
                <td>状态</td>
                <td>${receivingPlan.status}</td>
            </tr>
            </tbody>
        </table>
        <h4 class="ui header">Imaging Task List</h4>
        <div class="ui divider"></div>
        <img class="ui image" src="./images/map.png">
        <table class="ui selectable blue celled table">
            <thead>
            <tr>
                <th width="5%">No.</th>
                <th width="10%">任务ID</th>
                <th width="10%">任务名称</th>
                <th width="15%">成像时间</th>
                <th width="10%">任务状态</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td><a href="#">IMG_TASK_2018_01_12_0001</a></td>
                <td>任务名称</td>
                <td>2018-01-24 10:10:10</td>
                <td>等待数传</td>
            </tr>
            <tr>
                <td>1</td>
                <td><a href="#">IMG_TASK_2018_01_12_0001</a></td>
                <td>任务名称</td>
                <td>2018-01-24 10:10:10</td>
                <td>等待数传</td>
            </tr>
            <tr>
                <td>1</td>
                <td><a href="#">IMG_TASK_2018_01_12_0001</a></td>
                <td>任务名称</td>
                <td>2018-01-24 10:10:10</td>
                <td>等待数传</td>
            </tr>
            <tr>
                <td>1</td>
                <td><a href="#">IMG_TASK_2018_01_12_0001</a></td>
                <td>任务名称</td>
                <td>2018-01-24 10:10:10</td>
                <td>等待数传</td>
            </tr>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
