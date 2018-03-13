package com.zero.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by hui_deng on 2018/3/13.
 */
@Entity(tableName = "t_menu")
public class Menu {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long createdTime;

    private long lastUpdateTime;

    private long canteenId;

    private String name;

    private double price;

    private String intro;

    private String imgKey;
}
