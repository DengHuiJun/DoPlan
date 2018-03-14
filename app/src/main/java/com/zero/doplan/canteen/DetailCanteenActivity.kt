package com.zero.doplan.canteen

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.zero.base.DBConstants

import com.zero.doplan.kt.BaseActionBarActivity
import com.zero.doplan.R
import com.zero.room.DBManager
import com.zero.room.entity.Menu
import kotlinx.android.synthetic.main.activity_detail_canteen.*


class DetailCanteenActivity : BaseActionBarActivity(), MenuListAdapter.ClickItemOrder {

    var canteenId : Int = DBConstants.ONE_CANTEEN
    private lateinit var listAdapter : MenuListAdapter

    override fun orderMenu(id: Long) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_canteen)

        val t = intent.getStringExtra("title")
        canteenId = intent.getIntExtra("id", 0)

        setTitleText(t)

        listAdapter = MenuListAdapter(this, this)
        menuLv.adapter = listAdapter

        changeBgColor(DBConstants.TYPE_MORN)

        morningTV.setOnClickListener {
            changeBgColor(DBConstants.TYPE_MORN)

        }

        afternoonTv.setOnClickListener {
            changeBgColor(DBConstants.TYPE_NOON)
        }

        dinnerTv.setOnClickListener {
            changeBgColor(DBConstants.TYPE_NIGHT)
        }
    }

    private fun changeBgColor(type : Int) {
        when (type) {
            1 -> {
                morningTV.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                afternoonTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                dinnerTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

                listAdapter.mData = getDataList(1)
            }
            2 -> {
                morningTV.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                afternoonTv.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                dinnerTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                listAdapter.mData = getDataList(2)

            }
            3 -> {
                morningTV.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                afternoonTv.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                dinnerTv.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
                listAdapter.mData = getDataList(3)
            }
        }
        listAdapter.notifyDataSetChanged()
    }

    private fun getDataList(type: Int) : List<Menu> {
        return DBManager.db.menuDao().getMenuByTypeAndCanteen(type, canteenId)
    }
}
