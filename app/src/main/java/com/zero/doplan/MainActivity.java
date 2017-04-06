package com.zero.doplan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zero.doplan.db.entity.Plan;
import com.zero.doplan.fragment.AddFragment;
import com.zero.doplan.fragment.MeFragment;
import com.zero.doplan.fragment.PlanWrapperFragment;
import com.zero.doplan.fragment.SignFragment;
import com.zero.doplan.util.DimenUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActionBarActivity implements PlanWrapperFragment.planWrapperFragListener, SignFragment.signFragmentListener {

    private static final int CLICK_PLAN = 1;
    private static final int CLICK_ADD = 2;
    private static final int CLICK_ME = 3;

    @BindView(R.id.bottom_plan_tv)
    TextView mPlanTv;
    @BindView(R.id.bottom_plan_iv)
    ImageView mPlanIv;
    @BindView(R.id.bottom_add_tv)
    TextView mAddTv;
    @BindView(R.id.bottom_add_iv)
    ImageView mAddIv;
    @BindView(R.id.bottom_me_tv)
    TextView mMeTv;
    @BindView(R.id.bottom_me_iv)
    ImageView mMeIv;

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

    @BindView(R.id.main_content_fl) FrameLayout mContentFl;

    private View mPopView;
    private PopupWindow mPopWind;
    private TextView mPopupManagerPlanTv;
    private TextView mPopupSignRecordTv;
    private int yOffset;
    private int xOffset;

    private PlanWrapperFragment mPlanWrapperFragment;
    private MeFragment mMeFragment;
    private AddFragment mAddFragment;
    private SignFragment mSignFragment;
    private Plan mSelectPlan;

    private int mStatus = CLICK_PLAN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        initOverflowActionBar();
        initFragment();

        // 默认点击计划
        clickStatus(mStatus);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_content_fl, mPlanWrapperFragment)
                    .commit();
        }

    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    // TODO 懒加载
    private void initFragment() {
        mPlanWrapperFragment = new PlanWrapperFragment();

        mAddFragment = new AddFragment();

        mMeFragment = new MeFragment();

        mSignFragment = new SignFragment();
    }

    private void changeFragment(Fragment fragment) {
        if (mContentFl != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content_fl, fragment)
//                    .addToBackStack(null) 不加入
                    .commit();
        }
    }

    private void changeToolbar(int status) {
        switch (status) {
            case CLICK_PLAN:
                mToolbarPlanSelectLy.setVisibility(View.VISIBLE);
                mToolbarTitleTv.setVisibility(View.GONE);
                mToolbarRightTv.setVisibility(View.GONE);
                mToolbarAddIv.setVisibility(View.VISIBLE);

                mToolbarAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_actionbar_add));
                break;

            case CLICK_ADD:
                mToolbarPlanSelectLy.setVisibility(View.GONE);
                mToolbarTitleTv.setVisibility(View.VISIBLE);
                mToolbarRightTv.setVisibility(View.GONE);
                mToolbarAddIv.setVisibility(View.VISIBLE);

                mToolbarAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_actionbar_done));
                mToolbarTitleTv.setText("添加计划");

                break;

            case CLICK_ME:

                mToolbarPlanSelectLy.setVisibility(View.GONE);
                mToolbarTitleTv.setVisibility(View.VISIBLE);
                mToolbarRightTv.setVisibility(View.GONE);
                mToolbarAddIv.setVisibility(View.VISIBLE);

                mToolbarAddIv.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_action_edit));

                mToolbarTitleTv.setText("个人信息");

                break;

            default:

                break;
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

    @OnClick(R.id.toolbar_doing_btn)
    void clickActionBarDoing() {
        clickStatus(CLICK_PLAN);
        changeFragment(mPlanWrapperFragment);
    }

    /**
     * 切换到今日打卡
     */
    @OnClick(R.id.toolbar_today_sign_btn)
    void clickActionBarTodaySigh() {
        clickStatus(CLICK_PLAN);
        if (mSelectPlan != null) {
            Bundle bundle = new Bundle();
            bundle.putLong(Constant.KEY_PLAN_ID, mSelectPlan.getPlanId());
            mSignFragment.setArguments(bundle);
        }
        changeFragment(mSignFragment);
    }

    @OnClick(R.id.toolbar_add_iv)
    void clickActionAdd() {
        if (mStatus == CLICK_PLAN) {
            showOverflowMenu(true);
        }
    }


    private void clickStatus(int status) {
        mStatus = status;
        changeToolbar(status);
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
                gotoSignRecord();
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
        startActivity(intent);
    }

    @Override
    public void sign() {

    }

    @Override
    public void onChangePlan(Plan plan) {
        mSelectPlan = plan;
    }
}
