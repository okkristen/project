package com.okkristen.project.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * 先书写 DTO 与Entity类之间的转换
 */

/**
 * bean 工具类
 */
public class MyBeanUtil extends BeanUtils {


    /**
     * 两个对象相互的限制
     * @param source 来源对象
     * @param target 目标对象
     */
    public  static  void  copyObjectProperties(Object source, Object target) {
        Assert.isTrue(source != null, "来源对象不能为空");
        Assert.isTrue(target != null, "目标对象不能为空");
        try {
            copyProperties(source,target);
            List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getPropertyDescriptor(target.getClass(),MyReflectionUtil.getFileds(target.getClass(),new ArrayList<>()));
            // 目标对象 的属性描述器 list
            for (PropertyDescriptor pd: propertyDescriptorList) {
                Class<?> tClass = pd.getPropertyType();
                Method readMethod = pd.getReadMethod();
                Object getObject = MyReflectionUtil.invokeMethod(source,pd.getReadMethod().getName());
                if (getObject != null) {
                    // 排除 空字符串的情况
                    if (getObject instanceof  String) {
                        String getString = (String)getObject;
                        getString = StringUtils.isEmpty(getString) ? null: getString;
                        Method writeMethod = pd.getWriteMethod();
                        writeMethod.invoke(target,getString);
                        // 复制处理 list 里面的东西
                    } else if (getObject instanceof Collection) {
                        Collection getCollection = (Collection) getObject;
                        Collection collection = new ArrayList();
                       Iterator i =  getCollection.iterator();
                       while (i.hasNext()) {
                           Object getObjectConent = i.next();
                           if (getObjectConent != null && checkObejectNull(getObjectConent)) {
                               Object setObjectConent = getObjectConent.getClass().newInstance();
                               copyObjectProperties(getObjectConent,setObjectConent);
                               collection.add(setObjectConent);
                           }
                       }
                        Method writeMethod = pd.getWriteMethod();
                        writeMethod.invoke(target,collection);
                        // 排除基本数字类型 特定类型
                    } else if (MyReflectionUtil.isLgnore(getObject)) {
                        // 处理该对象的内部是不是为空null 或者空字符串对象
                        if (getObject != null && checkObejectNull(getObject)) {
                            Object setObject = pd.getPropertyType().newInstance();
                            copyObjectProperties(getObject,setObject);
                            Method writeMethod = pd.getWriteMethod();
                            writeMethod.invoke(target,setObject);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理对象内部 为空对象 以及 空字符串的情况
     * 返回true 则里面不是空对象  返回false 则是空对象
     */
    public static Boolean checkObejectNull(Object getObject) {
        if (getObject == null) {
            return  false;
        }
        System.out.println(getObject);
        List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getPropertyDescriptor(getObject.getClass(),MyReflectionUtil.getFileds(getObject.getClass(),new ArrayList<>()));
        Boolean flag = false;
        for (PropertyDescriptor pd : propertyDescriptorList) {
            Method readMethod = pd.getReadMethod();
            Object getValue = MyReflectionUtil.invokeMethod(readMethod,getObject);
            if (String.class.equals(pd.getPropertyType())) {
                if (!StringUtils.isEmpty(getValue)) {
                    return true;
                }
            } else {
                // 如果是里面的 类型直接判断 是否为null 即可
                if (!MyReflectionUtil.isLgnore(pd.getPropertyType())) {
                    if (getValue != null) {
                        return  true;
                    }
                } else {
                    if (checkObejectNull(getValue)) {
                        return true;
                    } else {
                        flag = false;
                    }
                }
            }
        }
        return  flag;
    }
}
