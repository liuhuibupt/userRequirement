package com.charmingglobe.gr.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by PANZHENG on 2018/2/3.
 */
public class ImagingParaConverter {

    public static String toSring(Map<String, String> paraMap) {
        String paraTxt = "";
        if (null != paraMap) {
           Set<String> keySet =  paraMap.keySet();
           for (String key: keySet) {
               if (!"".equals(paraTxt)) {
                   paraTxt += "\n";
               }
               paraTxt += key + "=" + paraMap.get(key);
           }
        }
        return paraTxt;
    }

    public static Map<String, String> toMap(String para) {
        Map<String, String> paraMap = null;
        if (null != para && !"".equals(para)) {
            paraMap = new HashMap<String, String>();
            para = para.trim();
            String[] paraArray = para.split("\r\n");
            for (int i = 0; i < paraArray.length; i++) {
                String paraLine = paraArray[i];
                if (null!= paraLine && !"".equals(paraLine)) {
                    if (paraLine.contains("imagingWkt=")) {
                        paraMap.put("imagingWkt", paraLine.replace("imagingWkt=", ""));
                    }
                    if (paraLine.contains("elevation=")) {
                        paraMap.put("elevation", paraLine.replace("elevation=", ""));
                    }
                    if (paraLine.contains("q1=")) {
                        paraMap.put("q1", paraLine.replace("q1=", ""));
                    }
                    if (paraLine.contains("q2=")) {
                        paraMap.put("q2", paraLine.replace("q2=", ""));
                    }
                    if (paraLine.contains("q3=")) {
                        paraMap.put("q3", paraLine.replace("q3=", ""));
                    }
                    if (paraLine.contains("startTime=")) {
                        paraMap.put("startTime", paraLine.replace("startTime=", ""));
                    }
                    if (paraLine.contains("duration=")) {
                        paraMap.put("duration", paraLine.replace("duration=", ""));
                    }
                    if (paraLine.contains("framePeriod=")) {
                        paraMap.put("framePeriod", paraLine.replace("framePeriod=", ""));
                    }
                    if (paraLine.contains("framePeriod=")) {
                        paraMap.put("framePeriod", paraLine.replace("framePeriod=", ""));
                    }
                    if (paraLine.contains("exposeTime=")) {
                        paraMap.put("exposeTime", paraLine.replace("exposeTime=", ""));
                    }
                    if (paraLine.contains("attitudeChangeFlag=")) {
                        paraMap.put("attitudeChangeFlag", paraLine.replace("attitudeChangeFlag=", ""));
                    }
                }
            }
        }

        return paraMap;
    }
}
