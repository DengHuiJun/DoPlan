package com.zero.doplan.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.zero.doplan.Constant;
import com.zero.doplan.R;
import com.zero.doplan.db.DaoHelper;
import com.zero.doplan.db.entity.Sign;
import com.zero.doplan.greendao.PlanDao;
import com.zero.doplan.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class SignFragment extends Fragment {

    private static final String TAG = SignFragment.class.getSimpleName();

    private signFragmentListener mListener;
    private SimpleDateFormat mDateSDF = new SimpleDateFormat("yyyy年MM月", Locale.getDefault());
    private SimpleDateFormat mDateDaySDF = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());

    @BindView(R.id.compactcalendar_view)
    CompactCalendarView mCalendarView;

    @BindView(R.id.calendar_date_tv)
    TextView mDateTitleTv;

    @BindView(R.id.sign_content_til)
    TextInputLayout mSignContentTIL;

    private long mPlanId = 0L;

    private long mSelectTime;

    private Calendar mCurrentCalendar = Calendar.getInstance(Locale.getDefault());

    public SignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mPlanId = args.getLong(Constant.KEY_PLAN_ID);
        }
        LogUtil.d(TAG + "get planId:" + mPlanId);

        TimeZone timeZone = TimeZone.getDefault();
        mCalendarView.setLocale(timeZone, Locale.CHINESE);
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendarView.setUseThreeLetterAbbreviation(true);

        mCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                mSelectTime = dateClicked.getTime();
                mDateTitleTv.setText(mDateDaySDF.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mDateTitleTv.setText(mDateSDF.format(mCalendarView.getFirstDayOfCurrentMonth()));
            }
        });

        mDateTitleTv.setText(mDateSDF.format(mCalendarView.getFirstDayOfCurrentMonth()));

        addEvent();
    }

    private void addEvent() {
        if (mPlanId == 0L) {
            return;
        }

        List<Sign> allSign = DaoHelper.getPlanDao().queryBuilder()
                .where(PlanDao.Properties.PlanId.eq(mPlanId))
                .list().get(0).getSigns();

        List<Event> eventList = new ArrayList<>(allSign.size());

        for (Sign sign : allSign) {
            Event event = new Event(Color.argb(255, 169, 68, 65), sign.getSignTime(), sign.getSignContent());
            eventList.add(event);
        }

        mCalendarView.addEvents(eventList);
    }

    /**
     * 分割点
     *
     * @param calendar
     */
    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.sign_btn)
    public void sign() {
        long time = System.currentTimeMillis();
        Sign sign = new Sign();
        sign.setPlanId(mPlanId);
        sign.setSignContent(mSignContentTIL.getEditText().getText().toString());
        sign.setCreatedTime(time);
        if (mSelectTime != 0) {
            time = mSelectTime;
        }
        sign.setSignTime(time);
        DaoHelper.getSignDao().insert(sign);
        Snackbar.make(mCalendarView, "保存成功", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof signFragmentListener) {
            mListener = (signFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement signFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface signFragmentListener {
        void sign();
    }

}
