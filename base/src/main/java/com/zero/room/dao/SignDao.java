package com.zero.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zero.room.entity.Sign;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by hui_deng on 2017/12/19.
 */
@Dao
public interface SignDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(Sign sign);

    @Query("SELECT * FROM t_sign WHERE planId = :id")
    Flowable<List<Sign>> getSigns(long id);

}
