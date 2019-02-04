package com.okkristen.project.core.global;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存放全局的数据
 */
public class GlobalUtils {

    /**
     * 存放所有的实体类的map 以及基本数据类型的包装类 通用的类型
     */
    public volatile static Map<String,Class<?>> typesMap = new ConcurrentHashMap<>();
//
    static {
        typesMap.put("Integer",Integer.class);
        typesMap.put("Boolean",Boolean.class);
        typesMap.put("Float",Float.class);
        typesMap.put("Character",Character.class);
        typesMap.put("Double",Double.class);
        typesMap.put("Long",Long.class);
        typesMap.put("Byte",Byte.class);
        typesMap.put("Short",Short.class);
        typesMap.put("String",String.class);
        typesMap.put("BigDecimal", BigDecimal.class);
        typesMap.put("Date", Date.class);
    }
}
