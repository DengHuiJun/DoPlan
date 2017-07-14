package com.zero.doplan.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Allen.D on 17/4/8.
 */

public class TimeUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());

    public static String getFormatDate(long time) {
        return SDF.format(new Date(time));
    }

    public static int getDaysByTwoTime(long start, long end) {
        return (int) ((end - start) / 86400000);
    }

    /**
     * 获取当天凌晨2点整时刻
     * @return
     */
    public static long getTodayKey() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        c.set(Calendar.HOUR_OF_DAY, 2);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTimeInMillis();
    }

}
