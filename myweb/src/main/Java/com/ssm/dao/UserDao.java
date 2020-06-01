package com.ssm.dao;

import com.ssm.bean.User;

import java.util.List;

public interface UserDao {
    User selectUserById(Integer id);

    List<User> selectUser();
}