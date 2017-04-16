package com.zero.doplan.util;

import android.util.Log;

import com.zero.doplan.BuildConfig;

/**
 * Created by Allen.D on 17/4/8.
 */

public class LogUtil {
    private static final String GOAL_TAG = "Allen";

    private LogUtil() {

    }

    public static void d(String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.d(GOAL_TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            Log.d(tag, msg);
        }
    }
}
