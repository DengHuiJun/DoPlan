package com.zero.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zero.room.entity.Menu;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.internal.operators.flowable.FlowableTakeLastOne;

/**
 * Created by zk on 2018/3/13.
 */
@Dao
public interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Menu menu);

    @Query("SELECT * FROM t_menu where type = :type and canteenId = :canteenId")
    List<Menu> getMenuByTypeAndCanteen(int type, int canteenId);

    @Query("SELECT * FROM t_menu where canteenId = :canteenId")
    List<Menu> getMenuByCanteen(int canteenId);

    @Query("SELECT * FROM t_menu where id = :id")
    Menu getMenuById(long id);

    @Query("SELECT * FROM t_menu where name like + :keyword group by name")
    Flowable<List<Menu>> searchMenuByKeyWord(String keyword);
}
