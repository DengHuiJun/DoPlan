package com.zero.doplan.ui

import android.os.Bundle
import com.zero.doplan.kt.BaseActionBarActivity
import com.zero.doplan.R

import kotlinx.android.synthetic.main.activity_add_or_edit_plan.*


class AddOrEditPlanActivity : BaseActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit_plan)

        setTitleText("添加计划")
    }
}
