package com.zero.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zero.room.entity.User;

/**
 * Created by hui_deng on 2018/3/13.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(User user);

    @Query("SELECT * FROM t_user where username = :username and pwd = :password")
    User checkLogin(String username, String password);


}
