package com.zero.doplan.db;

import com.zero.doplan.AppContext;
import com.zero.doplan.greendao.DaoSession;
import com.zero.doplan.greendao.PlanDao;
import com.zero.doplan.greendao.SignDao;

/**
 * Created by Allen.D on 17/3/12.
 */

public class DaoHelper {

    private static final DaoSession mDaoSession = AppContext.getDaoSession();

    private DaoHelper() {

    }

    public static PlanDao getPlanDao() {
        return mDaoSession.getPlanDao();
    }

    public static SignDao getSignDao() {
        return mDaoSession.getSignDao();
    }

}
