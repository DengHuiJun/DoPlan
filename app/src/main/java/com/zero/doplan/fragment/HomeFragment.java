package com.zero.doplan.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zero.doplan.R;
import com.zero.doplan.adapter.PlanListAdapter;
import com.zero.doplan.event.EventsType;
import com.zero.doplan.ui.AddOrEditPlanActivity;
import com.zero.room.Injection;
import com.zero.room.PlanViewModel;
import com.zero.room.ViewModelFactory;
import com.zero.room.entity.Plan;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 主页
 */
public class HomeFragment extends BaseObserverFragment {

    private SlidePlanListener mListener;

    private List<Plan> mPlanList = new ArrayList<>();

    private PlanListAdapter mAdapter;

    private PlanViewModel mViewModel;

    @BindView(R.id.home_rv)
    RecyclerView mHomeRv;

    public HomeFragment() {
    }

    private void refreshData() {
//        mPlanList = DaoHelper.getPlanDao().queryBuilder().orderAsc(PlanDao.Properties.CreatedTime).limit(10).list();
        mViewModel.getAllPlans()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            mPlanList = list;
                            mAdapter.setItems(mPlanList);
                        },
                        throwable -> Log.e("home", "all plans", throwable));
    }

    @Override
    protected void onChange(String eventType, Bundle eventArgs) {
        refreshData();
    }

    @Override
    protected String[] getObserverEventType() {
        return new String[]{
                EventsType.PLAN_ADD_EVENT,
                EventsType.PLAN_REFRESH_EVENT
        };
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

        ViewModelFactory factory = Injection.provideViewModelFactory(getActivity());
        mViewModel = ViewModelProviders.of(this, factory).get(PlanViewModel.class);

        mAdapter = new PlanListAdapter(getActivity(), mPlanList);
        mHomeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeRv.setAdapter(mAdapter);
        refreshData();
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
