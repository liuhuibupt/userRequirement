<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>用户需求</title>
<script>
$(document).ready(function() {
    $('.ui.selection.dropdown').dropdown();
    $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
    });
});

function requestResult(pageNum) {

    var locationHref = "userRequest-list?&curPageNum=" + pageNum;

    var requestName = $("#requestName").val();
    if ('' != requestName) {
        locationHref += "&requestName=" + requestName;
    }

    var keyword = $("#keyword").val();
    if ('' != keyword) {
        locationHref += "&keyword=" + keyword;
    }

    window.location.href = locationHref;
}
</script>
</head>
<body>
<h2 class="ui header">用户需求</h2>
<div class="ui divider"></div>
<div class="ui styled fluid accordion">
    <div class="title active"><i class="search icon"></i> Search Criteria </div>
    <div class="content active">
        <div class="ui mini form">
            <div class="two fields">
                <div class="field">
                    <label>需求名称</label>
                    <input id="requestName" placeholder="Request Name" type="text">
                </div>
                <div class="field">
                    <label>关键字</label>
                    <input id="keyword" placeholder="Keyword" type="text">
                </div>
            </div>
            <a class="ui submit button" href="javascript:requestResult(0)">Search</a>
        </div>
    </div>
</div>
<div class="ui mini blue message">共检索1024条记录</div>
<table class="ui celled table">
    <thead>
    <tr>
        <th width="5%">No.</th>
        <th width="12%">需求代码</th>
        <th>需求名称</th>
        <th width="10%">Satellite</th>
        <th width="10%">From</th>
        <th width="10%">提交者</th>
        <th width="20%">提交时间</th>
        <th width="10%">状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resultSet}" var="userRequest">
        <tr>
            <td>${userRequest.num}</td>
            <td><a href="userRequest?userRequestId=${userRequest.id}">${userRequest.requestCode}</a></td>
            <td>${userRequest.requestName}</td>
            <td>${userRequest.requestSatellites}</td>
            <td>${userRequest.requestFrom}</td>
            <td>${userRequest.submitter.displayName}</td>
            <td>${userRequest.submitTime}</td>
            <td>Status</td>
        </tr>
    </c:forEach>
    <tfoot>
    <tr>
        <th colspan="8">
            <div class="ui left floated pagination menu">
                <a class="icon item"  href="javascript:requestResult(${cri.curPageNum - 1})">
                    <i class="left chevron icon"></i>
                </a>
                <c:if test="${cri.curPageNum == 0}">
                    <span class="item active blue">1</span>
                </c:if>
                <c:if test="${cri.curPageNum != 0}">
                    <a class="item" href="javascript:requestResult(0)">1</a>
                </c:if>
                <c:if test="${cri.curPageNum > 5}">
                    <span class="item">...</span>
                </c:if>
                <c:if test="${cri.totalPageNum > 1}">
                <c:forEach begin="${(cri.curPageNum - 4)  < 1 ? 1 : cri.curPageNum - 4}" end="${cri.curPageNum < cri.totalPageNum - 5 ? cri.curPageNum + 4 : cri.totalPageNum - 2}" var="i">
                    <c:if test="${cri.curPageNum == i}">
                        <span class="item active blue">${i + 1}</span>
                    </c:if>
                    <c:if test="${cri.curPageNum != i}">
                        <a class="item" href="javascript:requestResult(${i})">${i + 1}</a>
                    </c:if>
                </c:forEach>
                </c:if>
                <c:if test="${cri.curPageNum < cri.totalPageNum - 6}">
                    <span class="item">...</span>
                </c:if>
                <c:if test="${cri.totalPageNum > 1}">
                    <c:if test="${cri.curPageNum + 1 == cri.totalPageNum}">
                        <span class="item active blue">${cri.totalPageNum}</span>
                    </c:if>
                    <c:if test="${cri.curPageNum + 1 != cri.totalPageNum}">
                        <a class="item" href="javascript:requestResult(${cri.totalPageNum - 1})">${cri.totalPageNum}</a>
                    </c:if>
                </c:if>
                <a class="icon item"  href="javascript:requestResult(${cri.curPageNum + 1})">
                    <i class="right chevron icon"></i>
                </a>
            </div>
        </th>
    </tr>
    </tfoot>
    </tbody>
</table>
</body>
</html>

