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
    <title>Imaging Task</title>
</head>
<body>
<h2 class="ui header">Test Adding Imaging Task</h2>
<div class="ui divider"></div>
<form class="ui form" method="post" action="submitImagingTask" style="margin-top: 0.5rem">
    <div class="ui error message">
    </div>
    <div class="two fields">
        <div class="field">
            <label>Input Imaging Task JSON</label>
            <textarea id="json" name="json" rows="20">{
    taskId:"taskId",
    planId:"planId",
    requestName:"requestName",
    satelliteId:"JL101A",
    satelliteName:"吉林一号",
    orbitId:123,
    imagingMode:"PB",
    imagingStart:"2018-02-22 12:12:12",
    imagingEnd:"2018-02-22 12:12:12",
    imagingWkt:"WKT"
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
