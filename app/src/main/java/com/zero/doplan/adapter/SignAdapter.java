package com.zero.doplan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zero.doplan.R;
import com.zero.doplan.db.entity.Sign;
import com.zero.doplan.util.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Allen.D on 17/4/8.
 */

public class SignAdapter extends BaseAdapter {

    private List<Sign> mSignList;
    private Context mContext;

    public SignAdapter(Context context, List<Sign> list) {
        mSignList = list;
        mContext = context;
    }

    public void setSignList(List<Sign> mSignList) {
        this.mSignList = mSignList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSignList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSignList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Sign sign = mSignList.get(position);
        ItemView itemView = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_sign_item, parent, false);
            itemView = new ItemView(convertView);
            convertView.setTag(itemView);
        } else {
            itemView = (ItemView) convertView.getTag();
        }

        itemView.timeTv.setText(TimeUtil.getFormatDate(sign.getSignTime()));
        itemView.contentTv.setText(sign.getSignContent());

        return convertView;
    }


    static class ItemView {
        @BindView(R.id.sign_time_tv)
        TextView timeTv;

        @BindView(R.id.sign_content_tv)
        TextView contentTv;

        public ItemView(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
