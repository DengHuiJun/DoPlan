package com.zero.doplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zero.doplan.R;
import com.zero.doplan.event.EventsType;
import com.zero.doplan.event.NotificationCenter;
import com.zero.doplan.util.TimeUtil;
import com.zero.doplan.util.ToastUtil;

import java.util.List;

import static com.zero.doplan.R.id.content_tv;

/**
 * Created by allen on 2017/7/12.
 */

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.ItemView> {

    private List<Plan> mItems;
    private Context mContext;

    public PlanListAdapter(Context context, List<Plan> items) {
        mContext = context;
        mItems = items;
    }

    public void setItems(List<Plan> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.plan_item, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        final Plan p = mItems.get(position);

        holder.typeTv.setText("type:" + p.getPlanType());
        String c = p.getContent() + ":" + p.getGoals();
        holder.contentTv.setText(c);

        holder.signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = PlanService.getInstance().signPlan(p.getPlanId());
                if (b) {
                    ToastUtil.showShort("打卡成功");
                    NotificationCenter.getInstance().notify(EventsType.PLAN_REFRESH_EVENT);
                } else {
                    ToastUtil.showShort("今天已经打过卡了哦~");
                }
            }
        });

        List<Sign> signs = DaoHelper.getSignDao()._queryPlan_Signs(p.getPlanId());
        int keepDay = signs.size();
        int totalDay = TimeUtil.getDaysByTwoTime(p.getStartTime(), p.getEndTime());
        int n = totalDay - keepDay;
        holder.keepDayTv.setText("Day " + keepDay);
        holder.countDownTv.setText("距离完成还有 " + n + " 天");

        holder.planPb.setMax(totalDay);
        holder.planPb.setProgress(keepDay);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ItemView extends RecyclerView.ViewHolder {

        TextView typeTv;
        TextView contentTv;
        Button signBtn;
        TextView keepDayTv;
        TextView countDownTv;
        ProgressBar planPb;

        public ItemView(View itemView) {
            super(itemView);

            typeTv = (TextView) itemView.findViewById(R.id.type_tv);
            contentTv = (TextView) itemView.findViewById(content_tv);
            signBtn = (Button) itemView.findViewById(R.id.sign_btn);
            keepDayTv = (TextView) itemView.findViewById(R.id.keep_day_tv);
            countDownTv = (TextView) itemView.findViewById(R.id.count_down_tv);
            planPb = (ProgressBar) itemView.findViewById(R.id.plan_pb);
        }
    }
}
