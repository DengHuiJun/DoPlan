package com.zero.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zk
 */

public class RowItem extends RelativeLayout {

    private ImageView iconIv;
    private TextView titleTv;
    private ImageView arrowIv;
    private View line;

    public RowItem(Context context) {
        this(context, null);
    }

    public RowItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RowItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RowItem);

        String title = ta.getString(R.styleable.RowItem_title);
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
        }

        final Drawable d = ta.getDrawable(R.styleable.RowItem_iconSrc);
        if (d != null) {
            iconIv.setImageDrawable(d);
        } else {
            iconIv.setVisibility(GONE);
        }

        ta.recycle();
    }

    private void init() {

        int dp16 = DensityUtil.dip2px(getContext(), 16f);
        int dp0_5 = DensityUtil.dip2px(getContext(), 0.5f);

        iconIv = new ImageView(getContext());
        iconIv.setId(R.id.row_item_icon_iv);
        LayoutParams icParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        icParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        icParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        icParams.addRule(RelativeLayout.CENTER_VERTICAL);
        icParams.leftMargin = dp16;
        iconIv.setLayoutParams(icParams);

        titleTv = new TextView(getContext());
        titleTv.setId(R.id.row_item_title_tv);
        titleTv.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams tParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tParams.addRule(RelativeLayout.ALIGN_RIGHT, iconIv.getId());
        tParams.addRule(RelativeLayout.CENTER_VERTICAL);
        tParams.leftMargin = dp16;
        titleTv.setLayoutParams(tParams);

        arrowIv = new ImageView(getContext());
        arrowIv.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow));
        LayoutParams aParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        aParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        aParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        aParams.addRule(RelativeLayout.CENTER_VERTICAL);
        aParams.rightMargin = dp16;
        arrowIv.setLayoutParams(aParams);

        line = new View(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp0_5);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        line.setLayoutParams(params);
        line.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.line_divider));

        addView(iconIv);
        addView(titleTv);
        addView(arrowIv);
        addView(line);
    }
}
