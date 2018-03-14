package com.zero.doplan.canteen

import android.os.Bundle

import com.zero.doplan.kt.BaseActionBarActivity
import com.zero.doplan.R


class DetailCanteenActivity : BaseActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_canteen)

        val t = intent.getStringExtra("title")
        val id = intent.getIntExtra("id", 0)

        setTitleText(t)
    }
}
