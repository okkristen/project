package com.okkristen.project.core.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityNotFoundException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * 拷贝对象
 * @author fengw
 * @create 2017年1月13日
 */
public class MyBeanUtils extends BeanUtils {
	private static final Logger logger = LogManager.getLogger(MyBeanUtils.class);
	/**
	 * 复制对象source到target 
	 * <p>需要两个对象get,set方法名相同，且类型相同(DTO和Entity的互转例外)</p>
	 * @param source 原对象
	 * @param target 目标对象
	 * @author fengw
	 * @create 2017年2月10日
	 */
	public static void copyProperties(Object source, Object target) {
		// 对source进行预处理，将内容为null的对象替换为null
//		dealObjectWithIsNull(source);
		copyProperties(source, target, true);
	}
    public static void copyProperties(Object source, Object target, boolean checkNull) {
        // 对source进行预处理，将内容为null的对象替换为null
        if (checkNull) {
            dealObjectWithIsNull(source);
        }
        copyProperties(source, target, (String[]) null);
    }
	/**
	 * 复制对象source到target
	 * <p>需要两个对象get,set方法名相同，且类型相同(DTO和Entity的互转例外)</p>
	 * @param source 原对象
	 * @param target 目标对象
	 * @param ignoreProperties 排除字段
	 * @author fengw
	 * @create 2017年2月10日
	 */
	public static void copyProperties(Object source, Object target, String... ignoreProperties) {
		copyProperties(source, target, null, 0, false, ignoreProperties);
	}
	/**
	 * 
	 * @param source
	 * @param target
	 * @param objects 临时对象存储，用于存储需要转换的对象键值对，在二次使用时直接获取，不用再次进行转换
	 * @param level 方法的深度
	 * @param setLoopObj 是否为循环嵌套阶段
	 * @param ignoreProperties
	 * @author fengw
	 * @create 2017年2月10日
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void copyProperties(Object source, Object target, Map<Object, Set<Object>> objects, int level, boolean setLoopObj, String... ignoreProperties) {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		
		// 是否重新进行objects中键值对的copy
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = null;
							try {
								value = readMethod.invoke(source);
							} catch (InvocationTargetException  e) {
								logger.error(e.getMessage());
								System.out.println("被假删了");
							}
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							// 特殊处理entity转DTO，和DTO转Entity的情况
							if (value != null) {
								// 将objects中的对象如果有相互嵌套的话进行赋值
								if (setLoopObj) {
									if (objects.containsKey(value)
											&& getTargetObjectByType(objects.get(value), writeMethod.getParameterTypes()[0]) != null) {
										writeMethod.invoke(target, getTargetObjectByType(objects.get(value), writeMethod.getParameterTypes()[0]));
									}
								} else {
									if (objects == null) {
										objects = new HashMap<Object, Set<Object>>();
									}
									// 如果已有该value对应的目标对象，则直接赋值
									if (objects.containsKey(value)
											&& getTargetObjectByType(objects.get(value), writeMethod.getParameterTypes()[0]) != null) {
										writeMethod.invoke(target, getTargetObjectByType(objects.get(value), writeMethod.getParameterTypes()[0]));
									} else {
										// 获取并判断target的相应变量是否与将要Set的value相同，如果不同，进行Set操作
										Method dtoReadMethod = targetPd.getReadMethod();
										Object readObject = dtoReadMethod.invoke(target);
										if (readObject != value) {
											// 如果objects中没有当前source,target集合的键值对，则添加上，这样后面如果需要set当前target，可以直接取
											if (!objects.containsKey(source)) {
												Set<Object> targetSet = new HashSet<>();
												targetSet.add(target);
												objects.put(source, targetSet);
											}
											if (writeMethod.getParameterTypes()[0].getName().endsWith("DTO")) {
												// Entity 转 DTO
												// 如果已记录该value，但还没有目标对象，则暂不处理
												if (objects.containsKey(value)
														&& hasTargetClass(objects.get(value), writeMethod.getParameterTypes()[0])) {
													continue;
												} else {
													Class<?> dtoClass = writeMethod.getParameterTypes()[0];
													if (!objects.containsKey(value)) {
														Set<Object> targetSet = new HashSet<>();
														objects.put(value, targetSet);
													}
													// 使用目标的Class暂时代替目标类
													objects.get(value).add(dtoClass);
													Object dto = dtoClass.newInstance();
													copyProperties(value, dto, objects, level + 1, false, ignoreProperties);
													writeMethod.invoke(target, dto);
													addToSet(objects, value, dto);
												}
											} else if (value instanceof Collection && ((Collection)value).size() > 0) {
												// 集合处理
												Type[] types = ((ParameterizedType)writeMethod.getGenericParameterTypes()[0]).getActualTypeArguments();
												Class<?> setClass = (Class<?>)types [0];
												if (setClass.getName().endsWith("DTO")) {
													// EntityList 转 DTOList
													List dtoList = new ArrayList<>();
													for (Object obj: (Collection)value) {
														// 如果已有该value对应的目标对象，则直接赋值
														if (objects.containsKey(obj)
																&& getTargetObjectByType(objects.get(obj), setClass) != null) {
															dtoList.add(getTargetObjectByType(objects.get(obj), setClass));
														} else {
															// 如果已记录该value，但还没有目标对象，则暂不处理
															if (objects.containsKey(obj)
																	&& hasTargetClass(objects.get(obj), setClass)) {
																continue;
															} else {
																// 如果尚未记录该value，则记录该Value，并put到Objects中
																if (!objects.containsKey(value)) {
																	Set<Object> targetSet = new HashSet<>();
																	objects.put(obj, targetSet);
																}
																// 使用目标的Class暂时代替目标类
																objects.get(obj).add(setClass);
																Object dto = setClass.newInstance();
																copyProperties(obj, dto, objects, level + 1, false, ignoreProperties);
																dtoList.add(dto);
																addToSet(objects, obj, dto);
															}
														}
													}
													writeMethod.invoke(target, dtoList);
												} else {
													types = ((ParameterizedType)readMethod.getGenericReturnType()).getActualTypeArguments();
													Class<?> entityClass = (Class<?>)types [0];
													if (entityClass.getName().endsWith("DTO")) {
														// DTOList to EntityList
														List entityList = new ArrayList<>();
														for (Object obj: (Collection)value) {
															// 如果已有该value对应的目标对象，则直接赋值
															if (objects.containsKey(obj)
																	&& getTargetObjectByType(objects.get(obj), setClass) != null) {
																entityList.add(getTargetObjectByType(objects.get(obj), setClass));
															} else {
																// 如果已记录该value，但还没有目标对象，则暂不处理
																if (objects.containsKey(obj)
																		&& hasTargetClass(objects.get(obj), setClass)) {
																	continue;
																} else {
																	// 如果尚未记录该value，则记录该Value，并put到Objects中
																	if (!objects.containsKey(value)) {
																		Set<Object> targetSet = new HashSet<>();
																		objects.put(obj, targetSet);
																	}
																	// 使用目标的Class暂时代替目标类
																	objects.get(obj).add(setClass);
																	Object entity = setClass.newInstance();
																	copyProperties(obj, entity, objects, level + 1, false, ignoreProperties);
																	entityList.add(entity);
																	addToSet(objects, obj, entity);
																}
															}
														}
														writeMethod.invoke(target, entityList);
													} else {
														writeMethod.invoke(target, value);
													}
												}
											} else if (readMethod.getReturnType().getName().endsWith("DTO")) {
												// 如果已记录该value，但还没有目标对象，则暂不处理
												if (objects.containsKey(value)
														&& hasTargetClass(objects.get(value), writeMethod.getParameterTypes()[0])) {
													continue;
												} else {
													if (!objects.containsKey(value)) {
														Set<Object> targetSet = new HashSet<>();
														objects.put(value, targetSet);
													}
													// DTO 转 Entity
													Class<?> entityClass = writeMethod.getParameterTypes()[0];
													// 使用目标的Class暂时代替目标类
													objects.get(value).add(entityClass);

													Object entity = entityClass.newInstance();
													copyProperties(value, entity, objects, level + 1, false, ignoreProperties);
													writeMethod.invoke(target, entity);
													addToSet(objects, value, entity);
												}
											} else {
												writeMethod.invoke(target, value);
											}
										}
									}
								}
							} else {
								writeMethod.invoke(target, value);
							}
						}
						catch (Throwable ex) {
							throw new FatalBeanException(
									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
		// 获取到新的键值对时，重新转换，这样可以将循环嵌套互相SET进去
		if (objects != null && level == 0) {
			for (Object temp: objects.keySet()) {
				if (objects.get(temp) != null && !objects.get(temp).isEmpty()) {
					for (Object obj: objects.get(temp)) {
						copyProperties(temp, obj, objects, level + 1, true, ignoreProperties);
					}
				}
			}
		}
	}
	/**
	 * 判断是否有目标Class,该结果与有目标对象互斥
	 * @param targetObjs
	 * @param targetClass
	 * @return
	 * @author fengw
	 * @create 2017年2月23日
	 */
	private static boolean hasTargetClass(Set<Object> targetObjs, Class<?> targetClass) {
		if (getTargetObjectByType(targetObjs, targetClass) != null) {
			return false;
		}
		for (Object obj: targetObjs) {
			if (obj == targetClass) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 针对一个Entity转换为多个DTO的情况，从target集合中获取与目标DTO相同类型的对象
	 * @param targetObjs
	 * @param targetClass
	 * @return
	 * @author fengw
	 * @create 2017年2月23日
	 */
	private static Object getTargetObjectByType(Set<Object> targetObjs, Class<?> targetClass) {
		for (Object obj: targetObjs) {
			if (obj.getClass() == targetClass) {
				return obj;
			}
		}
		return null;
	}
	
	private static Map<Object, Set<Object>> addToSet(Map<Object, Set<Object>> objects, Object source, Object target) {
		for (Object obj: objects.get(source)) {
			if (obj == target.getClass()) {
				objects.get(source).remove(obj);
				objects.get(source).add(target);
				break;
			}
		}
		return objects;
	}
	
	/**
	 * 判断对象是否为null
	 * 如果对象内所有属性为null，则对象同样为null
	 * @param object
	 * @return
	 * @author fengw
	 * @create 2016年12月28日
	 */
	public static Boolean objectIsNull(Object object) {
		return checkObjectIsNull(object, null);
	}
	/**
	 * 处理object内部属性，如果都为null的话，则将该object设为null
	 * 包含object内部属性的属性，迭代设置
	 * @param object
	 * @return
	 * @author fengw
	 * @create 2016年12月28日
	 */
	public static void dealObjectWithIsNull(Object object) {
		dealObjectWhenIsNull(object, null);
	}
	/**
	 * 处理object内部属性，如果都为null的话，则将该object设为null
	 * 包含object内部属性的属性，迭代设置
	 * @param object
	 * @param objects 存储已经判断的value，防止循环嵌套的对象进入死循环
	 * @author fengw
	 */
	@SuppressWarnings("rawtypes")
	public static void dealObjectWhenIsNull(Object object, Set<Object> objects) {
		if (object != null) {
			if (isIgnore(object)) {
				return;
			}
			PropertyDescriptor[] pds = getPropertyDescriptors(object.getClass());
			for (PropertyDescriptor pd : pds) {
				Method readMethod = pd.getReadMethod();
				Method writeMethod = pd.getWriteMethod();
				if (readMethod != null && writeMethod != null &&
						ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
					try {
						String[] ignoreMethod = {"getHandler", "getClass"};
						if (Arrays.asList(ignoreMethod).contains(readMethod.getName())) {
							continue;
						}
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(object);
						// 处理value不为null的情况
						if (value != null) {
							if (isIgnore(value)) {
								continue;
							} else if (objectIsNull(value)) {
								value = null;
							} else {
								if (objects == null) {
									objects = new HashSet<>();
								}
								if (objects.contains(value)) {
									continue;
								} else {
									if (value instanceof Collection && ((Collection)value).size() == 0) {
										value = null;
									} else {
										objects.add(value);
										dealObjectWhenIsNull(value, objects);
									}
								}
							}
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(object, value);
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 判断对象是否为null
	 * 如果对象内所有属性为null，则对象同样为null
	 * @param object
	 * @param objects 存储已经判断的value，防止循环嵌套的对象进入死循环
	 * @return
	 * @author fengw
	 * @create 2016年12月28日
	 */
	public static Boolean checkObjectIsNull(Object object, Set<Object> objects) {
		if (object != null) {
			if (isIgnore(object)) {
				return false;
			}
			PropertyDescriptor[] pds = getPropertyDescriptors(object.getClass());
			for (PropertyDescriptor pd : pds) {
				Method readMethod = pd.getReadMethod();
				if (readMethod != null) {
					try {
						String[] ignoreMethod = {"getHandler", "getClass", "getHibernateLazyInitializer"};
						if (Arrays.asList(ignoreMethod).contains(readMethod.getName())) {
							continue;
						}
						if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(object);
						// 处理value为null的情况
						if (value != null) {
							if (isIgnore(value)) {
								return false;
							} else {
								if (objects == null) {
									objects = new HashSet<>();
								}
								if (objects.contains(value)) {
									return false;
								} else {
									if (!checkObjectIsNull(value, objects)) {
										return false;
									} else if (!(value instanceof Collection)) {
										objects.add(value);
									}
								}
							}
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						if (((InvocationTargetException) e).getTargetException() instanceof EntityNotFoundException) {
							logger.info(((InvocationTargetException) e).getTargetException().getMessage());
							System.out.println("被假删了" + ((InvocationTargetException) e).getTargetException().getMessage());
						} else {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}
	/**
	 * 判断对象是不是忽略判断的字段
	 * 包含基本数据类型、日期、时间戳、hibernate临时数据对象
	 * @param object
	 * @return
	 * @author fengw
	 * @create 2017年1月2日
	 */
	public static boolean isIgnore(Object object) {
		if (object.getClass().isPrimitive() ||
				object instanceof Boolean ||
				object instanceof String ||
				object instanceof Double ||
				object instanceof Float ||
				object instanceof Integer ||
				object instanceof Long ||
                object instanceof BigDecimal ||
				object instanceof Date ||
				object instanceof java.sql.Date ||
				object instanceof Timestamp ||
				object instanceof Time ||
				object instanceof PersistentBag) {
			return true;
		}
		return false;
	}

	/**
	 * setIsDel
	 */
//	private static  <T>T setIsDel(T t) {
//		Method getMethod = ReflectionUtils.findMethod(t.getClass(), "getIsDel");
//		if(getMethod == null){
//			return t;
//		}
//		Object isDel = ReflectionUtils.invokeMethod(getMethod,t);
//		if(isDel == null) {
//			Method setMethod = ReflectionUtils.findMethod(t.getClass(), "setIsDel", Boolean.class);
//			if (setMethod !=null) {
//				ReflectionUtils.invokeSetterMethod(t.getClass(),"setIsDel",t, Boolean.class,Boolean.FALSE);
//			}
//		}
//		return  t;
//	}
}
