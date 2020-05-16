package com.test.dao;

import com.test.service.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    //由于该查询语句较为简单，所以可以直接在@Select注解中写SQL语句
    @Select("select * from t_user where username = #{username} and password = #{password}")
    User queryForLogin(User user);

    //sql语句在mapper-user.xml文件中编写
    void insertUser(User user);

    @Select("select * from t_user where username = #{username}")
    User queryForReg(String username);
}
