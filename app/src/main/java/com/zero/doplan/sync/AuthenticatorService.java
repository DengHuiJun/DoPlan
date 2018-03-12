package com.zero.doplan.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Allen.D on 17/two/25.
 */

public class AuthenticatorService extends Service {

    private Authenticator mAuth;

    @Override
    public void onCreate() {
        mAuth = new Authenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAuth.getIBinder();
    }
}
