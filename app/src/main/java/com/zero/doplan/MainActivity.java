package com.zero.doplan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zero.base.IconItem;
import com.zero.doplan.fragment.FindFragment;
import com.zero.doplan.fragment.HomeFragment;
import com.zero.doplan.fragment.MeFragment;
import com.zero.doplan.fragment.MsgFragment;
import com.zero.doplan.fragment.SignFragment;
import com.zero.doplan.util.DimenUtil;
import com.zero.doplan.util.LogUtil;
import com.zero.room.entity.Plan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActionBarActivity implements HomeFragment.SlidePlanListener, SignFragment.signFragmentListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int CLICK_HOME = 0;
    private static final int CLICK_FIND = 1;
    private static final int CLICK_MSG = 2;
    private static final int CLICK_ME = 3;

    @BindView(R.id.main_vp)
    ViewPager mMainVp;

    @BindView(R.id.main_tl)
    TabLayout mMainTabLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_back_iv)
    ImageView mToolbarBackIv;

    @BindView(R.id.toolbar_doing_btn)
    Button mToolBarDoingBtn;

    @BindView(R.id.toolbar_today_sign_btn)
    Button mToolbarTodaySignBtn;

    @BindView(R.id.toolbar_plan_select_ly)
    LinearLayout mToolbarPlanSelectLy;

    @BindView(R.id.toolbar_center_title_tv)
    TextView mToolbarTitleTv;

    @BindView(R.id.toolbar_right_tv)
    TextView mToolbarRightTv;

    @BindView(R.id.toolbar_add_iv)
    ImageView mToolbarAddIv;

    private View mPopView;
    private PopupWindow mPopWind;
    private TextView mPopupManagerPlanTv;
    private TextView mPopupSignRecordTv;
    private int yOffset;
    private int xOffset;

    private HomeFragment mHomeFragment;
    private FindFragment mFindFragment;
    private MsgFragment mMsgFragment;
    private MeFragment mMeFragment;

    private IconItem mHomeTab;
    private IconItem mFindTab;
    private IconItem mMsgTab;
    private IconItem mMeTab;

    private ArrayList<Fragment> mFragments = new ArrayList<>(4);

    private MainPagerAdapter mAdapter;

    private Plan mSelectPlan;

    private int mStatus = CLICK_HOME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initOverflowActionBar();
        initView();
        // 默认选中
        changeTabStatus(mStatus);

    }

    @Override
    protected void initButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    private void initView() {
        initFragment();
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mMainVp.setAdapter(mAdapter);
        mMainTabLayout.setupWithViewPager(mMainVp);
        initTabs();

        mMainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initTabs() {
        mHomeTab = new IconItem(mContext);
        mHomeTab.setTabTitle("首页");
        mHomeTab.setNormalDrawable(R.drawable.ic_home);
        mHomeTab.setSelectDrawable(R.drawable.ic_home_s);
        mHomeTab.setSelectColor(R.color.colorPrimary);
        mMainTabLayout.getTabAt(0).setCustomView(mHomeTab);

        mFindTab = new IconItem(mContext);
        mFindTab.setTabTitle("发现");
        mFindTab.setNormalDrawable(R.drawable.ic_find);
        mFindTab.setSelectDrawable(R.drawable.ic_find_s);
        mFindTab.setSelectColor(R.color.colorPrimary);
        mMainTabLayout.getTabAt(1).setCustomView(mFindTab);

        mMsgTab = new IconItem(mContext);
        mMsgTab.setTabTitle("消息");
        mMsgTab.setNormalDrawable(R.drawable.ic_msg);
        mMsgTab.setSelectDrawable(R.drawable.ic_msg_s);
        mMsgTab.setSelectColor(R.color.colorPrimary);
        mMainTabLayout.getTabAt(2).setCustomView(mMsgTab);

        mMeTab = new IconItem(mContext);
        mMeTab.setTabTitle("我的");
        mMeTab.setNormalDrawable(R.drawable.ic_me);
        mMeTab.setSelectDrawable(R.drawable.ic_me_s);
        mMeTab.setSelectColor(R.color.colorPrimary);
        mMainTabLayout.getTabAt(3).setCustomView(mMeTab);
    }

    // TODO 懒加载
    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mFindFragment = new FindFragment();
        mMsgFragment = new MsgFragment();
        mMeFragment = new MeFragment();

        mFragments.add(mHomeFragment);
        mFragments.add(mFindFragment);
        mFragments.add(mMsgFragment);
        mFragments.add(mMeFragment);
    }

    private void changeToolbar(int status) {
        switch (status) {
            case CLICK_HOME:
                mToolbarPlanSelectLy.setVisibility(View.VISIBLE);
                mToolbarTitleTv.setVisibility(View.GONE);
                mToolbarRightTv.setVisibility(View.GONE);
                mToolbarAddIv.setVisibility(View.VISIBLE);

                mToolbarAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_action_menu));

                break;


            case CLICK_FIND:
                mToolbarPlanSelectLy.setVisibility(View.GONE);
                mToolbarTitleTv.setVisibility(View.VISIBLE);
                mToolbarRightTv.setVisibility(View.GONE);
                mToolbarAddIv.setVisibility(View.GONE);

                mToolbarAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_actionbar_done));
                mToolbarTitleTv.setText("发现");

                break;

            case CLICK_MSG:

                break;

            case CLICK_ME:
                mToolbarPlanSelectLy.setVisibility(View.GONE);
                mToolbarTitleTv.setVisibility(View.VISIBLE);
                mToolbarRightTv.setVisibility(View.GONE);
                mToolbarAddIv.setVisibility(View.GONE);

                mToolbarTitleTv.setText("我的");

                break;

            default:

                break;
        }
    }

    @OnClick(R.id.toolbar_doing_btn)
    void clickActionBarDoing() {
        changeTabStatus(CLICK_HOME);
    }

    /**
     * 切换到今日打卡
     */
    @OnClick(R.id.toolbar_today_sign_btn)
    void clickActionBarTodaySigh() {
        changeTabStatus(CLICK_HOME);
    }

    @OnClick(R.id.toolbar_add_iv)
    void clickActionAdd() {
        if (mStatus == CLICK_HOME) {
            showOverflowMenu(true);
        }
    }


    private void changeTabStatus(int status) {
        mStatus = status;
        changeToolbar(status);
        switch (status) {
            case CLICK_HOME:
                mHomeTab.setSelect(true);
                mFindTab.setSelect(false);
                mMsgTab.setSelect(false);
                mMeTab.setSelect(false);
                break;

            case CLICK_FIND:
                mHomeTab.setSelect(false);
                mFindTab.setSelect(true);
                mMsgTab.setSelect(false);
                mMeTab.setSelect(false);

                break;

            case CLICK_MSG:
                mHomeTab.setSelect(false);
                mFindTab.setSelect(false);
                mMsgTab.setSelect(true);
                mMeTab.setSelect(false);
                break;

            case CLICK_ME:
                mHomeTab.setSelect(false);
                mFindTab.setSelect(false);
                mMsgTab.setSelect(false);
                mMeTab.setSelect(true);
                break;

            default:
                break;
        }
    }

    private void initOverflowActionBar() {
        mPopView = getLayoutInflater().inflate(R.layout.main_menu_popwindow_item, null);
        mPopupManagerPlanTv = (TextView) mPopView.findViewById(R.id.pop_item_one_tv);
        mPopupSignRecordTv = (TextView) mPopView.findViewById(R.id.pop_item_two_tv);
        mPopWind = new PopupWindow(mPopView, DimenUtil.dip2px(mContext, 85), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopView.setFocusable(true);
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        yOffset = frame.top + DimenUtil.dip2px(mContext, 51);
        xOffset = DimenUtil.dip2px(mContext, 12);
        mPopWind.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        mPopWind.setOutsideTouchable(true);
        mPopWind.setAnimationStyle(android.R.style.Animation_Dialog);

        initPopupClickListener();
    }

    private void initPopupClickListener() {
        mPopupManagerPlanTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPlanCenter();
                showOverflowMenu(false);
            }
        });

        mPopupSignRecordTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                gotoSignRecord();
                showOverflowMenu(false);
            }
        });
    }

    private void showOverflowMenu(boolean isShow) {
        if (isShow && !mPopWind.isShowing()) {
            mPopWind.showAtLocation(getWindow().getDecorView(), Gravity.RIGHT | Gravity.TOP, xOffset, yOffset);
        } else if (!isShow && mPopWind.isShowing() && !MainActivity.this.isFinishing()) {
            mPopWind.dismiss();
        }
    }

    private void gotoPlanCenter() {
        Intent intent = new Intent(this, PlanCenterActivity.class);
        startActivity(intent);
    }

    private void gotoSignRecord() {
        Intent intent = new Intent(this, SignRecordActivity.class);
//        intent.putExtra(Constant.KEY_PLAN_ID, mSelectPlan.getPlanId());
        startActivity(intent);
    }

    @Override
    public void sign() {

    }

    @Override
    public void onChangePlan(Plan plan) {
        LogUtil.d(TAG + "onChangePlan: id," + plan.getId());
        mSelectPlan = plan;
    }

    public class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
