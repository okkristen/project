package com.okkristen.project.core.utils;

import org.springframework.util.ReflectionUtils;

public class MyReflectionUtil extends ReflectionUtils {

    /**
     * 根据 方法名执行方法
     * @param object
     * @param methodName
     * @return
     */
    public Object invokeMethod(Object object,String methodName) {
        return  invokeMethod(findMethod(object.getClass(),methodName),object);
    }
}
