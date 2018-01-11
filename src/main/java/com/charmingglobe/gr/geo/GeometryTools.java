package com.charmingglobe.gr.geo;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;

/**
 * Created by PANZHENG on 2017/2/23.
 */
@Component
public class GeometryTools {

    private GeometryFactory geometryFactory;

    public GeometryTools() {
        geometryFactory = new GeometryFactory();
    }

    public Geometry getGeometryFromWKT(String wkt) {
        WKTReader wktReader = new WKTReader();
        Geometry geometry = null;
        try {
            geometry = wktReader.read(wkt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return geometry;
    }

    public Polygon getLocationFromShp(String shpPath) {
        ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
        try {
            ShapefileDataStore sds = (ShapefileDataStore) dataStoreFactory.createDataStore(new File(shpPath).toURI().toURL());
            SimpleFeatureSource featureSource = sds.getFeatureSource();
            SimpleFeatureIterator simpleFeatureIterator = featureSource.getFeatures().features();

            while (simpleFeatureIterator.hasNext()) {
                SimpleFeature feature = simpleFeatureIterator.next();
                Iterator<Property> it = feature.getProperties().iterator();

                while (it.hasNext()) {
                    System.out.println("--- --- --- --- --- --- --- ---");
                    Property pro = it.next();
                    Object obj = pro.getValue();
                    System.out.println("class=" + obj);

                    if (obj instanceof Polygon) {
                        Polygon location = (Polygon) obj;
                        String wktText = location.toText();
                        System.out.println(wktText);
                        return location;
                    }

                    if (obj instanceof MultiPolygon) {
                        MultiPolygon multiPolygon = (MultiPolygon) obj;
                        String wktText = multiPolygon.toText();
                        System.out.println(wktText);

                        Coordinate[] coordinate = multiPolygon.getCoordinates();
                        Polygon polygon = geometryFactory.createPolygon(coordinate);
                        String wktText1 = polygon.toText();
                        System.out.println(wktText1);

                        return polygon;
                    }

                    if (obj instanceof MultiLineString) {
                        MultiLineString multiLineString = (MultiLineString) obj;
                        String wktText = multiLineString.toText();
                        System.out.println(wktText);
                        Geometry geometry = getGeometryFromWKT(wktText.replace("MULTILINESTRING", "POLYGON"));
                        return (Polygon)geometry;
                    }
                }
            }
            simpleFeatureIterator.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Polygon getLocationFromCoordinate(double[] x, double[] y) {
        Coordinate[] coordinates = new Coordinate[5];
        for (int i = 0; i < 5; i++) {
            coordinates[i] = new Coordinate(x[i], y[i]);
        }
        Polygon polygon = geometryFactory.createPolygon(coordinates);
        return polygon;
    }
}
