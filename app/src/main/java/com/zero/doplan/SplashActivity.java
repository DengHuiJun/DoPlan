package com.zero.doplan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.zero.doplan.util.LogUtil;

/**
 * Created by Allen.D on 17/2/26.
 */

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.d(TAG + "onCreate");
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goMainActivity();
                finish();
            }
        }, 300L);
    }

    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
