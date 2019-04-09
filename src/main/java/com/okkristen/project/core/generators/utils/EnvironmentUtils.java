package com.okkristen.project.core.generators.utils;

import com.okkristen.project.core.generators.environment.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 环境工具类
 */
public class EnvironmentUtils {

    /**
     * 获取 业务环境
     */
    public LogicEnvironment getLogicEnvironment() {
        return new LogicEnvironment(
                getControllerEnvironment(),
                getServiceEnvironment(),
                getServiceImplEnvironment(),
                getDaoEnvironment(),
                getEntityEnvironment(),
                getDtoEnvironment()
        );
    }

    /**
     * 获取控制层环境
     * @return
     */
    public ControllerEnvironment getControllerEnvironment () {
        String entityName = "database";
        ControllerEnvironment controllerEnvironment = new ControllerEnvironment();
        controllerEnvironment.setControllerName(entityName + "Controller");
        controllerEnvironment.setControllerApiPath(controllerEnvironment.getPrefix() +"/" + entityName);
        String path = controllerEnvironment.getProjectComPtah() + controllerEnvironment.getProjectPath().replaceAll(".", "/") + "controller";
        controllerEnvironment.setControllerPath(path);
        controllerEnvironment.setServiceImplEnvironments(new ArrayList<>(Arrays.asList(getServiceImplEnvironment())));
        return controllerEnvironment;
    }

    /**
     * 获取业务层环境 接口
     * @return
     */
    public ServiceEnvironment getServiceEnvironment () {
        String entityName = "database";
        ServiceEnvironment serviceEnvironment = new ServiceEnvironment();
        serviceEnvironment.setServiceName(entityName + "Serivce");
        serviceEnvironment.setServicePath("login." +entityName + ".service");
        return serviceEnvironment;
    }

    /**
     * 获取业务层环境 实现
     * @return
     */
    public ServiceImplEnvironment getServiceImplEnvironment () {
        String entityName = "database";
        ServiceImplEnvironment serviceEnvironment = new ServiceImplEnvironment();
        serviceEnvironment.setServiceImplName(entityName + "SerivceImpl");
        serviceEnvironment.setServiceImplPath("login." +entityName + ".Impl.service");
        serviceEnvironment.setDaoEnvironments(new ArrayList<DaoEnvironment>(Arrays.asList(getDaoEnvironment())));
        return serviceEnvironment;
    }

    public  DaoEnvironment getDaoEnvironment() {
        String entityName = "database";
        DaoEnvironment daoEnvironment = new DaoEnvironment();
        daoEnvironment.setDaoName(entityName + "Repository");
        daoEnvironment.setDaoPath("login." +entityName + ".dao");
        return daoEnvironment;
    }
    /**
     * 获取实体类环境
     * @return
     */
    public EntityEnvironment getEntityEnvironment () {
        String entityName = "database";
        entityName = entityName.substring(0,1).toUpperCase() + entityName.substring(1);
        EntityEnvironment entityEnvironment = new EntityEnvironment();
        entityEnvironment.setEntityName(entityName);
        entityEnvironment.setEntityPath("login." +entityName + ".entity" );
        entityEnvironment.setEntityAllName(entityEnvironment.getProjectPath() + ".logic. "+ entityName + ".entity");
        // 属性
        entityEnvironment.setPropertyEnvironments(new ArrayList<>());
        return entityEnvironment;
    }

    /**
     * 获取DTO类环境
     * @return
     */
    public DtoEnvironment getDtoEnvironment () {
        String dtoName = "database";
        dtoName = dtoName.substring(0,1).toUpperCase() + dtoName.substring(1) + "DTO";
        DtoEnvironment dtoEnvironment = new DtoEnvironment();
        dtoEnvironment.setDtoName(dtoName);
        dtoEnvironment.setDtoPath("login." + dtoName + ".dto");
        dtoEnvironment.setDtoAllName(dtoEnvironment.getProjectPath() + ".logic. "+ dtoName + ".dto");
        dtoEnvironment.setPropertyEnvironments(new ArrayList<>());
        return dtoEnvironment;
    }
}
