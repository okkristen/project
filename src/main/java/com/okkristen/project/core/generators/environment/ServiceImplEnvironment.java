package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 业务层的环境配置 (实现)
 */
@Getter
@Setter
public class ServiceImplEnvironment extends ProjectEnvironment {


    /**
     * 业务实现类的名字
     */
    private String serviceImplName;
    /**
     * 业务实现类的存放路径 -
     */
    private String serviceImplPath;

    /**
     * 实现业务接口
     */
    private ServiceEnvironment serviceEnvironments;
    /**
     * dao 注入 集合
     */
    private List<DaoEnvironment> daoEnvironments;
}
