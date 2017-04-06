package com.zero.doplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignRecordActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_record);

        setTitleText("打卡记录");
    }
}
