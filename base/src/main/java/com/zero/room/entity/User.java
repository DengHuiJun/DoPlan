package com.zero.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by hui_deng on 2018/3/13.
 */

@Entity(tableName = "t_user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long uid;

    private String username;

    private String pwd;

    private long createdTime;

    private long lastUpdateTime;
}
