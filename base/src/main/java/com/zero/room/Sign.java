package com.zero.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by hui_deng on 2017/12/19.
 */
@Entity(tableName = "t_sign",
        foreignKeys = @ForeignKey(entity = Plan.class,
                parentColumns = "id",
                childColumns = "planId")
)
public class Sign {
    @PrimaryKey
    private long id;

    private long planId;

    private long signTime;

    // 取每天的凌晨2点整作为标识，这天是否打卡
    private long keyTodayTime;

    private String signContent;

    private long createdTime;
}
