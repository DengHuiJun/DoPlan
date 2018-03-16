package com.zero.doplan.canteen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zero.doplan.AppContext
import com.zero.doplan.R

/**
 * Created by zk on 2018/three/11.
 */
class CanteenAdapter(context : Context) : BaseAdapter() {

    var mData: ArrayList<Canteen> = ArrayList()

    init {
        mData.add(Canteen(R.drawable.one, "1号食堂"))
        mData.add(Canteen(R.drawable.two, "2号食堂"))
        mData.add(Canteen(R.drawable.three, "3号食堂"))
        mData.add(Canteen(R.drawable.four, "4号食堂"))
        mData.add(Canteen(R.drawable.five, "5号食堂"))
    }

    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount() = mData.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = convertView ?: layoutInflater.inflate(R.layout.item_canteen, parent, false)

        val img : ImageView = v.findViewById(R.id.canteenPhotoIv)
        val intro : TextView = v.findViewById(R.id.canteenIntroTv)

        Glide.with(AppContext.sContext).load(mData[position].res).into(img)
        intro.text = mData[position].name

        return v
    }

    override fun getItem(position: Int): Canteen {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return position + 1L
    }


}