package com.zero.doplan.util;

import android.widget.Toast;

import com.zero.doplan.AppContext;

/**
 * Created by Allen.D on 17/7/14.
 */

public class ToastUtil {

    public static void showShort(String msg) {
        Toast.makeText(AppContext.sContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(AppContext.sContext, msg, Toast.LENGTH_LONG).show();
    }
}
