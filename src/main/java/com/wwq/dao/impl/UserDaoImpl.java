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

    @Override
    public List<User> queryByName(User user) {
        if (user.getName() == null){
            return null;
        }
        String sql = "select * from user where ";
        sql = sql + "name=\'" + user.getName() + "\'";
        if (user.getGender() != null && !user.getGender().isEmpty()){
            sql = sql + " and gender=\'" + user.getGender() + "\'";
        }
        if (user.getBirthday() != null){
            sql = sql + " and birthday=\'" + user.getBirthday() + "\'";
        }

        return JdbcUtil.query(sql, new User());
    }
}
