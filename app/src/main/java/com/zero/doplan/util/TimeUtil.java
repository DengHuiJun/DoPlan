package com.zero.doplan.util;

import java.text.SimpleDateFormat;
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


}
