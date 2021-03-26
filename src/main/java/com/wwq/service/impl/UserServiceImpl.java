package com.wwq.service.impl;

import com.wwq.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserService userService = new UserServiceImpl();
    private UserServiceImpl(){}
    public static UserService getInstance(){
        return userService;
    }
}
