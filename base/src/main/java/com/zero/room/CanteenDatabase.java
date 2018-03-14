package com.zero.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.zero.room.dao.MenuDao;
import com.zero.room.dao.OrderDao;
import com.zero.room.dao.UserDao;
import com.zero.room.entity.Menu;
import com.zero.room.entity.Order;
import com.zero.room.entity.User;

/**
 * Created by hui_deng on 2017/12/19.
 */
@Database(entities = {Menu.class, Order.class, User.class}, version = 1, exportSchema = false)
public abstract class CanteenDatabase extends RoomDatabase {

    public abstract MenuDao menuDao();

    public abstract OrderDao orderDao();

    public abstract UserDao userDao();

}
