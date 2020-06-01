package com.ssm.service.impl;

import com.ssm.bean.User;
import com.ssm.dao.UserDao;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;

    public User selectUserBuId(Integer id) {
        return userDao.selectUserById(id);
    }
}