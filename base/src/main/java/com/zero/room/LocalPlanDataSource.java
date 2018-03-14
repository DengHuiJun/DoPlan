package com.zero.room;

import com.zero.room.dao.PlanDao;
import com.zero.room.dao.SignDao;
import com.zero.room.dao.UserDao;
import com.zero.room.entity.Plan;
import com.zero.room.entity.Sign;
import com.zero.room.entity.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by hui_deng on 2017/12/20.
 */

public class LocalPlanDataSource implements PlanDataSource {

    private final PlanDao mPlanDao;
    private final SignDao mSignDao;
    private final UserDao mUserDao;

    public LocalPlanDataSource(PlanDao planDao, SignDao signDao, UserDao userDao) {
        mPlanDao = planDao;
        mSignDao = signDao;
        mUserDao = userDao;
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

    @Override
    public long checkLogin(String username, String pwd) {
        User user = mUserDao.checkLogin(username, pwd);
        if (user != null) {
            return user.getUid();
        }
        return -1L;
    }
}
