package com.ssm.dao;

import com.ssm.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class UserTest {

    @Autowired(required = false)
    private UserDao mm;

    @Test
    public void testUser(){
        User user = mm.selectUserById(1);
        System.out.println(user);
    }

    @Test
    public void testUserAll(){
        List<User> userList = mm.selectUser();
        for (User user : userList){
            System.out.println(user);
        }
    }
}