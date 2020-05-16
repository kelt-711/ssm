package com.test.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LoginCache {

    //使用单例模式
    private LoginCache(){}

    private static LoginCache instance = new LoginCache();

    public static LoginCache getInstance(){
        return instance;
    }

    // key值：登录用户登录名，value值：登录用户sessionId
    private Map<String,String> loginUserSession = new HashMap<String,String>();
    //key值:登录用户sessionId,value值：登录用户session对象
    private Map<String,HttpSession> loginSession = new HashMap<String,HttpSession>();

    /**
     * 通过登录名获取对应登录用户的sessionId
     * @param username
     * @return
     */
    public String getSessionIdByUsername(String username){
        return loginUserSession.get(username);
    }

    /**
     * 通过sessionId获取对应的session对象
     * @param sessionId
     * @return
     */
    public HttpSession getSessionBySessionId(String sessionId){
        return loginSession.get(sessionId);
    }

    /**
     * 存储登录名与对应的登录sessionID至缓存对象
     * @param username
     * @param sessionId
     */
    public void setSessionIdByUserName(String username,String sessionId){
        loginUserSession.put(username, sessionId);
    }

    /**
     * 存储sessionId与对应的session对象至缓存对象
     * @param sessionId
     * @param session
     */
    public void setSessionBySessionId(String sessionId,HttpSession session){
        loginSession.put(sessionId, session);
    }

}
