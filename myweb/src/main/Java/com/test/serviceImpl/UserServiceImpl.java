package com.test.serviceImpl;

import com.test.bean.User;
import com.test.dao.UserDao;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User queryForLogin(User user) {
        return userDao.queryForLogin(user);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public User queryForReg(String username) {
        return userDao.queryForReg(username);
    }
}
