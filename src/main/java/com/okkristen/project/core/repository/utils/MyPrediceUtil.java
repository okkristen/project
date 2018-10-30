package com.okkristen.project.core.repository.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.core.utils.MyJsonUtil;
import com.okkristen.project.core.utils.MyReflectionUtil;
import org.hibernate.mapping.Collection;
import org.hibernate.mapping.Value;

import javax.persistence.criteria.*;
import java.beans.Expression;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ysj
 * @create 2018-10-23
 **/
public class MyPrediceUtil {
    /**
     * 获取查询 条件
     * @param root
     * @param criteriaQuery
     * @param criteriaBuilder
     * @param <T>
     * @return
     */
    public  static <T>Predicate getPredicate (Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder, final  T example) {
        // 获取 root 里面的所有属性 相对应的 Path 对象
//        List<Path> paths = getPaths(root,example);
        // 根据path 对象 构建出 Predicate 对象条件
//        List<Predicate> predicates = getPredicate(paths,criteriaBuilder,example);
        // 返回一个 最终的对象条件
//        return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        // 获取不为null属性 并且不为字符串的属性的 属性
//       List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getNotNullPropertyDescriptor(example.getClass(),example);
       // 里面包含 这整个对象里面的 有值的信息
       JSONObject exampleJson = JSONObject.parseObject(JSONObject.toJSONString(example, MyJsonUtil.getPropertyFilter()));
        // 把 类组成动态条件 查询出 复杂的 数据
        List<Predicate> list = createPredicate(root,criteriaBuilder,exampleJson, (Class<T>) example.getClass(), null);
        Predicate predicate = criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        return  predicate;
    }

    /**
     * 生成动态查询条件
     * @param root
     * @param exampleJson
     * @param <T>
     * @return
     */
    public  static  <T>List<Predicate> createPredicate (Root<T> root, CriteriaBuilder criteriaBuilder, JSONObject exampleJson, Class<T> tClass,Path path) {
        Set<String> keySet = exampleJson.keySet();
        List<Predicate> predicates = new ArrayList<>();
        Path first = null;
        if (path == null) {
            first  = root;
        } else {
            first = path;
        }
//         三种类型
        for (String key : keySet) {
            System.out.println("key"  + key);
            Object value = exampleJson.get(key);
            System.out.println("value"  + value);
            // 查询时间 范围
            if (MyJsonUtil.ignoreMethod(key)) {
                if (key.indexOf("start") == 0) {
                    String fileName= toLowerCaseFirstOne(key.substring(5));
//                    大于等于
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(first.get(fileName), new Date((Long) value)));
                }
                if (key.indexOf("end") == 0) {
                    String fileName= toLowerCaseFirstOne(key.substring(3));
                    // 小于等于
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(first.get(fileName), new Date((Long) value)));
                }
            } else {
                if (value instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray)value;
                    if (!jsonArray.isEmpty()) {
                        JSONObject fisrtObject = jsonArray.getJSONObject(0);
                        Object valueObject = getTableName(tClass,key,value);
                        String tableName = getTableName(valueObject);
                        Path table = root.join(key, JoinType.LEFT);
                        List<Predicate> predicateList =  createPredicate(root,criteriaBuilder,fisrtObject,(Class<T>) valueObject.getClass(),table);
                        predicates.addAll(predicateList);
                    }
                } else if (!MyReflectionUtil.isLgnore(value)) {
                    predicates.add(criteriaBuilder.like(first.get(key).as(String.class), likeFomat(String.valueOf(value))));
                } else if (value instanceof JSONObject) {
                    JSONObject  jsonObject = (JSONObject)value;
                    Object valueObject = getTableName(tClass,key,value);
                    String tableName = getTableName(valueObject);
                    Path table = root.join(tableName, JoinType.LEFT);
                    List<Predicate> predicateList = createPredicate(root,criteriaBuilder,jsonObject,(Class<T>)valueObject.getClass(),table);
                    predicates.addAll(predicateList);
                }
            }
        }
        return  predicates;
    }

    /**
     * like 查询 增加 前后%
     * @param value
     * @return
     */
    public  static  String likeFomat(String value) {
        return  "%" + value + "%";
    }

    /**
     * 跟据Class 对象 一级 属性对象 来获取 表链接对象
     */

    public static <T>Object getTableName (Class<T> tClass,String attributeName, Object value) {
        try {
            T t = tClass.newInstance();
            Field field = MyReflectionUtil.findField(tClass,attributeName);
            if (value instanceof JSONArray) {
                Class clazz = (Class) ((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
                return  clazz.newInstance();
            } else if (value instanceof  JSONObject) {
                Class clazz =  (Class)field.getGenericType();
                return  clazz.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static String getTableName (Object value) {
        String tableName = value.getClass().getName();
        String className = tableName.substring(tableName.lastIndexOf(".") + 1);
       return new StringBuilder().append(className.substring(0,1).toLowerCase()).append(className.substring(1)).toString();
    }
    public static  String toLowerCaseFirstOne(String s){
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
