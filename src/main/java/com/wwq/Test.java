package com.wwq;

import com.wwq.bean.User;
import com.wwq.dao.UserDao;
import com.wwq.dao.impl.UserDaoImpl;

import java.util.List;

public class Test {
    public static void main(String[] args) {


        UserDao userDao = UserDaoImpl.getInstance();
        List<User> users = userDao.queryAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
