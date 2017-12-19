package com.zero.doplan.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Allen.D on 17/7/14.
 */
@Entity(
        nameInDb = "t_plan_type"
)
public class PlanType {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private int type;

    @NotNull
    private int name;

    private String iconId;

    @Generated(hash = 1321262574)
    public PlanType(Long id, int type, int name, String iconId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.iconId = iconId;
    }

    @Generated(hash = 686983741)
    public PlanType() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getName() {
        return this.name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getIconId() {
        return this.iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

}
