package com.zero.doplan.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.zero.doplan.greendao.DaoSession;
import com.zero.doplan.greendao.SignDao;
import com.zero.doplan.greendao.PlanDao;

/**
 * Created by Allen.D on 17/3/12.
 */

@Entity(
        nameInDb = "t_plan"
)
public class Plan {

    @Id(autoincrement = true)
    private Long planId;

    @NotNull
    private long createdTime;

    @NotNull
    private long lastUpdateTime;

    private long startTime;

    private long endTime;

    private int planType;

    private boolean hasDone;

    private String goals;

    private String content;


    @ToMany(referencedJoinProperty = "planId")
    @OrderBy("signTime DESC")
    private List<Sign> signs;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 317818512)
    private transient PlanDao myDao;


    @Generated(hash = 2047451973)
    public Plan(Long planId, long createdTime, long lastUpdateTime, long startTime,
            long endTime, int planType, boolean hasDone, String goals, String content) {
        this.planId = planId;
        this.createdTime = createdTime;
        this.lastUpdateTime = lastUpdateTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.planType = planType;
        this.hasDone = hasDone;
        this.goals = goals;
        this.content = content;
    }


    @Generated(hash = 592612124)
    public Plan() {
    }


    public Long getPlanId() {
        return this.planId;
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


    public long getStartTime() {
        return this.startTime;
    }


    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    public long getEndTime() {
        return this.endTime;
    }


    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    public int getPlanType() {
        return this.planType;
    }


    public void setPlanType(int planType) {
        this.planType = planType;
    }


    public boolean getHasDone() {
        return this.hasDone;
    }


    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }


    public String getGoals() {
        return this.goals;
    }


    public void setGoals(String goals) {
        this.goals = goals;
    }


    public String getContent() {
        return this.content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2052002221)
    public List<Sign> getSigns() {
        if (signs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SignDao targetDao = daoSession.getSignDao();
            List<Sign> signsNew = targetDao._queryPlan_Signs(planId);
            synchronized (this) {
                if (signs == null) {
                    signs = signsNew;
                }
            }
        }
        return signs;
    }


    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 324125799)
    public synchronized void resetSigns() {
        signs = null;
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 2098727688)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlanDao() : null;
    }


    public void setPlanId(Long planId) {
        this.planId = planId;
    }


}
