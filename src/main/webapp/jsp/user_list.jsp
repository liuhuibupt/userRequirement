<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<h2 class="ui header">用户列表</h2>
<div class="ui divider"></div>
<table class="ui celled table">
    <thead>
    <tr>
        <th width="10%">No.</th>
        <th width="10%">User Name</th>
        <th>Display Name</th>
        <th width="10%">User Role</th>
        <th width="10%">Department Name</th>
        <th width="10%">标题</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>01</td>
            <td>${user.username}</td>
            <td>${user.displayName}</td>
            <td>${user.role}</td>
            <td>${user.departmentName}</td>
            <td>Cell</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
