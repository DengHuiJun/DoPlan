package com.zero.doplan.canteen

import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.zero.doplan.AppContext
import com.zero.doplan.R
import com.zero.room.DBManager
import com.zero.room.Injection
import com.zero.room.entity.Menu
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : BaseActionBarActivity(), MenuListAdapter.ClickItemOrder {
    override fun orderMenu(m : Menu) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("提示")
                .setMessage("是否取消" + m.name + "的订单")
                .setPositiveButton("确定") { _, _ ->
                    DBManager.db.orderDao().deleteOrderById(m.orderId)
                    refresh()
                }
                .setNegativeButton("取消") { _, _ -> }
        builder.create()
        builder.show()
    }

    private lateinit var mAdapter : MenuListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        setTitleText("我的订单")

        mAdapter = MenuListAdapter(this, this, true)

        mAdapter.mData = getDataList()
        orderLv.adapter = mAdapter
    }

    private fun refresh() {
        mAdapter.mData = getDataList()
        mAdapter.notifyDataSetChanged()
    }

    private fun getDataList() : List<Menu> {
        return Injection.provideUserDataSource(this).getOrderMenu(AppContext.sUserId)
    }
}
