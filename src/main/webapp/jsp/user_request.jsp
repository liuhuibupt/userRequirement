<%--CREATED BY LIUHUI ON 2018/3/20--%>
<%--EDITED BY PANSHENGNAN--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec' %>

<c:set var="serverUrl" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<html>
<head>
    <title>录入需求-基本信息</title>
    <link rel="stylesheet" type="text/css" href="${serverUrl}/css/calendar.min.css">
    <script src="${serverUrl}/Cesium/Cesium.js"></script>
    <script src="${serverUrl}/Cesium/DrawTool.js"></script>
    <script src="${serverUrl}/Cesium/DrawHelper.js"></script>
    <script src="${serverUrl}/js/calendar.js"></script>
    <script src="${serverUrl}/js/semantic.js"></script>
    <script>
        $(document).ready(function () {
            var arcgisImageProvider = new Cesium.ArcGisMapServerImageryProvider({
                // url: "http://10.10.20.234:6080/arcgis/rest/services/World14/MapServer"
               url:" http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer"
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

            $('#testPopup').popup({
                hoverable: true,
                delay: {
                    show: 100,
                    hide: 100
                }
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
                    sensitive: {
                        identifier: 'sensitive',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select [Is Sensitive]'
                            }
                        ]
                    },
                    priority: {
                        identifier: 'priority',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter [Priority]'
                            }
                        ]
                    },
                    imagingMode: {
                        identifier: 'imagingMode',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter [imagingMode]'
                            }
                        ]
                    },
                    imagingPara: {
                        identifier: 'imagingPara',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter [Imaging Parameter]'
                            }
                        ]
                    },
                    longitude: {
                        identifier: 'longitude',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter [longitude Parameter]'
                            }
                        ]
                    },
                    imagingPara: {
                        identifier: 'imagingPara',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter [Imaging Parameter]'
                            }
                        ]
                    },




                }
            });

            $('#requestType').ready(function () {
                var val = $('#requestType').val();
                if (val == 'IN-SPACE') {
                    $('#cesiumContainer').hide();
                }
            });

            $('#requestType').change(function () {
                $('#requestType_point').hide();
                $('#cesiumContainer').show();
                $('#imagingPara').val("");

                var val = $(this).val();
                if (val == 'POINT') {
                    $('#requestType_point1').show();
                    $('#requestType_point2').show();
                    drawPoint(loggingMark);
                }
                if (val == 'AREA') {
                    drawPolygon(loggingPolygon);
                    $('#requestType_point1').hide();
                    $('#requestType_point2').hide();
                }
                if (val == 'IN-SPACE') {
                    $('#cesiumContainer').hide();
                }
            });
            // $('#imagingMode').ready(function () {
            //     $('#Duration_isMultiGrid').hide();
            //     var val = $(this).val();
            //     if (val == '凝视成像' || val == '夜光凝视成像') {
            //         $('#Duration_isMultiGrid').show();
            //     }
            // });
            $('#imagingMode').change(function () {
                $('#Duration_isMultiGrid').hide();
                var val = $(this).val();
                if (val == '凝视成像' || val == '夜光凝视成像') {
                    $('#Duration_isMultiGrid').show();
                }
            });

            $('#elevation').change(function () {
                var imagingPara = $('#imagingPara').val();
                if ('' != imagingPara) {
                    updateImagingParameter();
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
                    updateImagingParameter();
                }
            }

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
            updateImagingParameter();
        }

        var loggingMark = function(lon, lat) {
            $("#longitude").val(lon);
            $("#latitude").val(lat);
            var wkt = "POINT(" + lon + " " + lat + ")";
            $("#imagingWkt").val(wkt);
            updateImagingParameter();
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
        function updateImagingParameter() {
            var imagingPara = '';
            var requestType = $('#requestType').val();

            if ('POINT' == requestType || 'AREA' == requestType) {
                var imagingWkt = $('#imagingWkt').val();
                if (imagingWkt != '') {
                    imagingPara += "imagingWkt=" + imagingWkt;
                }

                var elevation = $('#elevation').val();
                if (elevation != '') {
                    if ('' != imagingPara) {
                        imagingPara += "\n";
                    }
                    imagingPara += "elevation=" + elevation;
                }
            }
            $('#imagingPara').val(imagingPara);
        }

        $(document).ready(function() {
            $('.ui.selection.dropdown').dropdown();
            $('.ui.dropdown').dropdown();
            $('.ui.menu.dropdown').dropdown();
            $('.ui.checkbox').checkbox();

            $(document).ready(function() {
                $('#productRequirement').popup({
                    hoverable: true,
                    delay: {
                        show: 100,
                        hide: 100
                    }
                });
                $('#moreRequirement').popup({
                    hoverable: true,
                    delay: {
                        show: 100,
                        hide: 100
                    }
                });
            });

            $('div[name="moredetail"]').popup({
                hoverable: true,
                delay: {
                    show: 100,
                    hide: 100
                }
            });
        });

        $(document).ready(function() {
                $('.menu .item')
                    .tab()
                ;
            })
        ;
    </script>
    <style>
        @import url(${serverUrl}/Cesium/Widgets/widgets.css);
        @import url(${serverUrl}/Cesium/DrawHelper.css);

        #cesiumContainer {
            z-index: 0;
            height: 100%;
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
        #more-constraint-conditions{
            z-index: 1;
        }
        #open-more{
            z-index: 1;
        }
        .bottom {
            margin: 10px 20px 10px 20px;
            position:fixed;
            bottom:0;
        }
        .body-style{

            margin: 0px 20px 0px 20px;
        }
        .radio-text{
            display: block;
            margin: 0em 2em 0.28571429rem 0em;
            color: rgba(0, 0, 0, 0.87);
            font-size: 0.92857143em;
            font-weight: bold;
            text-transform: none;
        }

    </style>

