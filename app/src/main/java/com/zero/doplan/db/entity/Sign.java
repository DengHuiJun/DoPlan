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

    private long planId;

    private long signTime;

    private String signContent;

    @NotNull
    private long createdTime;

    @NotNull
    private long lastUpdateTime;

    @Generated(hash = 870359712)
    public Sign(Long id, long planId, long signTime, String signContent,
            long createdTime, long lastUpdateTime) {
        this.id = id;
        this.planId = planId;
        this.signTime = signTime;
        this.signContent = signContent;
        this.createdTime = createdTime;
        this.lastUpdateTime = lastUpdateTime;
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

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}
