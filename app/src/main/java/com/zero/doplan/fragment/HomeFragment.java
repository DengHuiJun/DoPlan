package com.zero.doplan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zero.doplan.R;
import com.zero.doplan.adapter.PlanListAdapter;
import com.zero.doplan.db.entity.Plan;
import com.zero.doplan.greendao.PlanDao;
import com.zero.doplan.ui.AddOrEditPlanActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主页
 */
public class HomeFragment extends Fragment {

    private SlidePlanListener mListener;

    private List<Plan> mPlanList;

    private PlanDao mPlanDao;

    private ArrayList<String> mMockDatas = new ArrayList<>();

    private PlanListAdapter mAdapter;

    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    public HomeFragment() {
//        mPlanDao = DaoHelper.getPlanDao();
//        refreshData();
    }

    private void refreshData() {
        mPlanList = mPlanDao.queryBuilder().orderAsc(PlanDao.Properties.StartTime).limit(10).list();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        for (int i = 0; i < 15; i++) {
            mMockDatas.add(Integer.toString(i));
        }
        mAdapter = new PlanListAdapter(getActivity(), mMockDatas);
        mHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRv.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SlidePlanListener) {
            mListener = (SlidePlanListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @OnClick(R.id.add_plan_fab)
    public void goToAddPlan() {
        Intent intent = new Intent(getActivity(), AddOrEditPlanActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface SlidePlanListener {
        void onChangePlan(Plan plan);
    }


}