</head>
<body>

<form class="ui form" action="submitUserRequest" method="post" style="margin-top: 0.5rem">

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" id="submitTime" name="submitTime" placeholder="Submit Time" value="<fmt:formatDate value="${userRequest.submitTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly>
    <input type="hidden" name="submitterId" value="${submitter.id}" >
    <input type="hidden" id="requestFrom" name="requestFrom" placeholder="Request From" value="${userRequest.requestFrom}">
    <input type="hidden" id="requestId" name="requestId" placeholder="Request ID" value="${userRequest.requestId}">
    <input type="hidden" id="elevation" name="elevation" placeholder="Elevation">
    <c:if test="${userRequest != null}">
        <input type="hidden" id="num" name="num" value="${userRequest.id}">
    </c:if>
    <div class="body-style">
        <h2>录入需求
            <a class="ui tag label" >需求提交人<dev class="detail">${submitter.displayName}</dev></a>
        </h2>
        <div class="ui divider"></div>

        <div class="ui top attached tabular menu">
            <a class="item active" data-tab="first">需求基本信息</a>
            <a class="item" data-tab="second">更多约束要求</a>
            <a class="item " data-tab="third">产品相关要求</a>
            <a class="item " data-tab="fourth">拍摄相关要求</a>
        </div>
        <div class="ui bottom attached tab segment active" data-tab="first">
            <div class="ui form">
                <div class="four fields">
                    <div class="field" data-tooltip="请输入需求名称">
                        <label>需求名</label>
                        <input type="text" id="requestName" name="requestName" placeholder="需求名称" value="${userRequest.requestName}">
                    </div>
                    <div class="field" data-tooltip="请选择您需求的优先级">
                        <label>优先级</label>
                        <div class="ui fluid dropdown selection" tabindex="0">
                            <select id="priority" name="priority">
                                <option value=""></option>
                                <option value="0" <c:if test="${userRequest.priority == '0级'}">selected</c:if>>0级</option>
                                <option value="1" <c:if test="${userRequest.priority == '1级'}">selected</c:if>>1级</option>
                                <option value="2" <c:if test="${userRequest.priority == '2级'}">selected</c:if>>2级</option>
                                <option value="3" <c:if test="${userRequest.priority == '3级'}">selected</c:if>>3级</option>
                                <option value="4" <c:if test="${userRequest.priority == '4级'}">selected</c:if>>4级</option>
                            </select><i class="dropdown icon"></i>
                            <div class="default text">优先级</div>
                            <div class="menu transition hidden" tabindex="-1">
                                <div class="item" data-value="0级">0级</div>
                                <div class="item" data-value="1级">1级</div>
                                <div class="item" data-value="2级">2级</div>
                                <div class="item" data-value="3级">3级</div>
                                <div class="item" data-value="4级">4级</div>
                            </div>
                        </div>
                    </div>
                    <div class="field" data-tooltip="请选择您需求类型">
                        <label>需求类型</label>
                        <div class="ui fluid dropdown selection" tabindex="0" >
                            <select id="requestType" name="requestType" >
                                <option value=""></option>
                                <option value="POINT" <c:if test="${userRequest.requestType == 'POINT'}">selected</c:if>>点目标</option>
                                <option value="AREA" <c:if test="${userRequest.requestType == 'AREA'}">selected</c:if>>大区域</option>
                                <option value="IN-SPACE" <c:if test="${userRequest.requestType == 'IN-SPACE'}">selected</c:if>>惯性空间</option>
                            </select><i class="dropdown icon"></i>
                            <div class="default text">需求类型</div>
                            <div class="menu transition hidden" tabindex="-1">
                                <div class="item" data-value="POINT">点目标</div>
                                <div class="item" data-value="AREA">大区域</div>
                                <div class="item" data-value="IN-SPACE">惯性空间</div>
                            </div>
                        </div>
                    </div>
                    <div class="fields">
                        <%--经度--%>
                        <div class="field" id="requestType_point1"style="display: none">
                            <label>经度 Longitude</label>
                            <input type="text" id="longitude" name="longitude" placeholder="Longitude">
                        </div>
                        <%--维度--%>
                        <div class="field"id="requestType_point2"style="display: none" >
                            <label>纬度 Latitude</label>
                            <input type="text" id="latitude" name="latitude" placeholder="Latitude">
                        </div>

                    </div>
            </div>
        </div>

            <div class="fields">
                <div class="inline field" data-tooltip="请选择您的需求是否敏感，此项为必填选项">
                    <label>是否为敏感需求</label>
                    <div class="ui radio checkbox">
                        <input type="radio" name="sensitive" checked="checked">
                        <label>是</label>
                    </div>
                    <div class="ui radio checkbox">
                        <input type="radio" name="sensitive">
                        <label>否</label>
                    </div>
                </div>
            </div>

        </div>
        <div class="ui bottom attached tab segment" data-tab="second">
            <div class="eight fields">
                <%--真正用户--%>
                <div class="field" data-tooltip="请填写此次需求的客户名，此项为可选项">
                    <label>用户名</label>
                    <input  type="text" id="requestUser" name="requestUser" placeholder="真正用户名" value="${userRequest.requestUser}">
                </div>
                <%--云量要求--%>
                <div class="field" data-tooltip="请选择您对云量的要求，此项为可选项">
                    <label>云量要求</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="cloud" name="cloud">
                            <option value=""></option>
                            <option value="10" <c:if test="${userRequest.cloud == '10'}">selected</c:if>>10</option>
                            <option value="20" <c:if test="${userRequest.cloud == '20'}">selected</c:if>>20</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">云量要求</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="10">10</div>
                            <div class="item" data-value="20">20</div>
                        </div>
                    </div>
                </div>
                <%--分辨率--%>
                <div class="field" data-tooltip="请选择您需求的分辨率，此项为可选项">
                    <label>分辨率</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="resolution" name="resolution">
                            <option value=""></option>
                            <option value="1m" <c:if test="${userRequest.resolution == '1m'}">selected</c:if>>1m</option>
                            <option value="10m" <c:if test="${userRequest.resolution == '10m'}">selected</c:if>>10m</option>
                        </select>
                        <i class="dropdown icon"></i>
                        <div class="default text">分辨率</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="1m">1m</div>
                            <div class="item" data-value="10m">10m</div>

                        </div>
                    </div>
                </div>
                <%--侧摆角--%>
                <div class="field" data-tooltip="请选择您对侧摆角的要求，此项为可选项">
                    <label>侧摆要求</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="sideAngel" name="sideAngel">
                            <option value=""></option>
                            <option value="-40~40" <c:if test="${userRequest.sideAngel == '-40~40'}">selected</c:if>>-40~40</option>
                            <option value="0~40" <c:if test="${userRequest.sideAngel == '0~40'}">selected</c:if>>0~40</option>
                        </select>
                        <i class="dropdown icon"></i>
                        <div class="default text">侧摆角要求</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="-40~40">-40~40</div>
                            <div class="item" data-value="0~40">0~40</div>
                        </div>
                    </div>
                </div>
                <%--成像模式--%>
                <div class="field" data-tooltip="请选择成像模式" >
                        <label>成像模式</label>
                        <div class="ui fluid dropdown selection" tabindex="0">
                            <select id="imagingMode" name="imagingMode">
                                <option value=""></option>
                                <option value="推扫成像" <c:if test="${userRequest.imagingMode == '推扫成像'}">selected</c:if>>推扫成像</option>
                                <option value="凝视成像" <c:if test="${userRequest.imagingMode == '凝视成像'}"> selected</c:if>>凝视成像</option>
                                <option value="夜光凝视成像" <c:if test="${userRequest.imagingMode == '夜光凝视成像'}">selected</c:if>>夜光凝视成像</option>
                            </select><i class="dropdown icon"></i>
                            <div class="default text">成像模式</div>
                            <div class="menu transition hidden" tabindex="-1">
                                <div class="item" data-value="推扫成像">推扫成像</div>
                                <div class="item" data-value="凝视成像">凝视成像</div>
                                <div class="item" data-value="夜光凝视成像">夜光凝视成像</div>
                            </div>
                        </div>
                    </div>
                <%--成像时长&多宫格--%>
                <div class="two wide fields" id="Duration_isMultiGrid" style="display: none">
                    <div class="field" data-tooltip="请输入视频要求成像时长,单位是s,默认为30s">
                        <label>成像时长</label>
                        <input type="text" id="imagingDuration" name="imagingDuration" placeholder="成像时长">
                    </div>
                    <div class="field" data-tooltip="请选择是否多宫格拍摄,默认为否" >
                        <label>是否选择多宫格</label>
                        <div class="ui fluid dropdown selection" tabindex="0">
                            <select id="isMultiGrid" name="multiGrid">
                                <option value=""></option>
                                <option value="true">是</option>
                                <option value="false" selected>否</option>
                            </select><i class="dropdown icon"></i>
                            <div class="default text">否</div>
                            <div class="menu transition hidden" tabindex="-1">
                                <div class="item" data-value="true">是</div>
                                <div class="item" data-value="false">否</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui bottom attached tab segment " data-tab="third">
            <div class="eight fields">
                <%--产品交付方式--%>
                <div class="field">
                    <label>产品交付方式</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="productDeliveryModel" name="productDeliveryModel">
                        <option value=""></option>
                        <option value="大数据平台" <c:if test="${userRequest.productDeliveryModel == '大数据平台'}">selected</c:if>>大数据平台</option>
                        <option value="FTP（专线）" <c:if test="${userRequest.productDeliveryModel == 'FTP（专线）'}">selected</c:if>>FTP（专线）</option>
                        <option value="FTP（互联网）" <c:if test="${userRequest.productDeliveryModel == 'FTP（互联网）'}">selected</c:if>>FTP（互联网）</option>
                        <option value="刻盘自取" <c:if test="${userRequest.productDeliveryModel == '刻盘自取'}">selected</c:if>>刻盘自取</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">产品交付方式</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="大数据平台">大数据平台</div>
                            <div class="item" data-value="FTP（专线）">FTP（专线）</div>
                            <div class="item" data-value="FTP（互联网）">FTP（互联网）</div>
                            <div class="item" data-value="刻盘自取">刻盘自取</div>
                        </div>
                     </div>
                </div>
                <%--产品交付时间--%>
                <div class="field">
                    <label >产品交付时间</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="productDeliveryTime" name="productDeliveryTime">
                            <option value=""></option>
                            <option value="成像12小时" <c:if test="${userRequest.productDeliveryTime == '成像12小时'}">selected</c:if>>成像12小时</option>
                            <option value="成像24小时" <c:if test="${userRequest.productDeliveryTime == '成像24小时'}">selected</c:if>>成像24小时</option>
                            <option value="成像48小时" <c:if test="${userRequest.productDeliveryTime == '成像48小时'}">selected</c:if>>成像48小时</option>
                            <option value="成像一周内" <c:if test="${userRequest.productDeliveryTime == '成像一周内'}">selected</c:if>>成像一周内</option>
                            <option value="完全覆盖后" <c:if test="${userRequest.productDeliveryTime == '完全覆盖后'}">selected</c:if>>完全覆盖后</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">产品交付时间</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="成像12小时">成像12小时</div>
                            <div class="item" data-value="成像24小时">成像24小时</div>
                            <div class="item" data-value="成像48小时">成像48小时</div>
                            <div class="item" data-value="成像一周内">成像一周内</div>
                            <div class="item" data-value="完全覆盖后">完全覆盖后</div>
                        </div>
                    </div>
                </div>
                <%--产品数据格式--%>
                <div class="field">
                    <label>产品数据格式</label>
                    <div class="ui fluid dropdown selection" tabindex="0">
                        <select id="productType" name="productType">
                            <option value=""></option>
                            <option value="GEOTIF" <c:if test="${userRequest.productLevel == 'GEOTIF'}">selected</c:if>>GEOTIF</option>
                            <option value="JPEG" <c:if test="${userRequest.productLevel == 'JPEG'}">selected</c:if>>JPEG</option>
                            <option value="MP4" <c:if test="${userRequest.productLevel == 'MP4'}">selected</c:if>>MP4</option>
                            <option value="AVI" <c:if test="${userRequest.productLevel == 'AVI'}">selected</c:if>>AVI</option>
                        </select><i class="dropdown icon"></i>
                        <div class="default text">产品数据格式</div>
                        <div class="menu transition hidden" tabindex="-1">
                            <div class="item" data-value="GEOTIF">GEOTIF</div>
                            <div class="item" data-value="JPEG">JPEG</div>
                            <div class="item" data-value="MP4">MP4</div>
                            <div class="item" data-value="AVI">AVI</div>
                        </div>
                    </div>
                </div>
                <%--产品级别--%>
                <div class="field"> <label>产品级别</label>
                <div class="ui fluid dropdown selection" tabindex="0">
                    <select id="productLevel" name="productLevel">
                        <option value=""></option>
                        <option value="L1级" <c:if test="${userRequest.productLevel == 'L1级'}">selected</c:if>>L1级</option>
                        <option value="L1A级" <c:if test="${userRequest.productLevel == 'L1A级'}">selected</c:if>>L1A级</option>
                        <option value="L1B级" <c:if test="${userRequest.productLevel == 'L1B级'}">selected</c:if>>L1B级</option>
                        <option value="L2级" <c:if test="${userRequest.productLevel == 'L2级'}">selected</c:if>>L2级</option>
                        <option value="L4级" <c:if test="${userRequest.productLevel == 'L4级'}">selected</c:if>>L4级</option>
                        <option value="L5级" <c:if test="${userRequest.productLevel == 'L5级'}">selected</c:if>>L5级</option>
                    </select><i class="dropdown icon"></i>
                    <div class="default text">产品级别</div>
                    <div class="menu transition hidden" tabindex="-1">
                        <div class="item" data-value="L1级">L1级</div>
                        <div class="item" data-value="L1A级">L1A级</div>
                        <div class="item" data-value="L1B级">L1B级</div>
                        <div class="item" data-value="L2级">L2级</div>
                        <div class="item" data-value="L4级">L4级</div>
                        <div class="item" data-value="L5级">L5级</div>
                    </div>
                </div>
            </div>
                <%--谱段要求--%>
                <div class="field"> <label>谱段要求</label>
                <div class="ui fluid dropdown selection" tabindex="0">
                    <select id="spectrum" name="spectrum">
                        <option value=""></option>
                        <option value="没有要求" <c:if test="${userRequest.productLevel == '没有要求'}">selected</c:if>>没有要求</option>
                        <option value="≥三谱段（红、绿、蓝）" <c:if test="${userRequest.productLevel == '≥三谱段（红、绿、蓝）'}">selected</c:if>>≥三谱段（红、绿、蓝）</option>
                        <option value="≥四谱段（红、绿、蓝、近红外）" <c:if test="${userRequest.productLevel == '≥四谱段（红、绿、蓝、近红外）'}">selected</c:if>>≥四谱段（红、绿、蓝、近红外）</option>
                    </select><i class="dropdown icon"></i>
                    <div class="default text">谱段要求</div>
                    <div class="menu transition hidden" tabindex="-1">
                        <div class="item" data-value="没有要求">没有要求</div>
                        <div class="item" data-value="≥三谱段（红、绿、蓝）">≥三谱段（红、绿、蓝）</div>
                        <div class="item" data-value="≥四谱段（红、绿、蓝、近红外）">≥四谱段（红、绿、蓝、近红外）</div>
                    </div>
                </div>
            </div>
                <%--图像几何要求--%>
                <div class="field">
                    <label>几何要求</label>
                <div class="ui fluid dropdown selection" tabindex="0">
                    <select id="geometry_request" name="geometry_request">
                        <option value=""></option>
                        <option value="小于100" <c:if test="${userRequest.geometryRequest == '小于100'}">selected</c:if>>小于100</option>
                        <option value="小于200" <c:if test="${userRequest.geometryRequest == '小于200'}">selected</c:if>>小于200</option>

                    </select><i class="dropdown icon"></i>
                    <div class="default text">几何要求</div>
                    <div class="menu transition hidden" tabindex="-1">
                        <div class="item" data-value="小于100">小于100</div>
                        <div class="item" data-value="小于200">小于200</div>
                    </div>
                </div>
            </div>
                <%--图像辐射要求--%>
                <div class="field"> <label>辐射要求</label>
                <div class="ui fluid dropdown selection" tabindex="0">
                    <select id="radiation_request" name="radiation_request">
                        <option value=""></option>
                        <option value="无明显辐射问题" <c:if test="${userRequest.radiation_request == '无明显辐射问题'}">selected</c:if>>无明显辐射问题</option>
                        <option value="无辐射问题" <c:if test="${userRequest.radiation_request == '无辐射问题'}">selected</c:if>>无辐射问题</option>

                    </select><i class="dropdown icon"></i>
                    <div class="default text">辐射要求</div>
                    <div class="menu transition hidden" tabindex="-1">
                        <div class="item" data-value="无明显辐射问题">无明显辐射问题</div>
                        <div class="item" data-value="无辐射问题">无辐射问题</div>
                    </div>
                </div>
            </div>
            </div>

        </div>
        <div class="ui bottom attached tab segment " data-tab="fourth">
            没想好
        </div>

    </div>
    <div id="cesiumContainer" style="margin-bottom: 0.75rem">

        <div class="loggingMessage">
        </div>
    </div>
    <input type="hidden" id="imagingWkt" name="imagingWkt" >
    <textarea  style="display:none" class="ready-only" id="imagingPara" name="imagingParaTxt">${userRequest.imagingParaTxt}</textarea>

    <div class="bottom">
    <div class="field">
        <div class="sixteen fields" >
            <c:if test="${userRequest == null}">
                <div class="field" data-tooltip="下一步填写拍摄要求">
                    <input class="ui teal submit button" type="submit" name="isSubmit" value="下一步" >
                </div>
            </c:if>
            <c:if test="${userRequest != null}">
                <div class="field">
                    <input class="ui teal submit button" type="submit" name="isSubmit" value="保存修改">
                </div>
            </c:if>
            <c:if test="${userRequest != null}">
                <c:if test="${author.id eq submitter.id}">
                    <div class="field">
                        <a class="ui teal submit button"  href="cancelUserRequest?userRequestId=${userRequest.id}">Cancel Request</a>
                     </div>
                </c:if>
                <c:if test="${author.id != submitter.id}">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <div class="field">
                        <a class="ui blue submit button"  href="cancelUserRequest?userRequestId=${userRequest.id}">Cancel Request</a>
                        </div>
                    </sec:authorize>
                </c:if>
            </c:if>
        </div>
    </div></div>
</form>
</body>
</html>

