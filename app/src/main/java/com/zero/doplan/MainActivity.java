package com.zero.doplan;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zero.doplan.fragment.AddFragment;
import com.zero.doplan.fragment.MeFragment;
import com.zero.doplan.fragment.PlanWrapperFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements PlanWrapperFragment.OnFragmentInteractionListener{

    private static final int CLICK_PLAN = 1;
    private static final int CLICK_ADD = 2;
    private static final int CLICK_ME = 3;

    @BindView(R.id.bottom_plan_tv) TextView mPlanTv;
    @BindView(R.id.bottom_plan_iv) ImageView mPlanIv;
    @BindView(R.id.bottom_add_tv) TextView mAddTv;
    @BindView(R.id.bottom_add_iv) ImageView mAddIv;
    @BindView(R.id.bottom_me_tv) TextView mMeTv;
    @BindView(R.id.bottom_me_iv) ImageView mMeIv;

    @BindView(R.id.main_content_fl) FrameLayout mContentFl;

    private PlanWrapperFragment mPlanWrapperFragment;
    private MeFragment mMeFragment;
    private AddFragment mAddFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();

        // 默认点击计划
        clickStatus(CLICK_PLAN);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_content_fl, mPlanWrapperFragment)
                    .commit();
        }

    }

    // TODO 懒加载
    private void initFragment() {
        mPlanWrapperFragment = new PlanWrapperFragment();

        mAddFragment = new AddFragment();

        mMeFragment = new MeFragment();
    }

    private void changeFragment(Fragment fragment) {
        if (mContentFl != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_fl, fragment)
//                    .addToBackStack(null) 不加入
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.bottom_plan_ly)
    void clickPlan() {
        clickStatus(CLICK_PLAN);
        changeFragment(mPlanWrapperFragment);
    }

    @OnClick(R.id.bottom_add_ly)
    void clickAdd() {
        clickStatus(CLICK_ADD);
        changeFragment(mAddFragment);

    }

    @OnClick(R.id.bottom_me_ly)
    void clickMe() {
        clickStatus(CLICK_ME);
        changeFragment(mMeFragment);
    }

    private void clickStatus(int status) {
        switch (status) {
            case CLICK_PLAN:
                mPlanIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_plan_select));
                mAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_add_normal));
                mMeIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_me_normal));

                break;

            case CLICK_ADD:
                mPlanIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_plan_normal));
                mAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_add_select));
                mMeIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_me_normal));

                break;

            case CLICK_ME:
                mPlanIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_plan_normal));
                mAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_add_normal));
                mMeIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.icon_bottom_me_select));

                break;

            default:

                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
