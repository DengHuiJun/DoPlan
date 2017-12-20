package com.zero.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.zero.room.entity.Plan;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public long getSignTime() {
        return signTime;
    }

    public void setSignTime(long signTime) {
        this.signTime = signTime;
    }

    public long getKeyTodayTime() {
        return keyTodayTime;
    }

    public void setKeyTodayTime(long keyTodayTime) {
        this.keyTodayTime = keyTodayTime;
    }

    public String getSignContent() {
        return signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
