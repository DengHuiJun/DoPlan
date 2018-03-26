package com.zero.doplan.util;

import android.support.design.widget.Snackbar;
import android.view.View;
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

    public static void snackBarShort(View v, String msg) {
        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }
}
