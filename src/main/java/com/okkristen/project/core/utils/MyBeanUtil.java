package com.okkristen.project.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.logic.test.entity.ExamineItem;
import org.hibernate.LazyInitializationException;
import org.hibernate.TransientObjectException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;


/**
 * 先书写 DTO 与Entity类之间的转换
 */

/**
 * bean 工具类
 */
public class MyBeanUtil extends org.springframework.beans.BeanUtils {

//    public static void copyProperties(Object source, Object target) {
//        checkObejectNull(source);
//        checkObejectNull(target);
//        BeanUtils.copyProperties(source, target);
//    }
    public  static  void  copyObjectProperties(Object source, Object target) {
//        copyObjectProperties(source, target,new HashMap<>(), false);
        checkObejectNull(source);
//        copyObjectProperties(source, target,new HashMap<>(), false);
//        Comparator<Object> com = new Comparator<Object>() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return o1.getClass().equals(o2.getClass()) ? 0 : -1;
//            }
//        };
//        Set<Object> set = new HashSet<Object>();
        Map<Object,Object> map = new HashMap();
//        set.add(target);
        copyObjectProperties(source, target,false,map);
    }

    /**
     *
     * @param source
     * @param target
     * @param isFlag 返回页面为ture 返回数据库 为 false
     * @param objects
     */
    public static void  copyObjectProperties(Object source, Object target,Boolean isFlag,Map<Object,Object> objects) {
        Assert.isTrue(source != null, "来源对象不能为空");
        Assert.isTrue(target != null, "目标对象不能为空");
       try {
            // 获取 目标对象的属性描述器
            List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getPropertyDescriptor(target.getClass(),MyReflectionUtil.getFileds(target.getClass(),new ArrayList<>()));
            // 循环目标用户的对象
            for (PropertyDescriptor pd: propertyDescriptorList) {
                // 获取 属性类型
                Class<?> tClass = pd.getPropertyType();
                // get 方法名
                String readMethodName = pd.getReadMethod().getName();
                // 从 来源对象中 获取 数据类型
                Object getObject = null;
                try {
                    getObject = MyReflectionUtil.invokeMethod(source,readMethodName);
                } catch (TransientObjectException e) {
                    continue;
                }
                if (getObject == null) {
                    continue;
                }
                Method write = pd.getWriteMethod();
                // 设置对象属性为 String 类型的
                if (!MyReflectionUtil.isLgnore(getObject)) {
                    try {
                        write.invoke(target,getObject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else if (getObject instanceof  Collection) {
                    Collection setCollection = new ArrayList();
                    // 集合里的对象
                    Object objectInList = getParameterizedTypeListype(pd);
                    Collection setInObject =(Collection)objects.get(objectInList.getClass());
                    if (setInObject != null) {
                        setCollection = setInObject;
                    }
                    if (!objects.containsKey(objectInList.getClass())) {
                        objects.put(objectInList.getClass(),setCollection);
                        Collection getCollection = (Collection) getObject;
                        Iterator iterator = getCollection.iterator();
                        while (iterator.hasNext()) {
                            Object getListContent = iterator.next();
                            Object setListContent = getParameterizedTypeListype(pd);
                            copyObjectProperties(getListContent,setListContent,isFlag,objects);
                            setCollection.add(setListContent);
                        }
                    } else {
                        if (isFlag) {
                            setCollection = null;
                        }
                    }
                    try {
                        write.invoke(target, setCollection);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
    //                用户自定义类型获取
                } else {
                    if (checkObejectNull(getObject)) {
                        Object setObject = pd.getPropertyType().newInstance();
                        Object setInObject = objects.get(setObject.getClass());
                        if (setInObject != null) {
                            setObject = setInObject;
                        }
                        if (!objects.containsKey(setObject.getClass())) {
                            objects.put(setObject.getClass(), setObject);
                            copyObjectProperties(getObject,setObject,isFlag,objects);
                        } else {
                            if (isFlag) {
                                setObject = null;
                            }
                        }
                        Method writeMethod = pd.getWriteMethod();
                        writeMethod.invoke(target,setObject);
                    }
                }
            }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }



    /**
     * @param source 来源对象
     * @param target 目标对象
     * @param adressMap 保存用户自定义类型
     * @param isLgnoreCustom 是否忽略自定义类型
     */
    public  static  void  copyObjectProperties(Object source, Object target,HashMap<Object,Object> adressMap,Boolean isLgnoreCustom) {
        Assert.isTrue(source != null, "来源对象不能为空");
        Assert.isTrue(target != null, "目标对象不能为空");
        try {
            // 普通类型全部复制
            copyProperties(source,target);
            List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getPropertyDescriptor(target.getClass(),MyReflectionUtil.getFileds(target.getClass(),new ArrayList<>()));
            // 目标对象 的属性描述器 list
            for (PropertyDescriptor pd: propertyDescriptorList) {
                Class<?> tClass = pd.getPropertyType();
                Method readMethod = pd.getReadMethod();
                Object getObject = MyReflectionUtil.invokeMethod(source,pd.getReadMethod().getName());
                if (getObject != null && checkObejectNull(getObject)) {
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
                               Object setObjectConent =  getParameterizedTypeListype(pd);
                               if (setObjectConent == null) {
                                   continue;
                               }
                               if (checkObejectNull(getObject)) {
                                   copyObjectProperties(getObjectConent,setObjectConent,adressMap,false);
                               }
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
                            if (checkObejectNull(getObject)) {
                                copyObjectProperties(getObject, setObject, adressMap, false);
                            }
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
//        System.out.println(getObject);
        List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getPropertyDescriptor(getObject.getClass(),MyReflectionUtil.getFileds(getObject.getClass(),new ArrayList<>()));
        Boolean flag = false;
        for (PropertyDescriptor pd : propertyDescriptorList) {
            Method readMethod = pd.getReadMethod();
            Object getValue = null;
            try {
                getValue = MyReflectionUtil.invokeMethod(readMethod,getObject);
            } catch (TransientObjectException e) {
                continue;
            }
            if (getValue == null || getValue instanceof Collection) {
                continue;
            }
            if (String.class.equals(pd.getPropertyType())) {
                if (!StringUtils.isEmpty(getValue)) {
                    return true;
                } else {
                    dealObejectNull(getObject, null,pd);
                }
            } else {
                // 如果是忽略里面的 类型直接判断 是否为null 即可
                if (!MyReflectionUtil.isLgnore(pd.getPropertyType()) || !MyReflectionUtil.isLgnore(getValue)) {
                    if (getValue != null) {
                        return  true;
                    }
                } else {
                    if (checkObejectNull(getValue)) {
                        return true;
                    } else {
                        dealObejectNull(getObject, null, pd);
                        flag = false;
                    }
                }
            }
        }
        return  flag;
    }
    public static  void dealObejectNull(Object dealObject,Object dealValue, PropertyDescriptor pd) {
       Method write =  pd.getWriteMethod();
       if (write == null) {
           return;
       }
        try {
            write.invoke(dealObject,dealValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * 利用 JSON 来复制一部分信息
     */
    public  static  <T>T  copyJsonObjectProperties(Object source, T target) {
       String sourceString = JSONObject.toJSONString(source);
       return  JSONObject.parseObject(sourceString, (Class<T>) target.getClass());
    }

    /**
     * 利用 FastJSON 来转换 实体与DTO 相互转换
     * @param source
     * @param tClass
     * @param <T>
     * @return
     */
    public  static  <T>T  copyJsonObjectProperties(Object source, Class<T> tClass) {
        String sourceString = JSONObject.toJSONString(source);
        return  JSONObject.parseObject(sourceString, tClass);
    }

    /**
     * 合并对象
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T>T mergeJsonObjectProperties(Object source,Object target, Class<T> tClass){
        JSONObject targetJson = JSONObject.parseObject(JSONObject.toJSONString(target));
        JSONObject sourceJson = JSONObject.parseObject(JSONObject.toJSONString(source));
        targetJson.putAll(sourceJson);
        return  JSONObject.parseObject(targetJson.toJSONString(),tClass);
    }
    /**
     * 利用属性描述器 来获取list 里面的 类型
     */
    public static Object getParameterizedTypeListype (PropertyDescriptor pd) {
        Type[] types = ((ParameterizedType)pd.getReadMethod().getGenericReturnType()).getActualTypeArguments();
        try {
            return  ((Class)types[0]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 获取Set中 相对应的 对象
     */
    private static Object getObjectInSet(Set<Object> objects, Object object){
        Iterator<Object> iterator = objects.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o.getClass().equals(object.getClass())) {
                return  o;
            }
        }
        return  null;
    }
}
