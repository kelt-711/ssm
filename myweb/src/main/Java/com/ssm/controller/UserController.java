package com.ssm.controller;

import com.ssm.bean.User;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String test(){
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public User selectUserById(@RequestParam(value = "id",required =true,defaultValue = "1") Integer id){
        return userService.selectUserBuId(id);
    }
}
