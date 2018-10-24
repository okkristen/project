package com.okkristen.project.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
                // 排除 空字符串的情况
                if (tClass.equals(String.class)) {
                    Method readMethod = pd.getReadMethod();
                    String getString = (String)MyReflectionUtil.invokeMethod(source,pd.getReadMethod().getName());
                    getString = StringUtils.isEmpty(getString) ? null: getString;
                    Method writeMethod = pd.getWriteMethod();
                    writeMethod.invoke(target,getString);
                    // 排除基本数字类型
                } else if (MyReflectionUtil.isLgnore(tClass)) {
                    Method readMethod = pd.getReadMethod();
                    Object getObject = MyReflectionUtil.invokeMethod(source,pd.getReadMethod().getName());
                    if (getObject != null) {
                        Object setObject = pd.getPropertyType().newInstance();
                        copyObjectProperties(getObject,setObject);
                        Method writeMethod = pd.getWriteMethod();
                        writeMethod.invoke(target,setObject);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
