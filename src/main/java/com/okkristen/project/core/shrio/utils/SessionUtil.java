package com.okkristen.project.core.shrio.utils;

import org.apache.shiro.SecurityUtils;

/**
 * shrio的用户
 * @author ysj
 * @create 2018-12-25
 **/
public class SessionUtil {
    /**
     * 保存到shrio中
     * @param key
     * @param value
     */
    public  void put (String key,Object value) {
        SecurityUtils.getSubject().getSession().setAttribute(key,value);
    }
    /**
     * 去除 seesion 中对象
     * @param key
     * @return
     */
    public void remove (String key) {
        SecurityUtils.getSubject().getSession().removeAttribute(key);
    }
    /**
     * 获取 保存对象
     * @param key
     * @return
     */
    public Object get(String key) {
        return SecurityUtils.getSubject().getSession().getAttribute(key);
    }
}
