package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 控制层的环境
 */
@Getter
@Setter
public class ControllerEnvironment extends ProjectEnvironment {

    /**
     *  控制层名称 --- 默认是实体类名字 + Controller
     */
    private String controllerName;

    /**
     * 控制层  类上的路径
     */
    private String controllerApiPath;

    /**
     * 存放路径 也就是 真实文件存放位置 以及 controller 包路径
     */
    private String controllerPath;

    /**
     * 注入  Service 集合
     */
    private List<ServiceImplEnvironment> serviceImplEnvironments;




}
