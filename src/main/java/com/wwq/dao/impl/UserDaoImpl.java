package com.wwq.dao.impl;

import com.wwq.bean.User;
import com.wwq.dao.UserDao;
import com.wwq.util.JdbcUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {
    //单例模式
    private static UserDao userDao = new UserDaoImpl();
    private UserDaoImpl() {
    }
    public static UserDao getInstance() {
        return userDao;
    }

    @Override
    public List<User> queryAll() {
        String sql = "select * from user";
        return JdbcUtil.query(sql, new User());
    }
}
