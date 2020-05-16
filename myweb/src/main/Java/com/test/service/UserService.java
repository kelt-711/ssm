package com.test.service;

import com.test.service.bean.User;

public interface UserService {

    //查询登录用户
    User queryForLogin(User user);

    //注册用户
    void insertUser(User user);

    User queryForReg(String username);
}
