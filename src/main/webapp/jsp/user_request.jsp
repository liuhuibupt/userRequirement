<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec' %>

<c:set var="serverUrl" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<html>
<head>
<title>提交需求</title>

<link rel="stylesheet" type="text/css" href="${serverUrl}/css/calendar.min.css">

<script src="${serverUrl}/Cesium/Cesium.js"></script>
<script src="${serverUrl}/Cesium/DrawTool.js"></script>
<script src="${serverUrl}/Cesium/DrawHelper.js"></script>
<script src="${serverUrl}/js/calendar.js"></script>

<script>
    $(document).ready(function () {
        var arcgisImageProvider = new Cesium.ArcGisMapServerImageryProvider({
            url: "http://10.10.20.234:6080/arcgis/rest/services/World14/MapServer"
        });
        var arcgisImageViewMode = new Cesium.ProviderViewModel({
            name: 'Argis',
            iconUrl: Cesium.buildModuleUrl('Widgets/Images/ImageryProviders/bingAerial.png'),
            tooltip: 'Argis',
            creationFunction: function () {
                return arcgisImageProvider;
            }
        });
        var imageryViewModels = new Array();
        imageryViewModels.push(arcgisImageViewMode);
        var viewer = new Cesium.Viewer('cesiumContainer', {
            animation: false, //是否创建动画小器件，左下角仪表
            baseLayerPicker: true, //是否显示图层选择器
            imageryProviderViewModels: imageryViewModels,
            //imageryProvider:googleImageProvider,
            // terrainProvider:terrainProvider,
            fullscreenButton: false, //是否显示全屏按钮
            geocoder: false, //是否显示geocoder小器件，右上角查询按钮
            homeButton: false, //是否显示Home按钮
            infoBox: false, //是否显示信息框
            sceneModePicker: false, //是否显示3D/2D选择器
            selectionIndicator: false, //是否显示选取指示器组件
            timeline: false, //是否显示时间轴
            navigationHelpButton: false, //是否显示右上角的帮助按钮
            scene3DOnly: false, //如果设置为true，则所有几何图形以3D模式绘制以节约GPU资源
            navigationInstructionsInitiallyVisible: false,
            showRenderLoopErrors: false,
            shadows: true,
            sceneMode: Cesium.SceneMode.SCENE2D,
        });
        startMap(viewer, loggingPolygon, loggingMark, loggingMessage);

        $('.ui.selection.dropdown').dropdown();
        $('.ui.dropdown').dropdown();

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
                imagingMode: {
                    identifier: 'imagingMode',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please select [Imaging Mode]'
                        }
                    ]
                },
                imagingWkt: {
                    identifier: 'imagingWkt',
                    rules: [
                        {
                            type: 'empty',
                            prompt: 'Please entry [Imaging WKT]'
                        }
                    ]
                }
            }
        });

        $('#requestType').change(function () {
            $('#requestType_point').hide();
            $('#imagingWkt').attr("readonly", "readonly");
            $('#imagingWkt').val("");

            var val = $(this).val();
            if (val == 'POINT') {
                $('#requestType_point').show();
                drawPoint(loggingMark);
            }
            if (val == 'AREA') {
                drawPolygon(loggingPolygon);
            }
            if (val == 'REPEATED-POINT') {
                $('#requestType_point').show();
                drawPoint(loggingMark);
            }
            if (val == 'IN-SPACE') {
                $('#imagingWkt').removeAttr('readonly');
            }
        });

        $("#longitude").on("input propertychange", setPoint);

        $("#latitude").on("input propertychange", setPoint);

        function setPoint() {
            var lon = $("#longitude").val();
            var lat = $("#latitude").val();
            if (lon != "" && lat != "") {
                setPointPosition(lon, lat);
                var wkt = "POINT(" + lon + " " + lat + ")";
                $("#imagingWkt").val(wkt);
            }
        }

        $('#requestType_point').hide();

        $('#requestStartDiv').calendar({
            type: 'datetime'
        });

        $('#requestEndDiv').calendar({
            type: 'datetime'
        });

        setGeoJson(${imagingGeojson});
    });

    var loggingPolygon = function(positions) {
        var wkt = "POLYGON ((";
        for(var i = 0; i <= positions.length - 2;i++) {
            var position;
            if (i == positions.length - 2) {
                position = positions[0];
            }else {
                position = positions[i];
            }
            var cartographic = Cesium.Cartographic.fromCartesian(new Cesium.Cartesian3(position.x, position.y, position.z));
            var longitudeString = Cesium.Math.toDegrees(cartographic.longitude).toFixed(8);
            var latitudeString = Cesium.Math.toDegrees(cartographic.latitude).toFixed(8);
            wkt += longitudeString + " " + latitudeString + ",";
        }
        wkt = wkt.substring(0, wkt.length - 1) + "))";
        $("#imagingWkt").val(wkt);
    }

    var loggingMark = function(lon, lat) {
        $("#longitude").val(lon);
        $("#latitude").val(lat);
        var wkt = "POINT(" + lon + " " + lat + ")";
        $("#imagingWkt").val(wkt);
    };

    var loggingMessage = function(message) {
        $(".loggingMessage").html(message);
    }

    function setGeoJson(geoJson) {
        var type = geoJson.type;
        if (type == "Point") {
            var lon = geoJson.coordinates[0];
            var lat = geoJson.coordinates[1];
            $('#requestType_point').show();
            loggingMark(lon, lat);
            setPointPosition(lon, lat);
        }else if (type == "Polygon") {
            addPolygonFromGeo(geoJson.coordinates[0]);
        }
    }

    function setRequestDate(obj, future) {
        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        var date = today.getDate() + future;

        month = month > 9 ? month : '0' + month;
        date = date > 9 ? date : '0' + date;

        if ('start' == obj) {
            $('#requestStart').val(year + '-' + month + '-' + date + ' 00:00:00');
        }
        if ('end' == obj) {
            $('#requestEnd').val(year + '-' + month + '-' + date + ' 23:59:59');
        }
    }
