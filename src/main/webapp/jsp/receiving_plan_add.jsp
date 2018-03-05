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
    <title>Receiving Plan</title>
</head>
<body>
<h2 class="ui header">Test Adding Receiving Plan </h2>
<div class="ui divider"></div>
<form class="ui form" method="post" action="submitReceivingPlan" style="margin-top: 0.5rem">
    <div class="ui error message">
    </div>
    <div class="two fields">
        <div class="field">
            <label>Input Receiving Plan JSON</label>
            <textarea id="json" name="json" rows="20">{
    trPlanId:"trPlanId",
    satelliteId:"JL101A",
    satelliteName:"吉林一号",
    stationId:"CC",
    stationName:"长春站",
    orbitId:123,
    downlinkChannel:"0",
    receptionType:"stationId",
    receiveStartTime:"2018-02-22 12:12:12",
    receiveStopTime:"2018-02-22 12:12:12",
    satelliteCaptureStartTime:"2018-02-22 12:12:12",
    satelliteCaptureStopTime:"2018-02-22 12:12:12",
    taskNames:"taskNames",
    otTaskIds:["taskId1", "taskId2"]
}
            </textarea>
        </div>
    </div>
    <div class="field">
        <div class="sixteen fields">
            <input class="ui teal submit button" type="submit" value="Submit Request">
        </div>
    </div>
</form>
</body>
</html>
