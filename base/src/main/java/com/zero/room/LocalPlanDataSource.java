package com.zero.room;

import com.zero.room.dao.PlanDao;
import com.zero.room.dao.SignDao;
import com.zero.room.entity.Plan;
import com.zero.room.entity.Sign;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by hui_deng on 2017/12/20.
 */

public class LocalPlanDataSource implements PlanDataSource {

    private final PlanDao mPlanDao;
    private final SignDao mSignDao;

    public LocalPlanDataSource(PlanDao planDao, SignDao signDao) {
        mPlanDao = planDao;
        mSignDao = signDao;
    }


    @Override
    public Flowable<List<Plan>> getAllPlans() {
        return mPlanDao.getAllPlans();
    }

    @Override
    public Flowable<List<Sign>> getPlanSigns(long id) {
        return mSignDao.getSigns(id);
    }

    @Override
    public void insertOrUpdatePlan(Plan plan) {
        mPlanDao.insertPlan(plan);
    }

    @Override
    public void insertOrUpdateSign(Sign sign) {
        mSignDao.insertOrUpdate(sign);
    }
}
