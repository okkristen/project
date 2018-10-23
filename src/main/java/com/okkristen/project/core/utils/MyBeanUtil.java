package com.okkristen.project.core.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * bean 工具类
 */
public class MyBeanUtil extends BeanUtils {
    /**
     * 两个对象 相互复制
     * @param source
     * @param target
     */
    public static void  copyProperties(Object source,Object target) {
        Assert.isTrue(source == null, "来源对象不能为空");
        Assert.isTrue(target == null, "目标对象不能为空");

        List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getNotNullPropertyDescriptor(source.getClass(),source);
        // 获取到 source 的 属性值 不为null的 属性
        for (PropertyDescriptor propertyDescriptor : propertyDescriptorList) {
            // 来源类型的 读取 method
            Method sourceMethod = propertyDescriptor.getReadMethod();
            Object sourceValue = MyReflectionUtil.invokeMethod(sourceMethod, source);

            // 由于 来源与目标的 属性名一致  则 方法名也一致 故 直接用
            String targetMethodName = propertyDescriptor.getWriteMethod().getName();










        }

    }

}
