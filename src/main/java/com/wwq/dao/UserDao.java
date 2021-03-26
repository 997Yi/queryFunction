package com.wwq.dao;

import com.wwq.bean.User;

import java.util.List;

public interface UserDao {

    List<User> queryAll();

    List<User> queryByName(User user);
}