</script>
<style>
    @import url(${serverUrl}/Cesium/Widgets/widgets.css);
    @import url(${serverUrl}/Cesium/DrawHelper.css);

    #cesiumContainer {
        width: 1200px;
        height: 500px;
        margin: 0;
        padding: 0;
        overflow: hidden;
        position: relative;
    }
    .loggingMessage {
        z-index: 1;
        position: absolute;
        width: 250px;
        bottom: 0px;
        right: 0;
        display: inline;
        margin: 10px;
        padding: 10px;
        background: white;
    }
    .cesium-viewer-bottom {
        display: none!important;
    }
</style>
</head>
<body>
<h2 class="ui header">提交需求</h2>
<div class="ui divider"></div>

<form class="ui form" action="submitUserRequest" method="post" style="margin-top: 0.5rem">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" id="requestId" name="requestId" placeholder="Request ID" value="${userRequest.requestId}">
    <input type="hidden" id="requestFrom" name="requestFrom" placeholder="Request From" value="${userRequest.requestFrom}">
    <input type="hidden" name="submitterId" value="${submitter.id}" >
    <input type="hidden" id="submitTime" name="submitTime" placeholder="Submit Time" value="<fmt:formatDate value="${userRequest.submitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
    <div class="ui error message">
    </div>
    <div class="sixteen fields">
        <div class="field">
            <a class="ui tag label">
                Request ID<dev class="detail">${userRequest.requestId}</dev>
            </a>
            <a class="ui tag label">
                Request Status<dev class="detail">${userRequest.status}</dev>
            </a>
            <a class="ui tag label">
                Request From<dev class="detail">市场部</dev>
            </a>
            <a class="ui tag label">
                Submitter<dev class="detail">${submitter.displayName}</dev>
            </a>
            <a class="ui tag label">
                Submit Date<dev class="detail"><fmt:formatDate value="${userRequest.submitTime}" pattern="yyyy-MM-dd HH:mm:ss"/></dev>
            </a>
        </div>
    </div>
    <div class="four fields">
        <div class="eight wide field">
            <label>Request Name</label>
            <input type="text" id="requestName" name="requestName" placeholder="Request Name" value="${userRequest.requestName}">
        </div>
        <div class="field">
            <label>敏感需求${userRequest.sensitive}</label>
            <div class="ui fluid dropdown selection" tabindex="0">
                <select id="isSensitive" name="isSensitive">
                    <option value="false" <c:if test="${userRequest.sensitive}">selected</c:if>>非敏感</option>
                    <option value="true" <c:if test="${userRequest.sensitive}">selected</c:if>>敏感</option>
                </select><i class="dropdown icon"></i>
                <div class="default text">非敏感</div>
                <div class="menu transition hidden" tabindex="-1">
                    <div class="item" data-value="false">非敏感</div>
                    <div class="item" data-value="true">敏感</div>
                </div>
            </div>
        </div>
    </div>
    <div class="four fields">
        <div class="field">
            <label>Request Type</label>
            <div class="ui fluid dropdown selection" tabindex="0">
                <select id="requestType" name="requestType">
                    <option value=""></option>
                    <option value="POINT" <c:if test="${userRequest.requestType == 'POINT'}">selected</c:if>>点目标</option>
                    <option value="AREA" <c:if test="${userRequest.requestType == 'AREA'}">selected</c:if>>大区域</option>
                    <option value="REPEATED-POINT" <c:if test="${userRequest.requestType == 'REPEATED-POINT'}">selected</c:if>>单点多次</option>
                    <option value="IN-SPACE" <c:if test="${userRequest.requestType == 'IN-SPACE'}">selected</c:if>>惯性空间</option>
                </select><i class="dropdown icon"></i>
                <div class="default text">Request Type</div>
                <div class="menu transition hidden" tabindex="-1">
                    <div class="item" data-value="POINT">点目标</div>
                    <div class="item" data-value="AREA">大区域</div>
                    <div class="item" data-value="REPEATED-POINT">单点多次</div>
                    <div class="item" data-value="IN-SPACE">惯性空间</div>
                </div>
            </div>
        </div>
        <div class="field">
            <label>Request Satellites</label>
            <div class="ui fluid dropdown selection multiple" tabindex="0">
                <select id="requestSatellites" name="requestSatellites" multiple="">
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
            <label>Imaging Mode</label>
            <div class="ui fluid dropdown selection" tabindex="0">
                <select id="imagingMode" name="imagingMode">
                    <option value=""></option>
                    <option value="常规推扫" <c:if test="${userRequest.imagingMode == '常规推扫'}">selected</c:if>>常规推扫</option>
                    <option value="反向推扫" <c:if test="${userRequest.imagingMode == '反向推扫'}">selected</c:if>>反向推扫</option>
                    <option value="凝视视频" <c:if test="${userRequest.imagingMode == '凝视视频'}">selected</c:if>>凝视视频</option>
                </select><i class="dropdown icon"></i>
                <div class="default text">Imaging Mode</div>
                <div class="menu transition hidden" tabindex="-1">
                    <div class="item" data-value="常规推扫">常规推扫</div>
                    <div class="item" data-value="反向推扫">反向推扫</div>
                    <div class="item" data-value="凝视视频">凝视视频</div>
                </div>
            </div>
        </div>
    </div>
    <div class="four fields" id="requestType_point">
        <div class="field">
            <label>经度 Longitude</label>
            <input type="text" id="longitude" name="longitude" placeholder="Longitude">
        </div>
        <div class="field">
            <label>纬度 Latitude</label>
            <input type="text" id="latitude" name="latitude" placeholder="Latitude">
        </div>
    </div>
    <div id="cesiumContainer" style="margin-bottom: 0.75rem">
        <div class="loggingMessage"></div>
    </div>
    <div class="field">
        <div class="four fields">
            <div class="field">
                <label>需求开始时间</label>
                <div class="ui calendar" id="requestStartDiv">
                    <div class="ui input left icon">
                        <i class="calendar icon"></i>
                        <input type="text" id="requestStart" name="requestStart" placeholder="Request Start" value="${userRequest.requestStart}">
                    </div>
                </div>
            </div>
            <div class="field">
                <label>需求结束时间</label>
                <div class="ui calendar" id="requestEndDiv">
                    <div class="ui input left icon">
                        <i class="calendar icon"></i>
                        <input type="text" id="requestEnd" name="requestEnd" placeholder="Request End" value="${userRequest.requestEnd}">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="field">
        <div class="four fields">
            <div class="field">
                <a class="mini ui labeled icon button" href="javascript:setRequestDate('start', 1)">
                    <i class="checked calendar icon"></i>
                    Enter Tomorrow
                </a>
                <div class="mini ui floating labeled icon dropdown button">
                    <i class="right arrow icon"></i>
                    <span>More Selection</span>
                    <div class="left menu">
                        <div class="item" onclick="javascript:setRequestDate('start', 2)">2 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('start', 3)">3 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('start', 4)">4 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('start', 5)">5 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('start', 6)">6 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('start', 7)">7 days later</div>
                    </div>
                </div>
            </div>
            <div class="field">
                <a class="mini ui labeled icon button" href="javascript:setRequestDate('end', 1)">
                    <i class="checked calendar icon"></i>
                    Enter Tomorrow
                </a>
                <div class="mini ui floating labeled icon dropdown button">
                    <i class="right arrow icon"></i>
                    <span>More Selection</span>
                    <div class="left menu">
                        <div class="item" onclick="javascript:setRequestDate('end', 2)">2 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('end', 3)">3 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('end', 4)">4 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('end', 5)">5 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('end', 6)">6 days later</div>
                        <div class="item" onclick="javascript:setRequestDate('end', 7)">7 days later</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <label>成像代码 Imaging WKT</label>
                <textarea class="ready-only" id="imagingWkt" name="imagingWkt" readonly>${userRequest.imagingWkt}</textarea>
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <label>关键字 Keyword</label>
                <input type="text" id="keyword" name="keyword" placeholder="Keyword" value="${userRequest.keyword}">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="two fields">
            <div class="field">
                <label>备注 Comments</label>
                <textarea id="comments" name="comments">${userRequest.comments}</textarea>
            </div>
            <div class="field">
            </div>
        </div>
    </div>
    <div class="field">
        <div class="sixteen fields">
            <c:if test="${userRequest == null}"><div class="field">
                <input class="ui teal submit button" type="submit" value="Submit Request">
            </div></c:if>
            <c:if test="${author.id eq submitter.id}"><div class="field">
                <a class="ui teal submit button"  href="cancelUserRequest?userRequestId=${userRequest.id}">Cancel Request</a>
            </div></c:if>
            <c:if test="${author.id != submitter.id}"><sec:authorize access="hasRole('ROLE_ADMIN')"><div class="field">
                <a class="ui blue submit button"  href="cancelUserRequest?userRequestId=${userRequest.id}">Cancel Request</a>
            </div></sec:authorize></c:if>
            <sec:authorize access="hasRole('ROLE_ADMIN')"><div class="field">
                <a class="ui red submit button"  href="deleteUserRequest?userRequestId=${userRequest.id}">Delete Request</a>
            </div></sec:authorize>
        </div>
    </div>
</form>
</body>
</html>

