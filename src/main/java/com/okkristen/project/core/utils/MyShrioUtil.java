package com.okkristen.project.core.utils;

import com.okkristen.project.core.shrio.utils.SessionUtil;

/**
 * @author ysj
 * @create 2018-12-27
 **/
public class MyShrioUtil {
    /**
     * 保存到shrio中
     * @param key
     * @param value
     */
    public static void put (String key,Object value) {
        SessionUtil.put(key,value);
    }
    /**
     * 去除 seesion 中对象
     * @param key
     * @return
     */
    public static void remove (String key) {
        SessionUtil.remove(key);
    }
    /**
     * 获取 保存对象
     * @param key
     * @return
     */
    public static Object get(String key) {
        return SessionUtil.get(key);
    }
}
