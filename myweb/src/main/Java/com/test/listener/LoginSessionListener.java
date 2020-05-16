package com.test.listener;

import com.test.utils.LoginCache;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class LoginSessionListener implements HttpSessionAttributeListener{

    private static final String LOGINUSER="loginUser";

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        //获得session中添加的属性值的名称
        String attrName = httpSessionBindingEvent.getName();

        if(LOGINUSER.equals(attrName)){
            //获取添加的属性值，即用户登录名
            String attrValue = (String)httpSessionBindingEvent.getValue();
            //该次操作的session对象
            HttpSession session1 = httpSessionBindingEvent.getSession();
            //该次操作的session对象ID
            String sessionId = session1.getId();
            //从缓存对象里面，获得该用户登录名对应的sessionID值
            String sessionId2 = LoginCache.getInstance().getSessionIdByUsername(attrValue);
            //未获得结果，不需要清理前次登录用户会话信息
            if(null == sessionId2){

            }else{
                HttpSession session2 = LoginCache.getInstance().getSessionBySessionId(sessionId2);//获取前次该用户登录对应的session对象
                //清理前次登录用户会话存储信息，使得前次登录失效
                session2.invalidate();
            }

            //完成该次登录用户登录名、sessionID，session对象的缓存对象存储
            LoginCache.getInstance().setSessionIdByUserName(attrValue, sessionId);
            LoginCache.getInstance().setSessionBySessionId(sessionId, session1);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
