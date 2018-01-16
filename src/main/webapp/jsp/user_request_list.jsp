<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="serverUrl" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<html>
<head>
<title>用户需求</title>
<link rel="stylesheet" type="text/css" href="${serverUrl}/css/calendar.min.css">
<script src="${serverUrl}/js/calendar.js"></script>

<script>
$(document).ready(function() {
    $('.ui.selection.dropdown').dropdown();
    $('.ui.menu .ui.dropdown').dropdown({
        on: 'hover'
    });

    $('#dateStartDiv').calendar({
        type: 'date'
    });

    $('#dateEndDiv').calendar({
        type: 'date'
    });
});

function requestResult(pageNum) {

    var locationHref = "userRequest-list?&curPageNum=" + pageNum;

    var requestSatellite = $("#requestSatellite").val();
    if ('' != requestName) {
        locationHref += "&requestSatellite=" + requestSatellite;
    }

    var imagingMode = $("#imagingMode").val();
    if ('' != imagingMode) {
        locationHref += "&imagingMode=" + imagingMode;
    }

    var dateStart = $("#dateStart").val();
    if ('' != dateStart) {
        locationHref += "&dateStart=" + dateStart + " 00:00:01";
    }

    var dateEnd = $("#dateEnd").val();
    if ('' != dateEnd) {
        locationHref += "&dateEnd=" + dateEnd + " 23:59:59";
    }

    var requestName = $("#requestName").val();
    if ('' != requestName) {
        locationHref += "&requestName=" + requestName;
    }

    var keyword = $("#keyword").val();
    if ('' != keyword) {
        locationHref += "&keyword=" + keyword;
    }

    var orderby = $("#orderby").val();
    if ('' != orderby) {
        locationHref += "&orderby=" + orderby;
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
            <div class="eight fields">
                <div class="field">
                    <label>选择卫星</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="requestSatellite">
                            <option value=""></option>
                            <option value="JL101A" <c:if test="${cri.requestSatellite == 'JL101A'}">selected</c:if>>光学A星</option>
                            <option value="JL101B" <c:if test="${cri.requestSatellite == 'JL101B'}">selected</c:if>>视频01星</option>
                            <option value="JL102B" <c:if test="${cri.requestSatellite == 'JL102B'}">selected</c:if>>视频02星</option>
                            <option value="JL103B" <c:if test="${cri.requestSatellite == 'JL103B'}">selected</c:if>>视频03星</option>
                            <option value="JL104B" <c:if test="${cri.requestSatellite == 'JL104B'}">selected</c:if>>视频04星</option>
                            <option value="JL105B" <c:if test="${cri.requestSatellite == 'JL105B'}">selected</c:if>>视频05星</option>
                            <option value="JL106B" <c:if test="${cri.requestSatellite == 'JL106B'}">selected</c:if>>>视频06星</option>
                            <option value="JL107B" <c:if test="${cri.requestSatellite == 'JL107B'}">selected</c:if>>>视频07星</option>
                            <option value="JL108B" <c:if test="${cri.requestSatellite == 'JL108B'}">selected</c:if>>视频08星</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">All Satellites</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="JL101A">光学A星</div>
                            <div class="item" data-value="JL101B">视频01星</div>
                            <div class="item" data-value="JL102B">视频02星</div>
                            <div class="item" data-value="JL103B">视频03星</div>
                            <div class="item" data-value="JL104B">视频04星</div>
                            <div class="item" data-value="JL105B">视频05星</div>
                            <div class="item" data-value="JL106B">视频06星</div>
                            <div class="item" data-value="JL107B">视频07星</div>
                            <div class="item" data-value="JL108B">视频08星</div>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>成像模式</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="imagingMode">
                            <option value=""></option>
                            <option value="常规推扫" <c:if test="${cri.imagingMode == '常规推扫'}">selected</c:if>>常规推扫</option>
                            <option value="凝视视频" <c:if test="${cri.imagingMode == '凝视视频'}">selected</c:if>>凝视视频</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">Imaging Type</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="常规推扫">常规推扫</div>
                            <div class="item" data-value="凝视视频">凝视视频</div>
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>日期开始</label>
                    <div class="ui calendar">
                        <div class="ui input left icon" id="dateStartDiv">
                            <i class="calendar icon"></i>
                            <input type="text" id="dateStart" name="dateStart" placeholder="Request Start" value="${cri.dateStart}">
                        </div>
                    </div>
                </div>
                <div class="field">
                    <label>日期截止</label>
                    <div class="ui calendar">
                        <div class="ui input left icon" id="dateEndDiv">
                            <i class="calendar icon"></i>
                            <input type="text" id="dateEnd" name="dateEnd" placeholder="Request End" value="${cri.dateStart}">
                        </div>
                    </div>
                </div>
            </div>
            <div class="four fields">
                <div class="field">
                    <label>需求名称</label>
                    <input id="requestName" placeholder="Request Name" type="text" value="${cri.requestName}">
                </div>
                <div class="field">
                    <label>关键字</label>
                    <input id="keyword" placeholder="Keyword" type="text" value="${cri.keyword}">
                </div>
            </div>
            <div class="four fields">
                <div class="field">
                    <label>排序方式</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="orderby" name="orderby">
                            <option value=""></option>
                            <option value="submitTimeAsc" <c:if test="${cri.orderby == '时间顺序'}">selected</c:if>>时间顺序</option>
                            <option value="submitTimeDesc" <c:if test="${cri.orderby == '时间倒序'}">selected</c:if>>时间倒序</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">默认排序</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="submitTimeAsc">时间顺序</div>
                            <div class="item" data-value="submitTimeDesc">时间倒序</div>
                        </div>
                    </div>
                </div>
            </div>
            <a class="ui teal submit button" href="javascript:requestResult(0)">Search</a>
        </div>
    </div>
</div>
<div class="ui mini blue message">共检索${cri.resultCount}条记录</div>
<table class="ui celled table">
    <thead>
    <tr>
        <th width="5%">No.</th>
        <th width="15%">需求代码</th>
        <th>需求名称</th>
        <th width="7%">Satellite</th>
        <th width="7%">需求类型</th>
        <th width="7%">成像模式</th>
        <th width="10%">From</th>
        <th width="10%">提交者</th>
        <th width="15%">提交时间</th>
        <th width="5%">状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${resultSet}" var="userRequest">
        <tr>
            <td>${userRequest.num}</td>
            <td><a href="userRequest?userRequestId=${userRequest.id}">${userRequest.requestId}</a></td>
            <td>${userRequest.requestName}</td>
            <td>${userRequest.requestSatellites}</td>
            <td>${userRequest.requestType}</td>
            <td>${userRequest.imagingMode}</td>
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

