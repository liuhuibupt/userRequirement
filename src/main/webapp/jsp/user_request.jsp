<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="serverAddress" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<html>
<head>
    <title>提交需求</title>
    <script src="${serverAddress}/Cesium/Cesium.js"></script>
    <script src="${serverAddress}/Cesium/DrawTool.js"></script>
    <script src="${serverAddress}/Cesium/DrawHelper.js"></script>

    <script>
        $(document).ready(function () {
            $('.ui.selection.dropdown').dropdown();
            $('.ui.menu .ui.dropdown').dropdown({
                on: 'hover'
            });


        });

        $('.ui.form').form({
            fields: {
                requestName: {
                    identifier: 'requestName',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please enter [Request Name]'
                        }
                    ]
                },
                requestType: {
                    identifier: 'requestType',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please select [Request Type]'
                        }
                    ]
                },
                imagingType: {
                    identifier: 'imagingType',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please select [Imaging Type]'
                        }
                    ]
                }
            }
        });

    </script>
    <style>
        @import url(${serverAddress}/Cesium/Widgets/widgets.css);
        @import url(${serverAddress}/Cesium/DrawHelper.css);

        #cesiumContainer {
            width: 1200px;
            height: 500px;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        #toolbar {
            z-index: 1;
            position: absolute;
            top: 200px;
            left: 240px;
            display: inline;
            margin: 10px;
            padding: 0px;
            background: white;
        }
        .cesium-viewer-bottom {
            display: none!important;
        }
    </style>
</head>
<body>
<div id="toolbar"></div>
<h2 class="ui header">提交需求</h2>
<div class="ui divider"></div>
<div id="cesiumContainer"> </div>
<form class="ui form" action="submitUserRequest" method="post" style="margin-top: 2rem">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <h4 class="ui dividing header">Imaging Request Information</h4>
    <div class="field">
        <label>Request Name</label>
        <div class="two fields">
            <div class="field">
                <input type="text" id="requestName" name="requestName" placeholder="Request Name" value="${userRequest.requestName}">
            </div>
            <div class="field">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="four fields">
            <div class="field">
                <label>Request Submitter</label>
                <input type="hidden" name="submitterId" value="${submitter.id}" >
                <input class="read-only" placeholder="Request Submitter"  value="${submitter.displayName}" readonly>
            </div>
            <div class="field">
                <label>Submit Time</label>
                <input class="read-only" placeholder="Submit Time" value="<fmt:formatDate value="${userRequest.submitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
            </div>
        </div>
    </div>
    <div class="field">
        <div class="four fields">
            <div class="field">
                <label>Request Satellites</label>
                <div class="ui fluid dropdown selection multiple" tabindex="0">
                    <select id="requestSatellites" name="requestSatellites" multiple="">
                        <option value="ALL-SATELLITE"></option>
                        <option value="JL101A" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL101A')}">selected</c:if>>光学A星</option>
                        <option value="JL101B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL101B')}">selected</c:if>>视频01星</option>
                        <option value="JL102B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL102B')}">selected</c:if>>视频02星</option>
                        <option value="JL103B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL103B')}">selected</c:if>>视频03星</option>
                        <option value="JL104B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL104B')}">selected</c:if>>视频04星</option>
                        <option value="JL105B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL105B')}">selected</c:if>>视频05星</option>
                        <option value="JL106B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL106B')}">selected</c:if>>视频06星</option>
                        <option value="JL107B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL107B')}">selected</c:if>>视频07星</option>
                        <option value="JL108B" <c:if test="${fn:contains(userRequest.requestSatellites, 'JL108B')}">selected</c:if>>视频08星</option>
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
            </div>
        </div>
    </div>
    <div class="field">
        <div class="four fields">
            <div class="field">
                <label>Request Type</label>
                <div class="ui fluid dropdown selection" tabindex="0">
                    <select id="requestType" name="requestType">
                        <option value=""></option>
                        <option value="POINT" <c:if test="${userRequest.requestType == 'POINT'}">selected</c:if>>点目标</option>
                        <option value="AREA" <c:if test="${userRequest.requestType == 'AREA'}">selected</c:if>>大区域</option>
                        <option value="REPEATED-POINT" <c:if test="${userRequest.requestType == 'REPEATED-POINT'}">selected</c:if>>单点多次</option>
                    </select><i class="dropdown icon"></i>
                    <div class="default text">Request Type</div>
                    <div class="menu transition hidden" tabindex="-1">
                        <div class="item" data-value="POINT">点目标</div>
                        <div class="item" data-value="AREA">大区域</div>
                        <div class="item" data-value="REPEATED-POINT">单点多次</div>
                    </div>
                </div>
            </div>
            <div class="field">
                <label>Imaging Type</label>
                <div class="ui fluid dropdown selection" tabindex="0">
                    <select id="imagingType" name="imagingType">
                        <option value=""></option>
                        <option value="常规推扫" <c:if test="${userRequest.imagingType == '常规推扫'}">selected</c:if>>常规推扫</option>
                        <option value="凝视视频" <c:if test="${userRequest.imagingType == '凝视视频'}">selected</c:if>>凝视视频</option>
                    </select><i class="dropdown icon"></i>
                    <div class="default text">Imaging Type</div>
                    <div class="menu transition hidden" tabindex="-1">
                        <div class="item" data-value="常规推扫">常规推扫</div>
                        <div class="item" data-value="凝视视频">凝视视频</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="field">
        <div class="four fields">
            <div class="field">
                <label>经度 Longitude</label>
                <input type="text" id="longitude" name="longitude" placeholder="Longitude">
            </div>
            <div class="field">
                <label>纬度 Latitude</label>
                <input type="text" id="latitude" name="latitude" placeholder="Latitude">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="four fields">
            <div class="field">
                <label>开始时间</label>
                <input type="text" id="startDate" name="startDate" placeholder="Start Date">
            </div>

            <div class="field">
                <label>结束时间</label>
                <input type="text" id="endDate" name="endDate" placeholder="End Date">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <label>区域 Area</label>
                <input type="text" id="area" name="area" placeholder="Area">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <label>成像代码 Imaging Code</label>
                <input type="text"  id="imagingCode" name="imagingCode" placeholder="Imaging Code">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <label>关键字 Keyword</label>
                <input type="text" id="keyword" name="keyword" placeholder="Imaging Code">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <label>备注 Comments</label>
                <textarea id="comments" name="comments"></textarea>
            </div>
            <div class="field">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <c:if test="${userRequest == null}"> <div class="ui teal submit button">Submit</div>
                </c:if>
            </div>
        </div>
    </div>
</form>
</body>
</html>

