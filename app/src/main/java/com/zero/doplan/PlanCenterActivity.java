package com.zero.doplan;

import android.os.Bundle;

import butterknife.ButterKnife;

public class PlanCenterActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_center);
    }

    @Override
    protected void initButterKnife() {
        ButterKnife.bind(this);
    }
}
