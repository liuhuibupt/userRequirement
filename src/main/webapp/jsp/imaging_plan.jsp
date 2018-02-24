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
    <title>拍摄计划</title>
</head>
<body>

<h2 class="ui header">Imaging Plan</h2>
<div class="ui divider"></div>
<div class="ui internally celled grid">
    <div class="twelve wide column">
        <h4 class="ui header">User Request</h4>
        <table class="ui olive celled table">
            <thead >
            <tr>
                <th width="30%">Parameter</th>
                <th width="70%">Value</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>需求ID</td>
                <td>${userRequest.requestId}</td>
            </tr>
            <tr>
                <td>需求名称</td>
                <td>${userRequest.requestName}</td>
            </tr>
            <tr>
                <td>Satellite</td>
                <td>${userRequest.requestSatellites}</td>
            </tr>
            <tr>
                <td>Submitter</td>
                <td>${userRequest.submitter.displayName}</td>
            </tr>
            <tr>
                <td>需求开始时间</td>
                <td>${userRequest.requestStart}</td>
            </tr>
            <tr>
                <td>需求结束时间</td>
                <td>${userRequest.requestEnd}</td>
            </tr>
            <tr>
                <td>需求状态</td>
                <td>${userRequest.status}</td>
            </tr>
            </tbody>
        </table>
        <h4 class="ui header">Imaging Plan List</h4>
        <div class="ui divider"></div>
        <img class="ui image" src="./images/map.png">
        <table class="ui celled striped table">
            <thead>
            <tr>
                <th colspan="7">
                    <div class="ui grid">
                        <div class="six column row">
                            <div class="left floated column">
                                <div class="ui label">
                                    <i class="list icon"></i>
                                    共检索123条记录
                                </div>
                            </div>

                        </div>
                    </div>
                </th>
            </tr>
            <tr>
                <th width="10%">No.</th>
                <th width="15%">Plan ID</th>
                <th width="15%">Satellites</th>
                <th width="15%">PLAN START</th>
                <th width="10%">成像任务</th>
                <th width="10%">状态</th>
            </tr>
            </thead>
            <tbody><c:forEach items="${imagingPlanList}" var="imagingPlan">
            <tr>
                <td>${imagingPlan.id}</td>
                <td>${imagingPlan.planId}</td>
                <td>${imagingPlan.requestSatellites}</td>
                <td>${imagingPlan.planStartTime}</td>
                <td><a href="#">LINK</a></td>
                <td>${imagingPlan.status}</td>
            </tr></c:forEach>
            <tfoot>
            </tfoot>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
