package com.zero.doplan.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Allen.D on 17-3-15.
 */
public class DatePickDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "DatePickDialogFragment";

    private PickDateListener mPickDateListener;

    public void setPickDateListener(PickDateListener dateListener) {
        mPickDateListener = dateListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of TimePickerDialog and return it
        return new DatePickerDialog(getContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        Date date = c.getTime();
        if (mPickDateListener != null) {
            mPickDateListener.dateSet(DateFormat.getDateFormat(getContext()).format(date), date.getTime());
        }
    }

    interface PickDateListener {
        void dateSet(String date, long time);
    }
}
