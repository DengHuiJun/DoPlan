package com.zero.doplan.canteen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zero.base.MenuPhotoHelper
import com.zero.doplan.AppContext
import com.zero.doplan.R
import com.zero.room.entity.Menu

/**
 * Created by zerogeek on 2018/3/14.
 */
class MenuListAdapter(context : Context, listener : ClickItemOrder) : BaseAdapter()  {

    var mData: List<Menu> = ArrayList()

    var orderListener : ClickItemOrder = listener

    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount() = mData.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = convertView ?: layoutInflater.inflate(R.layout.list_item_menu, parent, false)

        val data : Menu = mData[position]

        val img : ImageView = v.findViewById(R.id.menuPhotoIv)
        val name : TextView = v.findViewById(R.id.menuNameTv)
        val price : TextView = v.findViewById(R.id.priceTv)
        val btn : Button =v.findViewById(R.id.okBtn)

        Glide.with(AppContext.sContext).load(MenuPhotoHelper.IMG[data.imgKey]).into(img)
        name.text = data.name
        price.text = "元：" + data.price

        btn.setOnClickListener {
            orderListener.orderMenu(data.id)
        }
        return v
    }

    override fun getItem(position: Int): Menu {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return mData[position].id
    }

    interface ClickItemOrder {
        fun orderMenu(id: Long)
    }
}