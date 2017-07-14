package com.zero.doplan.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Allen.D on 17/3/12.
 */

@Entity(
        nameInDb = "t_sign"
)
public class Sign {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private long planId;

    @NotNull
    private long signTime;

    // 取每天的凌晨2点整作为标识，这天是否打卡
    @NotNull
    private long keyTodayTime;

    private String signContent;

    @NotNull
    private long createdTime;

    @Generated(hash = 751421876)
    public Sign(Long id, long planId, long signTime, long keyTodayTime,
            String signContent, long createdTime) {
        this.id = id;
        this.planId = planId;
        this.signTime = signTime;
        this.keyTodayTime = keyTodayTime;
        this.signContent = signContent;
        this.createdTime = createdTime;
    }

    @Generated(hash = 2025164192)
    public Sign() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPlanId() {
        return this.planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public long getSignTime() {
        return this.signTime;
    }

    public void setSignTime(long signTime) {
        this.signTime = signTime;
    }

    public String getSignContent() {
        return this.signContent;
    }

    public void setSignContent(String signContent) {
        this.signContent = signContent;
    }

    public long getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getKeyTodayTime() {
        return this.keyTodayTime;
    }

    public void setKeyTodayTime(long keyTodayTime) {
        this.keyTodayTime = keyTodayTime;
    }

}
