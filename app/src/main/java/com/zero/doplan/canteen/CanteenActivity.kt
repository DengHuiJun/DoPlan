package com.zero.doplan.canteen

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.zero.doplan.R
import kotlinx.android.synthetic.main.activity_canteen.*

class CanteenActivity : BaseActionBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canteen)

        setTitleText("所有食堂")

        setRighMenuVisible(true)
        setRightMenuIcon(R.drawable.ic_actionbar_head)

        listView.adapter = CanteenAdapter(this)
        listView.setOnItemClickListener({
            _, _, position, id ->
                val ada = listView.adapter
                if (ada is CanteenAdapter) {
                    toDetail(ada.getItem(position).name, id.toInt())
                }
        })
    }

    override fun onRightMenuClick(item: MenuItem) {
        super.onRightMenuClick(item)
        startActivity(Intent(this, OrdersActivity::class.java))
    }

    private fun toDetail(title: String, id : Int) {
        val intent = Intent(this, DetailCanteenActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
