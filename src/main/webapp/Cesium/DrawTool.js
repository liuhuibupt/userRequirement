/**
 * Created by yi on 2017/12/2.
 */
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
function startMap(viewer, loggingPolygonFun, loggingMarkFun, loggingMouseMoveFun) {
    viewer.scene.camera.setView({
        destination: Cesium.Cartesian3.fromDegrees(116, 39, 10000000.0),
    });

    viewer.scene.camera.zoomOut(100000000);
    viewer.scene.camera.moveDown(4000000);

    drawHelper = new DrawHelper(viewer, loggingMarkFun);
    var handler;
    handler = new Cesium.ScreenSpaceEventHandler(viewer.scene.canvas);
    handler.setInputAction(function(movement) {
        if (isDrawPolygon) {
            removeAll(viewer.scene);
            drawPolygon(loggingPolygonFun);
        }
    }, Cesium.ScreenSpaceEventType.RIGHT_CLICK );

    handler.setInputAction(function(movement) {
        var cartesian = viewer.camera.pickEllipsoid(movement.endPosition, scene.globe.ellipsoid);
        if (cartesian) {
            var cartographic = Cesium.Cartographic.fromCartesian(cartesian);
            var longitudeString = Cesium.Math.toDegrees(cartographic.longitude).toFixed(6);
            var latitudeString = Cesium.Math.toDegrees(cartographic.latitude).toFixed(6);

            var text =
                'Lon: ' + ('   ' + longitudeString) + '\u00B0' +
                '\nLat: ' + ('   ' + latitudeString) + '\u00B0';
            if (loggingMouseMoveFun != undefined) {
                loggingMouseMoveFun(text);
            }
        }
    }, Cesium.ScreenSpaceEventType.MOUSE_MOVE );
    scene = viewer.scene;
	scene.primitives.add(b);
}

function drawPoint(loggingFunction) {
    if (isDrawPolygon) {
        removeAll(scene);
    }
    drawHelper.startDrawingMarker({
        callback: function(position) {
            listenerMark(position, loggingFunction);
        }
    });
}

function drawPolygon(loggingFunction) {
    removeAll(scene);
    drawHelper.startDrawingPolygon({
        callback: function(positions) {
            listenerPolygon(positions, loggingFunction);
        }
    });
}

function listenerMark(position, loggingFunction) {
    isDrawPolygon = false;
    var cartographic = Cesium.Cartographic.fromCartesian(new Cesium.Cartesian3(position.x, position.y, position.z));
    var longitudeString = Cesium.Math.toDegrees(cartographic.longitude).toFixed(6);
    var latitudeString = Cesium.Math.toDegrees(cartographic.latitude).toFixed(6);
    if (loggingFunction != undefined) {
        loggingFunction(longitudeString, latitudeString);
    }
    billboard.position = position;
    billboard.show = true;
    billboard.setEditable();
    drawPoint(loggingFunction);
}

function listenerPolygon(positions, loggingFunction) {
    isDrawPolygon = true;
    if (loggingFunction != undefined) {
        loggingFunction(positions);
    }
    addPolygon(positions);
}

function setPointPosition(lon, lat) {
    var cartesian = Cesium.Cartesian3.fromDegrees(lon, lat, 0);
    if (!isDrawPolygon) {
        billboard.position = cartesian;
        billboard.show = true;
        billboard.setEditable();
    }
}

function addPolygon(positions) {
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
}

function addPolygonFromGeo(geoJson) {
    isDrawPolygon = true;
    var cartesianArray = [];
    for(var i = 0; i < geoJson.length; i++){
        var lon = geoJson[i][0];
        var lat = geoJson[i][1];
        var cartesian = Cesium.Cartesian3.fromDegrees(lon, lat, 0);
        cartesianArray.push(cartesian);
    }
    addPolygon(cartesianArray);
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
        scale : 0.7,
        image: './Cesium/img/marker.png',
        color : new Cesium.Color(1.0, 0.0, 1.0, 1.0)
    });
    var primitiveCollection = [];
    for(var i = 0; i< scene.primitives.length; i++) {
        var o = scene.primitives.get(i);
        if (o._textureAtlasGUID != undefined || o._createPrimitive != undefined) {
            primitiveCollection.push(o);
        }
    }
    for(var i = 0; i < primitiveCollection.length; i++) {
        scene.primitives.remove(primitiveCollection[i]);
    }
    scene.primitives.add(b);
}