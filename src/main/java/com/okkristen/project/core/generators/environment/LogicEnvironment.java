package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

/**
 *某个实体相关业务的 一系列环境
 */
@Getter
@Setter
public class LogicEnvironment {

    /**
     * 一个 控制层
     */
    private ControllerEnvironment controllerEnvironment;

    /**
     * 一个业务接口层
     */
    private  ServiceEnvironment serviceEnvironment;

    /**
     * 一个接口实现层
     */
    private ServiceImplEnvironment serviceImplEnvironment;


    /**
     * 一个dao层
     */
    private DaoEnvironment daoEnvironment;

    /**
     * 一个相对应的实体类
     */
    private EntityEnvironment entityEnvironment;

    /**
     * 一个相对应的DTO类
     */
    private  DtoEnvironment dtoEnvironment;

    /**
     * 必须只能利用 这个来构造 或者说以后有其他的构造方法 这个是主要的
     * @param controllerEnvironment 控制层
     * @param serviceEnvironment 业务层
     * @param serviceImplEnvironment 服务层
     * @param daoEnvironment 持久层
     * @param entityEnvironment 实体类
     * @param dtoEnvironment Dto
     */
    public LogicEnvironment(ControllerEnvironment controllerEnvironment, ServiceEnvironment serviceEnvironment, ServiceImplEnvironment serviceImplEnvironment, DaoEnvironment daoEnvironment, EntityEnvironment entityEnvironment, DtoEnvironment dtoEnvironment) {
        this.controllerEnvironment = controllerEnvironment;
        this.serviceEnvironment = serviceEnvironment;
        this.serviceImplEnvironment = serviceImplEnvironment;
        this.daoEnvironment = daoEnvironment;
        this.entityEnvironment = entityEnvironment;
        this.dtoEnvironment = dtoEnvironment;
    }
}
