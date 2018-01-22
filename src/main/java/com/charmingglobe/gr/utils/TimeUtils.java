package com.charmingglobe.gr.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by PANZHENG on 2018/1/18.
 */
public class TimeUtils {

    public static Date getZeroOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }
}
