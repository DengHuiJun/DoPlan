package com.zero.doplan.canteen

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import com.zero.base.DBConstants
import com.zero.doplan.AppContext
import com.zero.doplan.BaseActionBarActivity
import com.zero.doplan.util.ToastUtil
import com.zero.room.DBManager
import com.zero.room.entity.Menu
import com.zero.room.entity.Order
import kotlinx.android.synthetic.main.activity_detail_canteen.*


class DetailCanteenActivity : BaseActionBarActivity(), MenuListAdapter.ClickItemOrder {

    var canteenId: Int = DBConstants.ONE_CANTEEN
    private lateinit var listAdapter: MenuListAdapter

    override fun orderMenu(m: Menu) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("提示")
                .setMessage("是否预订" + m.name)
                .setPositiveButton("确定") { _, _ ->
                    if (m.id > 0) {
                        val t = System.currentTimeMillis()
                        val o = Order()
                        o.menuId = m.id
                        o.createdTime = t
                        o.lastUpdateTime = t
                        o.userId = AppContext.sUserId
                        DBManager.db.orderDao().insert(o)
                        ToastUtil.showShort("预订成功.")
                    } else {
                        ToastUtil.showShort("预订失败.")
                    }
                }
                .setNegativeButton("取消") { _, _ -> }
        builder.create()
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        val rightMenuItem = menu.add(0, MENU_ITEM_ID_RIGHT_SEARCH, 0, "搜索")
        rightMenuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_action_search)
        rightMenuItem.isEnabled = true
        MenuItemCompat.setShowAsAction(rightMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS or MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var consume = true
        when (item.itemId) {
            MENU_ITEM_ID_RIGHT_SEARCH -> onSearchClick()
            else -> consume = super.onOptionsItemSelected(item)
        }
        return consume
    }

    private fun onSearchClick() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    override fun onRightMenuClick(item: MenuItem) {
        startActivity(Intent(this, OrdersActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_canteen)

        setRighMenuVisible(true)
        setRightMenuIcon(R.drawable.ic_actionbar_head)

        val t = intent.getStringExtra("title")
        canteenId = intent.getIntExtra("id", 0)

        setTitleText(t)

        listAdapter = MenuListAdapter(this, this, false)
        menuLv.adapter = listAdapter

        changeDataAndBgColor(DBConstants.TYPE_MORN)

        morningTV.setOnClickListener {
            changeDataAndBgColor(DBConstants.TYPE_MORN)

        }

        afternoonTv.setOnClickListener {
            changeDataAndBgColor(DBConstants.TYPE_NOON)
        }

        dinnerTv.setOnClickListener {
            changeDataAndBgColor(DBConstants.TYPE_NIGHT)
        }
    }

    private fun changeDataAndBgColor(type: Int) {
        when (type) {
            DBConstants.TYPE_MORN -> {
                morningTV.setBackgroundColor(ContextCompat.getColor(this, R.color.new_color_text_c6))
                afternoonTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                dinnerTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            }
            DBConstants.TYPE_NOON -> {
                morningTV.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                afternoonTv.setBackgroundColor(ContextCompat.getColor(this, R.color.new_color_text_c6))
                dinnerTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            }
            DBConstants.TYPE_NIGHT -> {
                morningTV.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                afternoonTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                dinnerTv.setBackgroundColor(ContextCompat.getColor(this, R.color.new_color_text_c6))
            }
            else -> {
                ToastUtil.showShort("类型错误~")
                return
            }
        }
        listAdapter.mData = getDataList(type)
        listAdapter.notifyDataSetChanged()
    }

    private fun getDataList(type: Int): List<Menu> {
        return DBManager.db.menuDao().getMenuByTypeAndCanteen(type, canteenId)
    }
}
