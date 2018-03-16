package com.zero.doplan.canteen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import com.zero.doplan.AppContext
import com.zero.doplan.R
import com.zero.doplan.util.ToastUtil
import com.zero.room.DBManager
import com.zero.room.Injection
import com.zero.room.entity.Menu
import com.zero.room.entity.Order
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), MenuListAdapter.ClickItemOrder {
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

    var keyWords : String = ""

    val handler = Handler()

    val searchRunnable = Runnable {
        loadData()
    }

    private lateinit var listAdapter :MenuListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        cancel_tv.setOnClickListener {
            finish()
        }

        search_keyword_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                keyWords = p0.toString()
                handler.post(searchRunnable)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                handler.removeCallbacks(searchRunnable)
            }
        })

        listAdapter = MenuListAdapter(this, this, false)

        searchLv.adapter = listAdapter

    }

    private fun loadData() {
        DBManager.db.menuDao().searchMenuByKeyWord("%" + keyWords +"%")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it != null && !it.isEmpty()) {
                        listAdapter.mData = it
                        listAdapter.notifyDataSetChanged()
                    }
                }
    }

}
