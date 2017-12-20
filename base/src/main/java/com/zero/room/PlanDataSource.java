package com.zero.room;

import com.zero.room.entity.Plan;
import com.zero.room.entity.Sign;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 计划相关业务层
 * Created by hui_deng on 2017/12/20.
 */

public interface PlanDataSource {

    Flowable<List<Plan>> getAllPlans();

    Flowable<List<Sign>> getPlanSigns(long id);

    void insertOrUpdatePlan(Plan plan);

    void insertOrUpdateSign(Sign sign);
}
