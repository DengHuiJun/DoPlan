package com.zero.doplan.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zero.doplan.R
import com.zero.doplan.adapter.PlanListAdapter
import com.zero.doplan.event.EventsType
import com.zero.doplan.ui.AddOrEditPlanActivity
import com.zero.room.Injection
import com.zero.room.PlanViewModel
import com.zero.room.entity.Plan

import java.util.ArrayList

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 主页
 */
class HomeFragment : BaseObserverFragment() {

    private var mListener: SlidePlanListener? = null

    private var mPlanList: List<Plan> = ArrayList()

    private var mAdapter: PlanListAdapter? = null

    private var mViewModel: PlanViewModel? = null

    private fun refreshData() {
        //        mPlanList = DaoHelper.getPlanDao().queryBuilder().orderAsc(PlanDao.Properties.CreatedTime).limit(10).list();
        mViewModel!!.allPlans
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    mPlanList = list
                    mAdapter!!.setItems(mPlanList)
                }
                ) { throwable -> Log.e("home", "all plans", throwable) }
    }

    override fun onChange(eventType: String, eventArgs: Bundle) {
        refreshData()
    }

    override fun getObserverEventType(): Array<String> {
        return arrayOf(EventsType.PLAN_ADD_EVENT, EventsType.PLAN_REFRESH_EVENT)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = Injection.provideViewModelFactory()
        mViewModel = ViewModelProviders.of(this, factory).get(PlanViewModel::class.java)

        mAdapter = PlanListAdapter(activity, mPlanList)
        homeRv.layoutManager = LinearLayoutManager(activity)
        homeRv.adapter = mAdapter

        addPlanFab.setOnClickListener {
            goToAddPlan()
        }

        refreshData()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SlidePlanListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    fun goToAddPlan() {
        val intent = Intent(activity, AddOrEditPlanActivity::class.java)
        startActivity(intent)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface SlidePlanListener {
        fun onChangePlan(plan: Plan)
    }


}
