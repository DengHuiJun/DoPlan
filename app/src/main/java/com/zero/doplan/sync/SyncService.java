package com.zero.doplan.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Allen.D on 17/two/25.
 */

public class SyncService extends Service {

    private static SyncAdapter sSyncAdapter;

    private static final Object mLocal = new Object();

    @Override
    public void onCreate() {

        synchronized (mLocal) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return sSyncAdapter.getSyncAdapterBinder();
    }
}
