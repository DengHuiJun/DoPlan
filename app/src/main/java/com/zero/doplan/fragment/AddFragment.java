package com.zero.doplan.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zero.doplan.R;
import com.zero.doplan.event.EventsType;
import com.zero.doplan.event.NotificationCenter;
import com.zero.room.Injection;
import com.zero.room.PlanViewModel;
import com.zero.room.ViewModelFactory;
import com.zero.room.entity.Plan;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 添加计划
 *
 * @author Allen.D
 */
public class AddFragment extends Fragment implements DatePickDialogFragment.PickDateListener {

    @BindView(R.id.add_goals_til)
    TextInputLayout mGoalsTIL;

    @BindView(R.id.add_content_til)
    TextInputLayout mContentTIL;

    @BindView(R.id.start_time_tv)
    TextView mStartTimeTv;

    @BindView(R.id.end_time_tv)
    TextView mEndTimeTv;

    private DatePickDialogFragment mDialogFragment;

//    private PlanDao mPlanDao;

    private ViewModelFactory mViewModelFactory;
    private PlanViewModel mViewModel;

    private int mGoalValue;
    private String mGoals;
    private String mContent;
    private long mStartTime;
    private long mEndTime;

    private int SELECT_CODE = 0;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);

        mGoalsTIL.setHint("量化值：");
        mContentTIL.setHint("小目标：");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PlanViewModel.class);
        mStartTime = mEndTime = System.currentTimeMillis();
    }

    @OnClick(R.id.start_time_tv)
    public void setStartTime() {
        SELECT_CODE = 1;
        showPickDialog();
    }

    @OnClick(R.id.end_time_tv)
    public void setEndTime() {
        SELECT_CODE = 2;
        showPickDialog();
    }

    private void showPickDialog() {
        if (mDialogFragment == null) {
            mDialogFragment = new DatePickDialogFragment();
            mDialogFragment.setPickDateListener(this);
        }
        mDialogFragment.show(getFragmentManager(), "AddFragment");
    }

    @OnClick(R.id.add_save_btn)
    public void savePlan() {
        mGoals = mGoalsTIL.getEditText().getText().toString().trim();
        mContent = mContentTIL.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(mGoals)) {
            Snackbar.make(mGoalsTIL, "目标不能为空！", Snackbar.LENGTH_SHORT).show();
            return;
        }
        try {
            mGoalValue = Integer.parseInt(mGoals);
        } catch (Exception e) {
            Snackbar.make(mGoalsTIL, "量化值填写有误！", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (mStartTime >= mEndTime && mStartTime != 0) {
            Snackbar.make(mGoalsTIL, "时间错误！", Snackbar.LENGTH_SHORT).show();
            return;
        }

        mViewModel.updatePlan(wrapperPlan())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            clearData();
                            Snackbar.make(mGoalsTIL, "保存成功！", Snackbar.LENGTH_SHORT).show();
                            NotificationCenter.getInstance().notify(EventsType.PLAN_ADD_EVENT);
                            getActivity().finish();
                        },
                        t -> {}
                );


    }

    private void clearData() {
        mGoalsTIL.getEditText().setText("");
        mContentTIL.getEditText().setText("");
        mGoalsTIL.getEditText().setSelected(true);
        mStartTime = mEndTime = 0;
    }

    private Plan wrapperPlan() {
        long nowTime = System.currentTimeMillis();
        Plan plan = new Plan();
        plan.setGoals(mGoalValue);
        plan.setContent(mContent);
        plan.setCreatedTime(nowTime);
        plan.setLastUpdateTime(nowTime);
        plan.setStartTime(mStartTime);
        plan.setEndTime(mEndTime);
        plan.setPlanType(0);
        plan.setHasDone(false);

        return plan;
    }


    @Override
    public void dateSet(String date, long time) {
        if (SELECT_CODE == 1) {
            mStartTimeTv.setText(date);
            mStartTime = time;
        } else if (SELECT_CODE == 2) {
            mEndTimeTv.setText(date);
            mEndTime = time;
        }
        SELECT_CODE = 0;
    }

}
