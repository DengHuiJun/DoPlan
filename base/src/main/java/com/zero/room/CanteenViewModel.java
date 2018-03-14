package com.zero.room;

import android.arch.lifecycle.ViewModel;

/**
 * 和Activity挂钩
 * Created by hui_deng on 2017/12/20.
 */

public class CanteenViewModel extends ViewModel {
    private final CanteenDataSource mDataSource;

    public CanteenViewModel(CanteenDataSource dataSource) {
        mDataSource = dataSource;
    }

//    public Flowable<List<Plan>> getAllPlans() {
//        return mDataSource.getAllPlans();
//    }
//
//    public Flowable<List<Sign>> getPlanSigns(long id) {
//        return mDataSource.getPlanSigns(id);
//    }
//
//    public Completable updatePlan(final Plan plan) {
//        return Completable.fromAction(() -> {
//            mDataSource.insertOrUpdatePlan(plan);
//        });
//    }
//
//    public Completable insertSign(final Sign sign) {
//        return Completable.fromAction(() -> {
//            mDataSource.insertOrUpdateSign(sign);
//        });
//    }

}
