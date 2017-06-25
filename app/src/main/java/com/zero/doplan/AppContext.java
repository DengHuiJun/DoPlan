package com.zero.doplan;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.zero.doplan.greendao.DaoMaster;
import com.zero.doplan.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Allen.D on 17/1/10.
 */

public class AppContext extends Application {

    public static Context sContext;
    public static final boolean ENCRYPTED = false;
    private DaoSession mDaoSession;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        initGreenDao();
    }

    public static DaoSession getDaoSession() {
        return ((AppContext) sContext).mDaoSession;
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? "doplan-db-encrypted" : "doplan-db");
        // TODO 加密处理 还没做
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

}
