package com.okkristen.project.core.aop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.core.global.GlobalUtils;
import com.okkristen.project.core.javassist.JavassistUtil;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.utils.MyJsonUtil;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 请求的参数以及返回值处理
 */
@Aspect
@Component
public class ControllerAop {

    private static Logger logger = LoggerFactory.getLogger(ControllerAop.class);
    @Pointcut("execution(public * com.okkristen.project.logic..*.*Controller.find*(..))")
    private void findMethod() {}

    @Before("findMethod()")
    public void doBefore(JoinPoint joinPoint) {
    }

    @After("findMethod()")
    public void doAfter(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The afterlogging method "+methodName+" end");
    }
    @Around(value = "findMethod()")
    public Object around(ProceedingJoinPoint pjd) {
        Object result = null;
        String methodName = pjd.getSignature().getName();
        List<Object> args = Arrays.asList(pjd.getArgs());
    //        拿到返回参数
        String className = "com.okkristen.project.entity.Dem" + UUID.randomUUID().toString().replaceAll("-","");
        Map<String,Class<?>>  classMap = getClass(args);
        Object aClass = null;
        try {
            aClass = JavassistUtil.getParam(MyJsonUtil.getJson(args.get(0)).getJSONObject("resultParam"));
            System.out.println(aClass);
//            aClass =  JavassistUtil.createSimpleClass(className,classMap);
            System.out.println(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 前置通知
            System.out.println("The aroundlogging methor" + methodName + " begin with" + args);
            result = pjd.proceed();
            // 返回通知
            System.out.println("The aroundlogging method " + methodName + " end with " + result);
        } catch (Throwable e) {
            System.out.println("The aroundlogging method " + methodName + " occurs excetion: " + e);
            // 异常通知
            e.printStackTrace();
        }
        //后置通知
        System.out.println("The aroundlogging method " + methodName + " end");
        if (result instanceof AjaxResult) {
            AjaxResult ajaxResult = (AjaxResult) result;
            Object data = ajaxResult.getData();
            JSONObject object = MyJsonUtil.getJson(data);
            Object o = object.toJavaObject(aClass.getClass());
            ajaxResult.setData(o);
        }
        return result;
    }

    /**
     * 获取参数返回值格式
     */
    private  Map<String,Class<?>>   getClass (List<Object> args) {
        Map<String,Class<?>> classMap = new HashMap<>();
//       首先测试只有一个参数
        if (args != null && !args.isEmpty()) {
            JSONObject jsonObject = MyJsonUtil.getJson(args.get(0));
            JSONObject returnParam = jsonObject.getJSONObject("resultParam");
            Set<Map.Entry<String, Object>> entries =  returnParam.entrySet();

            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object classType = entry.getValue();
                classMap.put(key, GlobalUtils.typesMap.get(classType.toString()));
            }
            System.out.println("---------------");
            System.out.println(classMap);
            System.out.println("---------------");
        }
        return classMap;
    }

}
