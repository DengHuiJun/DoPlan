package com.zero.room;

import com.zero.room.entity.Menu;

import java.util.List;

/**
 * Created by zerogeek on 2018/3/13.
 */

public interface CanteenDataSource {

    long checkLogin(String username, String pwd);

    List<Menu> getOrderMenu(long userId);
}
