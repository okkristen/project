package com.okkristen.project.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import javassist.bytecode.AnnotationsAttribute;
import org.springframework.data.annotation.Persistent;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author ysj
 * @create 2018-10-30
 **/
public class MyJsonUtil {

    private static List<String> IgnoreList = Arrays.asList(Persistent.class.getName());


    /**
     * 根据忽略 用于@Persistent的注解字段
     */
    public  static  PropertyFilter  getPropertyFilter(){
        PropertyFilter profilter = new PropertyFilter(){
            @Override
            public boolean apply(Object object, String name, Object value) {
//                System.out.println("object" + object);
//                System.out.println("name" + name);
//                System.out.println("value" + value);
                // 特殊要求字段名  直接 返回 不忽略
                if (ignoreMethod(name)) {
                    return  true;
                }
//                空字符串 也同样忽略
                if (value != null &&value instanceof String) {
                    if (StringUtils.isEmpty(value)) {
                        return false;
                    }
                }
                Field field = MyReflectionUtil.findField(object.getClass(),name);
                Annotation[] annotations =  field.getDeclaredAnnotations();
                if (annotations == null) {
                    return  true;
                }
                for (Annotation annotation : annotations) {
                    String annotationName =annotation.annotationType().getName();
                    if (IgnoreList.contains(annotationName)){
                        return false;
                    }
                }
                return  true;
            }
        };
        return  profilter;
    }

    /**
     * 特殊要求  需要转换的 比如查询 时间  数据路 虽然不生成 但是 字段还是要转换成 sql 查询的
     */
    public  static  Boolean ignoreMethod(String fieldName) {
        if (fieldName.indexOf("start") == 0) {
            return  true;
        }
        if (fieldName.indexOf("end") == 0) {
            return true;
        }
        return  false;
    }

    /**
     * 对象转Json
     * @param object
     * @return
     */
    public static JSONObject getJson(Object object) {
        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(object));
        return  json;
    }

}
