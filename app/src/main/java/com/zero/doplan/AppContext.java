package com.zero.doplan;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.zero.doplan.util.SharePreferencesUtils;
import com.zero.room.CanteenDatabase;
import com.zero.room.DBManager;
import com.zero.room.Injection;

/**
 * Created by Allen.D on 17/1/10.
 */

public class AppContext extends Application {

    public static Context sContext;
    public static long sUserId = -1;

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
        DBManager.INSTANCE.init(this);

        if (!SharePreferencesUtils.getBoolean("INIT_DB", false)) {
            DBManager.INSTANCE.firstInit();
            SharePreferencesUtils.putBoolean("INIT_DB", true);
        }
    }

}
