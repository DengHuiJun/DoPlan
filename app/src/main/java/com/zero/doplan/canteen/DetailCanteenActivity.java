package com.zero.doplan.canteen;

import android.os.Bundle;

import com.zero.doplan.BaseActionBarActivity;
import com.zero.doplan.R;

import butterknife.ButterKnife;

public class DetailCanteenActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_canteen);

        String t = getIntent().getStringExtra("title");
        int id = getIntent().getIntExtra("id", 0);

        setTitleText(t);
    }

    @Override
    protected void initButterKnife() {
        ButterKnife.bind(this);
    }
}
