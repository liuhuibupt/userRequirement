/**
 * Created by yi on 2017/12/2.
 */

$(document).ready(function(){
    var arcgisImageProvider = new Cesium.ArcGisMapServerImageryProvider({
        url: "http://10.10.20.234:6080/arcgis/rest/services/World13/MapServer"
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

    viewer.scene.camera.setView({
        destination: Cesium.Cartesian3.fromDegrees(116, 39, 10000000.0),
    });

    var label = viewer.entities.add({
        label : {
            show : false,
            showBackground : true,
            font : '14px monospace',
            horizontalOrigin : Cesium.HorizontalOrigin.LEFT,
            verticalOrigin : Cesium.VerticalOrigin.TOP,
            pixelOffset : new Cesium.Cartesian2(15, 0)
        }
    });

    var handler;
    handler = new Cesium.ScreenSpaceEventHandler(viewer.scene.canvas);
    handler.setInputAction(function(movement) {
        if (isDrawPolygon) {
            removeAll(viewer.scene);
            drawPolygon();
        }

    }, Cesium.ScreenSpaceEventType.RIGHT_CLICK );

    handler.setInputAction(function(movement) {
        var cartesian = viewer.camera.pickEllipsoid(movement.endPosition, scene.globe.ellipsoid);
        if (cartesian) {
            var cartographic = Cesium.Cartographic.fromCartesian(cartesian);
            var longitudeString = Cesium.Math.toDegrees(cartographic.longitude).toFixed(6);
            var latitudeString = Cesium.Math.toDegrees(cartographic.latitude).toFixed(6);

            label.position = cartesian;
            label.label.show = true;
            label.label.text =
                'Lon: ' + ('   ' + longitudeString) + '\u00B0' +
                '\nLat: ' + ('   ' + latitudeString) + '\u00B0';
        } else {
            label.label.show = false;
        }
    }, Cesium.ScreenSpaceEventType.MOUSE_MOVE );
    startMap(viewer);
});
var isDrawPolygon = false;
var drawHelper;
var scene;

var b = new Cesium.BillboardCollection();
var billboard = b.add({
    name : "test",
    show : false,
    pixelOffset : new Cesium.Cartesian2(0, 0),
    eyeOffset : new Cesium.Cartesian3(0.0, 0.0, 0.0),
    horizontalOrigin : Cesium.HorizontalOrigin.CENTER,
    verticalOrigin : Cesium.VerticalOrigin.CENTER,
    scale : 0.7,
    image: './Cesium/img/marker.png',
    color : new Cesium.Color(1.0, 0.0, 1.0, 1.0)
});
function startMap(viewer) {
    drawHelper = new DrawHelper(viewer);
    scene = viewer.scene;
	scene.primitives.add(b);
}

function drawPoint() {
    drawHelper.startDrawingMarker({
        callback: function(position) {
            listenerMark(position);
        }
    });
}

function drawPolygon() {
    removeAll(scene);
    drawHelper.startDrawingPolygon({
        callback: function(positions) {
            listenerPolygon(positions);
        }
    });
}

function listenerMark(position) {
    isDrawPolygon = false;
    var cartographic = Cesium.Cartographic.fromCartesian(new Cesium.Cartesian3(position.x, position.y, position.z));
    var longitudeString = Cesium.Math.toDegrees(cartographic.longitude).toFixed(6);
    var latitudeString = Cesium.Math.toDegrees(cartographic.latitude).toFixed(6);
    loggingMark(longitudeString, latitudeString);
    billboard.position = position;
    billboard.show = true;
    billboard.setEditable();
    drawPoint();
}

function listenerPolygon(positions) {
    isDrawPolygon = true;
    loggingPolygon(positions);
    var polygon = new DrawHelper.PolygonPrimitive({
        positions: positions,
        material : new Cesium.Material({
            fabric : {
                type : 'Color',
                uniforms : {
                    color : new Cesium.Color(1.0, 0.0, 0.0, 0.3)
                }
            }
        }),
        strokeColor:new Cesium.Color(1.0, 0.0, 0.0, 1.0),
        strokeWidth:100
    });
    scene.primitives.add(polygon);
    //setPostion(event.positions, "polygon");
    polygon.setEditable();
    polygon.addListener('onEdited', function(event) {
        // loggingMessage('Polygon edited, ' + event.positions.length + ' points');
    });
}

function removeAll(scene) {
    b = new Cesium.BillboardCollection();
    billboard = b.add({
        name : "test",
        show : false,
        pixelOffset : new Cesium.Cartesian2(0, 0),
        eyeOffset : new Cesium.Cartesian3(0.0, 0.0, 0.0),
        horizontalOrigin : Cesium.HorizontalOrigin.CENTER,
        verticalOrigin : Cesium.VerticalOrigin.CENTER,
        scale : 1.0,
        image: './Cesium/img/glyphicons_242_google_maps.png',
        color : new Cesium.Color(1.0, 0.0, 1.0, 1.0)
    });
    scene.primitives.removeAll();
    scene.primitives.add(b);
}