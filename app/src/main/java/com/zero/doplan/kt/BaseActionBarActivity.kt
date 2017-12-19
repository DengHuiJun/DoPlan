package com.zero.doplan.kt

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.zero.doplan.R

/**
 * Created by Allen.D on 17-4-6.
 */

abstract class BaseActionBarActivity : AppCompatActivity(), View.OnClickListener {

    protected var mInflater: LayoutInflater? = null

    private var mTitleTv: TextView? = null

    private var mMenuText: CharSequence = ""
    private var mMenuIconDrawable: Drawable? = null
    private var mVisible = false
    private var mEnabled = true
    private var mWithText = false

    protected var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mInflater = layoutInflater
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        // 1. 清空内容
        val contentLayout = findViewById<FrameLayout>(Window.ID_ANDROID_CONTENT) as FrameLayout
        contentLayout.removeAllViews()

        if (hasActionBar()) {
            val contentView = mInflater?.inflate(layoutResID, null)
            if (contentView != null) {
                setContentView(contentView)
                return
            }
        }

        super.setContentView(layoutResID)
    }

    override fun setContentView(view: View) {
        var contentView = view
        if (hasActionBar() && view.findViewById<Toolbar>(R.id.toolbar) == null) {
            // 自动添加默认的ActionBar
            val root = mInflater?.inflate(R.layout.activity_base, null)
            (root?.findViewById<FrameLayout>(R.id.activity_content) as FrameLayout).addView(view)
            contentView = root
        }
        super.setContentView(contentView)
        initToolbar()
    }

    protected fun initToolbar() {
        if (hasActionBar()) {
            val toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
            if (toolbar != null) {
                setSupportActionBar(toolbar)
                val layoutRes = customToolbar
                if (layoutRes != 0) {
                    // 支持自定义Toolbar
                    toolbar.removeAllViews()
                    val customView = mInflater?.inflate(layoutRes, toolbar)
                    if (customView != null) {
                        setupCustomToolbar()
                    }
                }

                val actionBar = supportActionBar
                val navView = toolbar.findViewById<LinearLayout>(R.id.up)
                if (navView != null) {
                    actionBar!!.setDisplayShowHomeEnabled(false)
                    navView.setOnClickListener {
                        try {
                            onBackPressed()
                        } catch (e: Exception) {
                            // TODO 日志采集
                        }
                    }
                }
                mTitleTv = toolbar.findViewById<TextView>(R.id.toolbar_title) as TextView
                if (mTitleTv != null) {
                    actionBar!!.setDisplayShowTitleEnabled(false)
                }

                val backIv = toolbar.findViewById<ImageView>(R.id.actionbar_back_iv) as ImageView
                if (backIv != null) {
                    setupToolbarBackIv(backIv)
                }
                setupToolbar(toolbar)
            }
        }

    }

    protected fun setupCustomToolbar() {

    }

    protected fun setupToolbar(toolbar: Toolbar) {

    }

    protected fun setupToolbarBackIv(backIv: ImageView) {

    }

    protected val customToolbar: Int
        get() = 0

    /**
     * 默认带ActionBar

     * @return
     */
    protected fun hasActionBar(): Boolean {
        return true
    }


    fun setTitleText(text: CharSequence) {
        if (mTitleTv != null) {
            mTitleTv!!.text = text
        } else {
            val bar = supportActionBar
            if (bar != null) {
                bar.title = text
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val rightMenuItem = menu.add(0, MENU_ITEM_ID_RIGHT, 0, mMenuText)
        if (mMenuIconDrawable != null) {
            // TODO 点击变色
            //            DrawableUtil.setMenuTintIcon(rightMenuItem,mMenuIconDrawable);
        }
        rightMenuItem.isEnabled = mEnabled
        if (mWithText) {
            MenuItemCompat.setShowAsAction(rightMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS or MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT)
        } else {
            MenuItemCompat.setShowAsAction(rightMenuItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS)
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        val rightMenuItem = menu.findItem(MENU_ITEM_ID_RIGHT)
        if (rightMenuItem != null) {
            rightMenuItem.isEnabled = mEnabled
            rightMenuItem.isVisible = mVisible
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        var consume = true
        when (itemId) {
            MENU_ITEM_ID_RIGHT -> onRightMenuClick(item)
            else -> consume = super.onOptionsItemSelected(item)
        }
        return consume
    }

    protected fun setRighMenuVisible(visible: Boolean) {
        mVisible = visible
        supportInvalidateOptionsMenu()
    }

    protected fun setRightMenuText(text: CharSequence) {
        mMenuText = text
        if (!mVisible) {
            mVisible = true
        }
        supportInvalidateOptionsMenu()
    }

    protected fun setRightMenuIcon(iconRes: Int) {
        setRightMenuIcon(ContextCompat.getDrawable(mContext, iconRes))
    }

    protected fun setRightMenuIcon(iconDrawable: Drawable) {
        mMenuIconDrawable = iconDrawable
        if (!mVisible) {
            mVisible = true
        }
        supportInvalidateOptionsMenu()
    }

    protected fun setRightMenuEnabled(enabled: Boolean) {
        mEnabled = enabled
        supportInvalidateOptionsMenu()
    }

    protected fun onRightMenuClick(item: MenuItem) {

    }

    protected fun setRightMenuWithText(withText: Boolean) {
        mWithText = withText
        supportInvalidateOptionsMenu()
    }

    override fun onClick(v: View) {

    }

    companion object {

        private val MENU_ITEM_ID_RIGHT = Menu.FIRST + 99
    }
}
