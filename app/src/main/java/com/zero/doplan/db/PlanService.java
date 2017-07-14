package com.zero.doplan.db;


import com.zero.doplan.db.entity.Sign;
import com.zero.doplan.greendao.SignDao;
import com.zero.doplan.util.TimeUtil;

/**
 * Created by Allen.D on 17/7/14.
 */

public class PlanService {

    private static PlanService mPlanService;

    private PlanService() {

    }

    public static PlanService getInstance() {
        if (mPlanService == null) {
            synchronized (PlanService.class) {
                if (mPlanService == null) {
                    mPlanService = new PlanService();
                }
            }
        }

        return mPlanService;
    }

    /**
     *
     * @param planId
     * @return
     */
    public boolean signPlan(long planId) {
        if (!isSignedToday()) {
            long time = System.currentTimeMillis();
            Sign s = new Sign();
            s.setCreatedTime(time);
            s.setPlanId(planId);
            s.setSignContent("");
            s.setKeyTodayTime(TimeUtil.getTodayKey());
            s.setSignTime(time);

            DaoHelper.getSignDao().insert(s);
            return true;
        }
        return false;
    }

    public boolean isSignedToday() {
        return DaoHelper.getSignDao().queryBuilder()
                .where(SignDao.Properties.KeyTodayTime.eq(TimeUtil.getTodayKey()))
                .count() > 0;
    }

}
