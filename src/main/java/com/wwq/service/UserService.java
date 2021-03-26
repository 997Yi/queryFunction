package com.wwq.service;

import com.wwq.bean.User;

import java.util.List;

public interface UserService {
    List<User> queryByName(User user);
}
