package com.zero.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.zero.room.dao.PlanDao;
import com.zero.room.dao.SignDao;
import com.zero.room.entity.Plan;
import com.zero.room.entity.Sign;

/**
 * Created by hui_deng on 2017/12/19.
 */
@Database(entities = {Plan.class,Sign.class}, version = 1)
public abstract class PlanDatabase extends RoomDatabase {
    private static volatile PlanDatabase INSTANCE;

    public abstract PlanDao planDao();
    public abstract SignDao signDao();

    public static PlanDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PlanDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlanDatabase.class, "do_plan.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
