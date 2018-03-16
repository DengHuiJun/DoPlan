package com.zero.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 带图标和文字的Item
 * 选中，则自动给icon和文字上色
 * 否则，去色
 * Created by zk
 */

public class IconItem extends LinearLayout {

    private ImageView icon;
    private TextView title;

    private Drawable normalDraw;
    private Drawable selectDraw;

    private int selectColor;

    public IconItem(Context context) {
        this(context, null);
    }

    public IconItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IconItem);

        String text = ta.getString(R.styleable.IconItem_android_text);
        if (!TextUtils.isEmpty(text)) {
            title.setText(text);
        } else {
            title.setVisibility(GONE);
        }

        final Drawable d = ta.getDrawable(R.styleable.IconItem_android_src);
        if (d != null) {
            normalDraw = d;
            icon.setImageDrawable(normalDraw);
        }
        selectDraw = ta.getDrawable(R.styleable.IconItem_srcSelect);
        selectColor = ta.getColor(R.styleable.IconItem_textSelect, Color.GRAY);

        ta.recycle();
    }

    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        icon = new ImageView(getContext());

        title = new TextView(getContext());
        title.setTextSize(12f);
        title.setTextColor(Color.GRAY);
        title.setPadding(0, 4, 0, 0);
        title.setGravity(Gravity.CENTER);

        addView(icon, 0);
        addView(title, 1);
    }

    public void setSelect(boolean select) {
        if (select) {
            icon.setImageDrawable(selectDraw);
            title.setTextColor(selectColor);
        } else {
            icon.setImageDrawable(normalDraw);
            title.setTextColor(Color.GRAY);
        }
    }

    public void setNormalDrawable(int resId) {
        normalDraw = ContextCompat.getDrawable(getContext(), resId);
    }

    public void setSelectDrawable(int resId) {
        selectDraw = ContextCompat.getDrawable(getContext(), resId);
    }

    public void setSelectColor(int id) {
        selectColor = ContextCompat.getColor(getContext(), id);
    }

    public void setTabTitle(CharSequence str) {
        title.setText(str);
        if (TextUtils.isEmpty(str)) {
            title.setVisibility(GONE);
        } else {
            title.setVisibility(VISIBLE);
        }
    }
}
