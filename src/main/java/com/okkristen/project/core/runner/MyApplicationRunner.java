package com.okkristen.project.core.runner;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.core.JavaScan.ClassScannerUtils;
import com.okkristen.project.core.global.GlobalUtils;
import com.okkristen.project.core.javassist.JavassistUtil;
import com.okkristen.project.core.shrio.MyRealm.MyShrioRealm;
import javassist.ClassPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@Order(1)
public class MyApplicationRunner implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(MyShrioRealm.class);
//    @Autowired
//    private GlobalUtils globalUtils;


    @Override
    public void run(ApplicationArguments args) throws Exception {
//       扫描 程序里的所有的实体类以及DTO 加入map
        Set<Class<?>> classes = ClassScannerUtils.searchClasses("com.okkristen.project");
        Map<String,Class<?>> typesMap = GlobalUtils.typesMap;
        for (Class<?> clazz : classes) {
            if (!clazz.isInterface()) {
              Class<?> superClass = clazz.getSuperclass();
              if (superClass.equals(BaseEntity.class) || superClass.equals(BaseDTO.class)) {
                  if (typesMap.containsKey(clazz.getSimpleName())) {
                      logger.error("程序内实体类名字重复");
                      return;
                  } else {
                      typesMap.put(clazz.getSimpleName(),clazz);
                  }
              }
            }
        }
////        利用map 初始化 JavassistUtil 类型池
        ClassPool pool = JavassistUtil.getClassPool();
        Set<Map.Entry<String,Class<?>>> entrySet = typesMap.entrySet();
        for (Map.Entry<String,Class<?>> entry : entrySet) {
            pool.makeClass(entry.getValue().getName());
        }
        System.out.println("静态资源初始化");
    }
}
