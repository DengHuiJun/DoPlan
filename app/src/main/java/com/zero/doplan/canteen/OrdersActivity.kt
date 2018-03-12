package com.zero.doplan.canteen

import android.os.Bundle
import com.zero.doplan.kt.BaseActionBarActivity
import com.zero.doplan.R

class OrdersActivity : BaseActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        setTitleText("我的订单")
    }
}
