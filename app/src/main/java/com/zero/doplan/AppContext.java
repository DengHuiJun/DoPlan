package com.zero.doplan;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Allen.D on 17/1/10.
 */

public class AppContext extends Application {

    public static Context sContext;
    public static final boolean ENCRYPTED = false;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        initDataBase();
    }

    private void initDataBase() {

    }

}
