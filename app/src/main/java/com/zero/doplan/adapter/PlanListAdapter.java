package com.zero.doplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zero.doplan.R;

import java.util.ArrayList;

/**
 * Created by allen on 2017/7/12.
 */

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.ItemView> {

    private ArrayList<String> mItems;
    private Context mContext;

    public PlanListAdapter(Context context, ArrayList<String> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.plan_item, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        holder.titleTv.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {

        TextView titleTv;

        public ItemView(View itemView){
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
        }
    }
}
