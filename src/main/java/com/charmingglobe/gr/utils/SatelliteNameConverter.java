package com.charmingglobe.gr.utils;

/**
 * Created by PANZHENG on 2018/1/21.
 */
public class SatelliteNameConverter {

    public static String getSatelliteId(String satelliteName) {
        String satelliteId = satelliteName;
        if ("".equals(satelliteName)) {
            satelliteId = "";
        }
        return satelliteId;
    }
}
