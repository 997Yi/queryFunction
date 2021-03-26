package com.wwq.service.impl;

import com.wwq.bean.User;
import com.wwq.dao.UserDao;
import com.wwq.dao.impl.UserDaoImpl;
import com.wwq.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserService userService = new UserServiceImpl();
    private UserServiceImpl(){}
    public static UserService getInstance(){
        return userService;
    }

    //获取userDao对象
    UserDao userDao = UserDaoImpl.getInstance();

    @Override
    public List<User> queryByName(User user) {
        if (user == null) {
            return null;
        }
        return userDao.queryByName(user);
    }
}
