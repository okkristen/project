package com.okkristen.project.core.repository.utils;

import com.okkristen.project.core.utils.MyReflectionUtil;

import javax.persistence.criteria.*;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
       List<PropertyDescriptor> propertyDescriptorList =  MyReflectionUtil.getNotNullPropertyDescriptor(example.getClass(),example);





        return  null;
    }

    /**
     * 获取查询的path
     * @param root
     * @return
     */
    private  static <T>List<Path> getPaths(Root<T> root, final  T example) {
        List<String> list = MyReflectionUtil.getFieldName(example);
        Map<String,String> fieldType = MyReflectionUtil.getFiledType(example.getClass());
        List<Path> paths = new ArrayList<>();
        for(String name: list) {
            if (MyReflectionUtil.isLgnoreFieldName(name) && MyReflectionUtil.isLgnore(fieldType.get(name))) {
                Path path = root.get(name);
                paths.add(path);
            }
        }
        return  paths;
    }
    /**
     *创建搜索条件
     */
    private static <T>List<Predicate> getPredicate (List<Path> paths, CriteriaBuilder criteriaBuilder, final  T example) {
        List<Predicate> list = new ArrayList<>();
        for (Path path: paths) {
            Predicate predicate = criteriaBuilder.like(path, "");
            list.add(predicate);
        }
        return list;
    }
    /**
     * 根据数据类型的不同  选择不同的 方法
     */
    private static  <T>Predicate getPredicate (Path path,T exmple, CriteriaBuilder criteriaBuilder ) {
        List<Field> fields = MyReflectionUtil.getFileds(exmple.getClass(),new ArrayList<>());
        for (Field field: fields) {
            Object o = MyReflectionUtil.getFieldValue(field,exmple);
            Object type = MyReflectionUtil.getFieldType(field);
        }
        return  null;
    }
}
