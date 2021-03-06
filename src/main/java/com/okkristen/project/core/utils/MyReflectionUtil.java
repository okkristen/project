package com.okkristen.project.core.utils;

import ch.qos.logback.classic.turbo.TurboFilter;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.*;

public class MyReflectionUtil extends ReflectionUtils {

    /**
     * 根据 方法名执行方法
     * @param object
     * @param methodName
     * @return
     */
    public static Object invokeMethod(Object object,String methodName) {
        Method method = findMethod(object.getClass(),methodName);
        if (method == null) {
            return  null;
        }
        return  invokeMethod(method,object);
    }
    /**
     * 根据 方法名执行方法
     * @param object
     * @param methodName
     * @return
     */
    public static Object invokeMethodParam(Object object,String methodName,Object ... param) {
        Method method = findMethod(object.getClass(),methodName);
        if (method == null) {
            return  null;
        }
        return  invokeMethod(method,object,param);
    }
    /**
     * 根据对象 来获取对象有的属性以及属性名
     */
    public static List<String> getFieldName(Object exmple) {
        Class tClass = exmple.getClass();
        List<String> fieldName = new ArrayList<>();
        List<Field> fields = getFileds(tClass,new ArrayList<>());
        for (Field field : fields) {
             fieldName.add(field.getName());
        }
        return fieldName;
    }

    /**
     * 获取 对象中所有的字段
     * @param clazz
     * @param fields
     * @return
     */
    public static List<Field> getFileds(Class clazz, List<Field> fields) {
        Field[] field = clazz.getDeclaredFields();
        fields.addAll(Arrays.asList(field));
        if (clazz.getSuperclass() != null) {
            List<Field> fieldList = getFileds(clazz.getSuperclass(), fields);
        }
        return  fields;
    }

    /**
     * 获取 属性描述器 里面包含  读写方法
     * @param clazz
     * @return
     */
    public static List<PropertyDescriptor>  getPropertyDescriptor (Class clazz, List<Field> fields) {
        List<PropertyDescriptor> list = new ArrayList<>();
        if (fields == null || fields.isEmpty()) {
            fields = getFileds(clazz,fields);
        }
        for (Field field : fields) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
                list.add(pd);
            } catch (IntrospectionException e) {
                System.out.println("对象没有相对应的方法");
//                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     *获取字段不为bull  并且不为空字符串 的属性
     */
    public static  <T>List<PropertyDescriptor> getNotNullPropertyDescriptor (Class clazz, T exmple) {
      List<PropertyDescriptor> propertyDescriptors = getPropertyDescriptor(clazz, new ArrayList<Field>());
      List<PropertyDescriptor> hasVlaueProertyDesctoptors = new ArrayList<>();
      for (PropertyDescriptor pb: propertyDescriptors) {
          Object value = invokeMethod(pb.getReadMethod(),exmple);
          System.out.println(pb.getName());
          if (value != null) {
              if (value instanceof String && StringUtils.isEmpty(value)) {
                  continue;
              }
              hasVlaueProertyDesctoptors.add(pb);
          }
      }
      return  hasVlaueProertyDesctoptors;
    }

    /**
     * 根据 对象  获取 对象相对应的属性
     */
    public static Map<String,String> getFiledType(Class clazz) {
        List<Field> fields = getFileds(clazz,new ArrayList<>());
        Map<String,String> fileType = new HashMap<>();
        for (Field field : fields) {
            fileType.put(field.getName(),getFieldType(field));
        }
        return  fileType;
    }
    /**
     * 根据属性 来获取 属性类型
     */
    public static  String getFieldType (Field field) {
        return  field.getGenericType().toString();
    }
    /**
     * 根据 属性 获取 属性的值
     */
    public static  Object getFieldValue (Field field, Object object){
        String methodName = MyStringUtil.getGetMethodName(field.getName());
        Object fieldValue = invokeMethod(object,methodName);
        return fieldValue != null ? fieldValue : null;
    }
    /**
     * 忽略字段名字的方法
     * @param fieldName
     * @return
     */
    public static  Boolean isLgnoreFieldName(String fieldName){
        return  !(fieldName.indexOf("start") != -1 || fieldName.indexOf("end") != -1);
    }
    /**
     * 忽略字段属性 方法
     * 返回真 不忽略  返回假 忽略
     */
    public static  Boolean isLgnore(String fieldType){
        List<String> isBaseClass = Arrays.asList("long","byte","int", "float","double", "boolean", "short", "char");
        if (isBaseClass.contains(fieldType)) {
            return  false;
        }
        try {
            Class clazz = Class.forName(fieldType);
            return  isLgnore(clazz);
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
            return true;
        }
    }
    /**
     * 忽略字段 方法
     * 返回真 不忽略  返回假 忽略
     */
    public static  Boolean isLgnore(Class clazz){
        List<Class> classes = new ArrayList<>();
        // 基本数据类型 的包装类 以及包装类
//        classes.add(String.class);
        classes.add(Integer.class);
        classes.add(Boolean.class);
        classes.add(Float.class);
        classes.add(Byte.class);
        classes.add(Short.class);
        classes.add(Character.class);
        classes.add(Double.class);
        classes.add(Long.class);
//      忽略类型
        classes.add(Date.class);
        classes.add(BigDecimal.class);
        return !classes.contains(clazz);
    }
    /**
     * 获取当前类的的泛型
     */
    public  <T>Class<T> getParameterizedType(Class<T> tClass) {
        return  null;
//        Class<D> dc = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
//    object.getClass().isPrimitive() ||
//				object instanceof Boolean ||
//				object instanceof String ||
//				object instanceof Double ||
//				object instanceof Float ||
//				object instanceof Integer ||
//				object instanceof Long ||
//                object instanceof BigDecimal ||
//				object instanceof Date ||
//				object instanceof java.sql.Date ||
//				object instanceof Timestamp ||
//				object instanceof Time ||
//				object instanceof PersistentBag
}
