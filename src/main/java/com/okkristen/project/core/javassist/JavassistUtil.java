package com.okkristen.project.core.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.*;

import java.util.*;

/**
 * @author ysj
 * @create 2019-01-31
 **/
public class JavassistUtil {


    /**
     * 获取默认池
     * @return
     */
    public static ClassPool getClassPool() {
        return  ClassPool.getDefault();
    }

    /**
     * 获取类
     * @param className 类的全限定类名
     * @return
     */
    public static CtClass getCtClass(String className) {
        return  getClassPool().makeClass(className);
    }

    /**
     * 获取类型
     * @return
     */
    public static  CtClass[] getCtClasses(String [] strings) throws NotFoundException {
        ClassPool pool = getClassPool();
        CtClass[] ctClasses = pool.get(strings);
        return ctClasses;
    }
    /**
     * 增加对象字段
     * @return
     */
    public static CtField addCtField(CtClass ctClass,String fieldName,Class<?> tpyes) throws NotFoundException, CannotCompileException {
        //相当于private String name
        CtField param = new CtField(getClassPool().get(tpyes.getName()), fieldName, ctClass);
        param.setModifiers(Modifier.PRIVATE);
//        测试
        ctClass.addField(param, CtField.Initializer.constant(""));
        return param;
    }

    /**
     * 增加 对象方法
     */
    public static void  addGetMethod(CtClass ctClass, CtField param,String paramName) throws CannotCompileException {
        String methodName = "get" + String.valueOf(paramName.charAt(0)).toUpperCase() + paramName.substring(1);
        ctClass.addMethod(CtNewMethod.getter(methodName, param));
    }
    /**
     * 增加 对象方法
     */
    public static void  addSetMethod(CtClass ctClass, CtField param,String paramName) throws CannotCompileException {
        String methodName = "set" + String.valueOf(paramName.charAt(0)).toUpperCase() + paramName.substring(1);
        ctClass.addMethod(CtNewMethod.setter(methodName, param));
    }

    /**
     * 增加构造函数
     */
    public static  void  addCtConstructor(CtClass[] ctClasses,CtClass ctClass) throws CannotCompileException {
        //相当于public Sclass(){}
        CtConstructor cons = new CtConstructor(ctClasses, ctClass);
        cons.setBody("{}");
        ctClass.addConstructor(cons);
    }

    public static  void  addCtConstructor(CtClass[] ctClasses, CtClass ctClass,List<String> filedNames) throws CannotCompileException {
        CtConstructor cons = new CtConstructor(ctClasses, ctClass);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (filedNames != null && !filedNames.isEmpty()) {
            for (int i = 0; i <filedNames.size() ; i++) {
                sb.append("$0." + filedNames.get(i) + " = $" + (i+1) + ";");
            }
        }
        sb.append("}");
        cons.setBody(sb.toString());
        ctClass.addConstructor(cons);
    }

    /**
     * 创建对象
     * @return
     */
    /**
     *
     * @param className 全限定名
     * @param filedNames 字段map 类型与属性名
     * @return
     * @throws NotFoundException
     * @throws CannotCompileException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static  Object createClass (String className, Map<String,Class<?>> filedNames) throws NotFoundException, CannotCompileException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        利用全路径名 创建对象
        CtClass ctClass =  getCtClass(className);
////       在对象中添加 字段与setter geter 方法
      if (!filedNames.isEmpty()) {
          Set<Map.Entry<String, Class<?>>> entities =  filedNames.entrySet();
          for (Map.Entry<String, Class<?>> entry: entities) {
              String key = entry.getKey();
              Class<?> clazz = entry.getValue();
              CtField param = addCtField(ctClass,key,clazz);
              addGetMethod(ctClass,param,key);
              addSetMethod(ctClass,param,key);
          }
      }
      addCtConstructor(new CtClass[]{},ctClass);
      ctClass.toClass();
      return Class.forName(className).newInstance();
    }
}
