package com.zero.doplan;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;

public class SignRecordActivity extends BaseActionBarActivity {

    @BindView(R.id.sign_record_rv)
    RecyclerView mRecordsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_record);

        setTitleText("打卡记录");
    }
}
