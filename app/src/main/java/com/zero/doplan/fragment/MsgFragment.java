package com.zero.doplan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zero.doplan.R;

import butterknife.ButterKnife;

/**
 * Created by allen on 2017/7/12.
 */

public class MsgFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
