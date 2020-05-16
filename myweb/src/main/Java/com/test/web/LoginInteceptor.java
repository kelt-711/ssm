package com.test.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInteceptor implements HandlerInterceptor{

    /*在拦截器拦截之前判断是否之后的操作
     * @return false：不再继续执行后面的操作
     *         true：继续执行后面的操作
     * */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //判断当前的用户是否已经登录
        HttpSession session = httpServletRequest.getSession();
        String loginUser = (String)session.getAttribute("loginUser");

        //用户未登陆，跳转到登录页面
        if(loginUser == null){
            String path = session.getServletContext().getContextPath();
            httpServletResponse.sendRedirect(path + "/login");
            return false;
        }else{
            return true;
        }

    }

    //在拦截器拦截完毕之后进行的操作
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    //在完成视图渲染之后进行的操作
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
