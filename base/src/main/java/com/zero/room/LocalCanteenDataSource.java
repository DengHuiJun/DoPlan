package com.zero.room;

import com.zero.room.dao.MenuDao;
import com.zero.room.dao.OrderDao;
import com.zero.room.dao.UserDao;
import com.zero.room.entity.Menu;
import com.zero.room.entity.Order;
import com.zero.room.entity.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zk on 2017/12/20.
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

    @Override
    public List<Menu> getOrderMenu(long userId) {
        List<Order> orders = mOrderDao.getOrdersByUser(userId);
        List<Menu> result = new ArrayList<>();
        if (orders != null && !orders.isEmpty()) {
            for (Order o : orders) {
                Menu m = mMenuDao.getMenuById(o.getMenuId());
                m.setOrderId(o.getId());
                result.add(m);
            }
        }
        return result;
    }

}
