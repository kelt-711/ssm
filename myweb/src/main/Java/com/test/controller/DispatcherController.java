package com.test.controller;

import com.test.bean.AjaxResult;
import com.test.bean.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    //1.跳转到登录页面
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    //2.跳转到注册页面
    @RequestMapping("register")
    public String register(){
        return "register";
    }

    //2.登录成功后跳转到index.jsp页面
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    //3.用Ajax完成登录操作，但不跳转页面
    @ResponseBody
    @RequestMapping("doAjaxLogin")
    public Object doAjaxLogin(User user, HttpSession session, HttpServletResponse response){

        //创建一个AjaxResult对象，用于保存各种
        AjaxResult ajaxResult = new AjaxResult();

        User dbUser = userService.queryForLogin(user);

        //登录成功
        if(dbUser != null){
            //将用户放入session中
            System.out.print(dbUser.getUsername());
            session.setAttribute("loginUser",dbUser.getUsername());

            //获取是否记住用户名单选框的状态
            if("true".equals(user.getFlag())){
                // 完成记住用户名的功能:
                Cookie cookie = new Cookie("username",user.getUsername());
                //设置有效路径:
                cookie.setPath("/ssmtest");
                // 设置有效时间:
                cookie.setMaxAge(60*60*24);// 保存24小时
                // 将cookie回写到浏览器：
                response.addCookie(cookie);
            }

            ajaxResult.setSuccess(true);
        }else{
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    //3.用Ajax完成注册操作，但不跳转页面
    @ResponseBody
    @RequestMapping("doAjaxRegister")
    public Object doAjaxRegister(User user){

        //创建一个AjaxResult对象
        AjaxResult ajaxResult = new AjaxResult();

        User dbUser = userService.queryForReg(user.getUsername());

        if(dbUser != null){
            //该用户已经被注册
            ajaxResult.setSuccess(false);
        }else{
            try {
                userService.insertUser(user);

                ajaxResult.setSuccess(true);
            }catch (Exception e){
                e.printStackTrace();
                ajaxResult.setSuccess(false);
            }
        }

        return ajaxResult;
    }

}
