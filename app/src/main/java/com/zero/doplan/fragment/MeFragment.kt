package com.zero.doplan.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zero.doplan.R
import com.zero.doplan.invest.AddInvestActivity

import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_me, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        invest_model.setOnClickListener {
            activity.startActivity(Intent(activity, AddInvestActivity::class.java))
        }
    }


}// Required empty public constructor
