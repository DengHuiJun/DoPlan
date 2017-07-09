package com.zero.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 带图标和文字的Item
 * 选中，则自动给icon和文字上色
 * 否则，去色
 * Created by Allen.D on 17/7/9.
 */

public class IconItem extends LinearLayout {

    private ImageView icon;
    private TextView title;

    public IconItem(Context context) {
        this(context, null);
    }

    public IconItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

    }

    public void setSelect(boolean select) {

    }
}
