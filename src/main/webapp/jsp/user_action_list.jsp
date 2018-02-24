<%--
  Created by IntelliJ IDEA.
  User: PANZHENG
  Date: 2018/2/5
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="serverUrl" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<html>
<head>
    <title>用户操作</title>
    <link rel="stylesheet" type="text/css" href="${serverUrl}/css/calendar.min.css">
    <script src="${serverUrl}/js/calendar.js"></script>

    <script>
        $(document).ready(function() {
            $('.ui.selection.dropdown').dropdown();
            $('.ui.dropdown').dropdown();
            $('.ui.menu.dropdown').dropdown();
            $('.ui.checkbox').checkbox();
        });
    </script>
</head>
<body>
<h2 class="ui header">用户操作</h2>
<div class="ui divider"></div>
<table class="ui celled striped table">
    <thead>
    <tr>
        <th colspan="5">
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
        <th width="15%">User</th>
        <th>Action Name</th>
        <th width="10%">Jump Link</th>
        <th width="15%">Action Time</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resultSet}" var="userAction">
    <tr>
        <td>${userAction.id}</td>
        <td>${userAction.executor.displayName}</td>
        <td>${userAction.actionName}</td>
        <td><a href="${userAction.jumpLink}">
            <div class="ui teal label">
                <i class="browser icon"></i>Browser
            </div></a>
        </td>
        <td>${userAction.actionTime}</td>
    </tr>
    </c:forEach>
    <tfoot>
    </tfoot>
    </tbody>
</table>
</body>
</html>
