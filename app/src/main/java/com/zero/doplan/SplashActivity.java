package com.zero.doplan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Allen.D on 17/2/26.
 */

public class SplashActivity extends BaseActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goMainActivity();
                finish();
            }
        }, 600L);
    }

    private void goMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }
}
