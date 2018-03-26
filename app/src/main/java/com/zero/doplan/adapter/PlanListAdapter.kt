package com.zero.doplan.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.zero.doplan.R
import com.zero.base.TimeUtil
import com.zero.doplan.event.EventsType
import com.zero.doplan.event.NotificationCenter
import com.zero.doplan.util.ToastUtil
import com.zero.room.DBManager
import com.zero.room.Injection
import com.zero.room.entity.Plan
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by allen on 2017/7/12.
 */

class PlanListAdapter(private val mContext: Context, private var mItems: List<Plan>) : RecyclerView.Adapter<PlanListAdapter.ItemViewHolder>() {

    fun setItems(items: List<Plan>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.plan_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val p = mItems[position]

        holder.typeTv.text = "type:" + p.planType
        val c = p.content + ":" + p.goals
        holder.contentTv.text = c

        holder.signBtn.setOnClickListener {

            Observable.create(ObservableOnSubscribe<Boolean> {
                val s = DBManager.db.signDao().getSignByTime(p.id, TimeUtil.getTodayKey())
                val result: Boolean
                if (s == null) {
                    Injection.provideUserDataSource().insertOrUpdateSign(p.id)
                    result = true
                } else {
                    result = false
                }
                it.onNext(result)
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it) {
                            ToastUtil.snackBarShort(holder.typeTv, "打卡成功")
//                            NotificationCenter.getInstance().notify(EventsType.PLAN_REFRESH_EVENT)
                        } else {
                            ToastUtil.snackBarShort(holder.typeTv,"今天已经打过卡了哦~")
                        }
                    }
        }

        DBManager.db.signDao().getSigns(p.id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val keepDay = it.size
                    holder.keepDayTv.text = mContext.getString(R.string.keep_day, keepDay)
                }

        holder.countDownTv.text = mContext.getString(R.string.plan_time_and_distance,
                TimeUtil.getFormatDate(p.endTime), TimeUtil.getDaysByTwoTime(System.currentTimeMillis(), p.endTime))
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var typeTv: TextView = v.findViewById(R.id.xTypeTv)
        internal var contentTv: TextView = v.findViewById(R.id.xContentTv)
        internal var signBtn: Button = v.findViewById(R.id.xSignBtn)
        internal var keepDayTv: TextView = v.findViewById(R.id.xKeepDayTv)
        internal var countDownTv: TextView = v.findViewById(R.id.xCountDownTv)
        internal var planPb: View = v.findViewById(R.id.xProcessV)
    }
}
