package com.zero.doplan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Allen.D on 17/1/10.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

    }

    @Override
    public void onClick(View v) {

    }
}
