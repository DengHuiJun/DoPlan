package com.zero.doplan.util;

import android.content.SharedPreferences;

import com.zero.doplan.AppContext;

import static android.content.Context.MODE_PRIVATE;

/**
 * SharedPreferences Utils
 * Created by xuanke_huang on 2017/9/18.
 */
@SuppressWarnings("unused")
public class SharePreferencesUtils {
    /**
     * file name
     */
    private static final String P_DE_FILE = "canteen_de_file";

    public static void putInt(String key, int value) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key, int defaultValue) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        return preferences.getInt(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

    public static void putFloat(String key, float value) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static float getFloat(String key, float defaultValue) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        return preferences.getFloat(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLong(String key, long defaultValue) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        return preferences.getLong(key, defaultValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences preferences = AppContext.sContext.getSharedPreferences(P_DE_FILE, MODE_PRIVATE);
        return preferences.getString(key, defaultValue);
    }


}
