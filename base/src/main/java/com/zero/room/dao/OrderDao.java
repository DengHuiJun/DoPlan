package com.zero.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zero.room.entity.Order;

import java.util.List;

/**
 * Created by hui_deng on 2018/3/13.
 */
@Dao
public interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);

    @Query("SELECT * FROM t_order where userId = :uid")
    List<Order> getOrdersByUser(long uid);

    @Query("DELETE FROM t_order where id = :id")
    void deleteOrderById(long id);
}
