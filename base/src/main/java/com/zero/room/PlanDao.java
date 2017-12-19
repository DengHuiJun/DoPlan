package com.zero.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;

/**
 * Created by hui_deng on 2017/12/19.
 */
@Dao
public interface PlanDao {

    @Query("SELECT * FROM t_plan LIMIT 10")
    Flowable<Plan> getPlans();

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param plan the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlan(Plan plan);

    /**
     * Delete all users.
     */
    @Query("DELETE FROM t_plan")
    void deleteAllPlans();

    @Query("DELETE FROM t_plan where planId = :id")
    void deletePlanById(int id);
}
