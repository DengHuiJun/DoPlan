package com.zero.doplan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zero.doplan.R;
import com.zero.doplan.adapter.SignAdapter;
import com.zero.doplan.db.DaoHelper;
import com.zero.doplan.db.entity.Plan;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 计划详情页
 */
public class PlanFragment extends BaseObserverFragment {
    private static final String ARG_PLAN = "arg_plan";
    private static final String ARG_POSITION = "arg_position";

    private long mPlanId;
    private int mPosition;
    private Plan mPlan;

    private static final long DAY_MIS =  60L * 60 * 24 * 1000;

    @BindView(R.id.plan_goals_tv)
    TextView mGoalsTv;

    @BindView(R.id.plan_keep_tv)
    TextView mKeepTv;

    @BindView(R.id.plan_rest_tv)
    TextView mRestTv;

    @BindView(R.id.plan_sign_lv)
    ListView mSignLv;

    private SignAdapter mSignAdapter;

    public PlanFragment() {
        // Required empty public constructor
    }

    public static PlanFragment newInstance(long planId, int pos) {
        PlanFragment fragment = new PlanFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PLAN, planId);
        args.putInt(ARG_POSITION, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlanId = getArguments().getLong(ARG_PLAN);
            mPosition = getArguments().getInt(ARG_POSITION);
        }

        loadData(mPlanId);
    }

    @Override
    protected void onChange(String eventType, Bundle eventArgs) {

    }

    @Override
    protected String[] getObserverEventType() {
        return new String[0];
    }

    private void loadData(long id) {
        mPlan = DaoHelper.getPlanDao().loadByRowId(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mSignAdapter = new SignAdapter(mContext, mPlan.getSigns());
        mSignLv.setAdapter(mSignAdapter);
    }

    private void refreshPlanUI () {
        if (mPlan == null) return;

        mGoalsTv.setText(mPlan.getGoals());

        mSignAdapter.setSignList(mPlan.getSigns());

        mKeepTv.setText(Long.toString(getKeepDays(mPlan.getStartTime())));
        mRestTv.setText(Long.toString(getRestDays(mPlan.getEndTime())));
    }

    private long getKeepDays(long startTime) {
        long now = System.currentTimeMillis();
        return (now - startTime)/DAY_MIS + 1;
    }

    private long getRestDays(long endTime) {
        long now = System.currentTimeMillis();
        return (endTime - now)/DAY_MIS;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshPlanUI();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
