package com.zero.doplan;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Allen.D on 17-4-6.
 */

public abstract class BaseActionBarActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int MENU_ITEM_ID_RIGHT = Menu.FIRST + 99;

    protected LayoutInflater mInflater;

    private TextView mTitleTv;

    private CharSequence mMenuText = "";
    private Drawable mMenuIconDrawable;
    private boolean mVisible = false;
    private boolean mEnabled = true;
    private boolean mWithText = false;

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = getLayoutInflater();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        // 1. 清空内容
        FrameLayout contentLayout = (FrameLayout) findViewById(Window.ID_ANDROID_CONTENT);
        if (contentLayout != null) {
            contentLayout.removeAllViews();
        }

        if (hasActionBar()) {
            View contentView = mInflater.inflate(layoutResID, null);
            if (contentView != null) {
                setContentView(contentView);
                return;
            }
        }

        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        View contentView = view;
        if (hasActionBar() && view.findViewById(R.id.toolbar) == null) {
            // 自动添加默认的ActionBar
            View root = mInflater.inflate(R.layout.activity_base, null);
            ((FrameLayout) root.findViewById(R.id.activity_content)).addView(view);
            contentView = root;
        }
        super.setContentView(contentView);
        initToolbar();
    }

    protected void initToolbar() {
        if (hasActionBar()) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                int layoutRes = getCustomToolbar();
                if (layoutRes != 0) {
                    // 支持自定义Toolbar
                    toolbar.removeAllViews();
                    View customView = mInflater.inflate(layoutRes, toolbar);
                    if (customView != null) {
                        setupCustomToolbar();
                    }
                }

                final ActionBar actionBar = getSupportActionBar();
                View navView = toolbar.findViewById(R.id.up);
                if (navView != null) {
                    actionBar.setDisplayShowHomeEnabled(false);
                    navView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                onBackPressed();
                            } catch (Exception e) {
                                // TODO 日志采集
                            }
                        }
                    });
                }
                mTitleTv = (TextView) toolbar.findViewById(R.id.toolbar_title);
                if (mTitleTv != null) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }

                ImageView backIv = (ImageView) toolbar.findViewById(R.id.actionbar_back_iv);
                if (backIv != null) {
                    setupToolbarBackIv(backIv);
                }
                setupToolbar(toolbar);
            }
        }

    }

    protected void setupCustomToolbar() {

    }

    protected void setupToolbar(Toolbar toolbar) {

    }

    protected void setupToolbarBackIv(ImageView backIv) {

    }

    protected int getCustomToolbar() {
        return 0;
    }

    /**
     * 默认带ActionBar
     *
     * @return
     */
    protected boolean hasActionBar() {
        return true;
    }


    public void setTitleText(CharSequence text) {
        if (mTitleTv != null) {
            mTitleTv.setText(text);
        } else {
            final ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setTitle(text);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem rightMenuItem = menu.add(0, MENU_ITEM_ID_RIGHT, 0, mMenuText);
        if (mMenuIconDrawable != null) {
            // TODO 点击变色
//            DrawableUtil.setMenuTintIcon(rightMenuItem,mMenuIconDrawable);
        }
        rightMenuItem.setEnabled(mEnabled);
        if (mWithText) {
            MenuItemCompat.setShowAsAction(rightMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS | MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);
        } else {
            MenuItemCompat.setShowAsAction(rightMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem rightMenuItem = menu.findItem(MENU_ITEM_ID_RIGHT);
        if (rightMenuItem != null) {
            rightMenuItem.setEnabled(mEnabled);
            rightMenuItem.setVisible(mVisible);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        boolean consume = true;
        switch (itemId) {
            case MENU_ITEM_ID_RIGHT:
                onRightMenuClick(item);
                break;
            default:
                consume = super.onOptionsItemSelected(item);
                break;
        }
        return consume;
    }

    protected void setRighMenuVisible(boolean visible) {
        mVisible = visible;
        supportInvalidateOptionsMenu();
    }

    protected void setRightMenuText(CharSequence text) {
        mMenuText = text;
        if (!mVisible) {
            mVisible = true;
        }
        supportInvalidateOptionsMenu();
    }

    protected void setRightMenuIcon(int iconRes) {
        setRightMenuIcon(ContextCompat.getDrawable(mContext, iconRes));
    }

    protected void setRightMenuIcon(Drawable iconDrawable) {
        mMenuIconDrawable = iconDrawable;
        if (!mVisible) {
            mVisible = true;
        }
        supportInvalidateOptionsMenu();
    }

    protected void setRightMenuEnabled(boolean enabled) {
        mEnabled = enabled;
        supportInvalidateOptionsMenu();
    }

    protected void onRightMenuClick(MenuItem item) {

    }

    protected void setRightMenuWithText(boolean withText) {
        mWithText = withText;
        supportInvalidateOptionsMenu();
    }

    @Override
    public void onClick(View v) {

    }
}
