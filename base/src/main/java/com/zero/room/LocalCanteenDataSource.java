package com.zero.room;

import com.zero.room.dao.MenuDao;
import com.zero.room.dao.OrderDao;
import com.zero.room.dao.UserDao;
import com.zero.room.entity.User;



/**
 * Created by hui_deng on 2017/12/20.
 */

public class LocalCanteenDataSource implements CanteenDataSource {

    private final MenuDao mMenuDao;
    private final OrderDao mOrderDao;
    private final UserDao mUserDao;

    public LocalCanteenDataSource(MenuDao menuDao, OrderDao orderDao, UserDao userDao) {
        mMenuDao = menuDao;
        mOrderDao = orderDao;
        mUserDao = userDao;
    }

    @Override
    public long checkLogin(String username, String pwd) {
        User user = mUserDao.checkLogin(username, pwd);
        if (user != null) {
            return user.getUid();
        }
        return -1L;
    }

}
