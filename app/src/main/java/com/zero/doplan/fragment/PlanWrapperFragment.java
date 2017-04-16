package com.zero.doplan.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zero.doplan.R;
import com.zero.doplan.db.DaoHelper;
import com.zero.doplan.db.entity.Plan;
import com.zero.doplan.greendao.PlanDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 正在计划的Fragment
 */
public class PlanWrapperFragment extends Fragment {

    @BindView(R.id.plan_wrapper_vp) ViewPager mViewPager;
    @BindView(R.id.plan_count_tv) TextView mCountTv;

    private PlanAdapter mAdapter;

    private SlidePlanListener mListener;

    private List<Plan> mPlanList;

    private PlanDao mPlanDao;

    public PlanWrapperFragment() {
        mPlanDao = DaoHelper.getPlanDao();
        refreshData();
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
        View view = inflater.inflate(R.layout.fragment_planing, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new PlanAdapter(getChildFragmentManager()){
            @Override
            public Fragment getItem(int position) {
                return super.getItem(position);
            }

            @Override
            public int getCount() {
                return super.getCount();
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setCountTv(position);
            }

            @Override
            public void onPageSelected(int position) {
                setCountTv(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
        setCountTv(0);

    }

    private void setCountTv(int position) {
        if (mPlanList != null && !mPlanList.isEmpty()) {
            mListener.onChangePlan(mPlanList.get(position));
            int total = mPlanList.size();
            String countTip = (position + 1) + "/" + total;
            mCountTv.setText(countTip);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface SlidePlanListener {
        void onChangePlan(Plan plan);
    }

    public class PlanAdapter extends FragmentPagerAdapter {

        public PlanAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlanFragment.newInstance(mPlanList.get(position).getPlanId(), position);
        }

        @Override
        public int getCount() {
            return mPlanList.size();
        }
    }

}
