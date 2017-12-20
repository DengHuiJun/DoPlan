package com.zero.doplan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zero.room.entity.Sign;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignRecordActivity extends BaseActionBarActivity {

    private List<Sign> mSigns;

    private long mPlanId;

    @BindView(R.id.sign_record_rv)
    RecyclerView mRecordsRv;

    private SignAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_record);

        setTitleText("打卡记录");

        mPlanId = getIntent().getLongExtra(Constant.KEY_PLAN_ID, Constant.INVALID_ID);

        if (mPlanId != Constant.INVALID_ID) {
//            mSigns = DaoHelper.getPlanDao().loadByRowId(mPlanId).getSigns();
        }

        mRecordsRv.setLayoutManager(new LinearLayoutManager(mContext));

        mAdapter = new SignAdapter();
        mRecordsRv.setAdapter(mAdapter);
    }

    @Override
    protected void initButterKnife() {
        ButterKnife.bind(this);
    }


    private class SignHolder extends RecyclerView.ViewHolder {

        TextView mContentTv;

        public SignHolder(View itemView) {
            super(itemView);

            mContentTv = (TextView) itemView.findViewById(R.id.sign_content_tv);
        }
    }

    private class SignAdapter extends RecyclerView.Adapter<SignHolder> {

        @Override
        public SignHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.sign_rv_item, parent, false);
            return new SignHolder(view);
        }

        @Override
        public void onBindViewHolder(SignHolder holder, int position) {
            Sign sign = mSigns.get(position);
            holder.mContentTv.setText(sign.getSignContent());
        }

        @Override
        public int getItemCount() {
            return mSigns.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }


}
