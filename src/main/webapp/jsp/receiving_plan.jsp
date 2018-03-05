<%--
  Created by IntelliJ IDEA.
  User: PANZHENG
  Date: 2018/1/22
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <th width="7%">No.</th>
                <th width="10%">TASK ID</th>
                <th>需求名称</th>
                <th width="10%">SATELLITE</th>
                <th width="18%">START TIME</th>
                <th width="10%">More Detail</th>
                <th width="7%">状态</th>
            </tr>
            </thead>
            <tbody><c:forEach items="${imagingTaskList}" var="imagingTask">
            <tr>
                <td>${imagingTask.id}</td>
                <td><a href="imagingTask?imagingTaskId=${imagingTask.id}">${imagingTask.otTaskId}</a></td>
                <td>${imagingTask.requestName}</td>
                <td>${imagingTask.satelliteId}</td>
                <td>${imagingTask.imagingStart}</td>
                <td>MORE</td>
                <td>${imagingTask.status}</td>
            </tr></c:forEach>
        </table>
    </div>

</div>
</body>
</html>
