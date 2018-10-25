package com.okkristen.project.core.utils;

import org.springframework.util.StringUtils;

/**
 * @author ysj
 * @create 2018-10-23
 **/
public class MyStringUtil {
    /**
     * 根据属性名 来获取方法名遵行 JAVABEAN 命名规范
     * @param fieldName
     * @return
     */
    public static  String getGetMethodName (String fieldName) {
        char[] fileNameChar = fieldName.toCharArray();
        fileNameChar[0] = (fileNameChar[0] + "").toUpperCase().toCharArray()[0];
        return new String("get") + new String (fileNameChar);
    }
    /**
     * 根据属性名 来获取方法名遵行 JAVABEAN 命名规范
     * @param fieldName
     * @return
     */
    public static  String getSetMethodName (String fieldName) {
        char[] fileNameChar = fieldName.toCharArray();
        fileNameChar[0] = (fileNameChar[0] + "").toUpperCase().toCharArray()[0];
        return new String("set") + new String (fileNameChar);
    }

    /**
     * 格式化 sql like %%
     */
    public static  String getLikeValue (String value){
        return StringUtils.isEmpty(value) ? "%%" : "%" + value + "%";
    }



    public static void main(String[] args) {
        System.out.println(getGetMethodName("createDemo"));
    }
}
