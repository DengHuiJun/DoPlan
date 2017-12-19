package com.zero.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by hui_deng on 2017/12/19.
 */
@Entity(tableName = "t_plan")
public class Plan {

    @PrimaryKey
    private long id;

    private long createdTime;

    private long lastUpdateTime;

    private long startTime;

    private long endTime;

    private int planType;

    private boolean hasDone;

    // 量化目标值
    private int goals;

    private String content;

    private int signTimes;
}
