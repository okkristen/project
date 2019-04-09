package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务层的环境配置 (接口)
 */
@Getter
@Setter
public class ServiceEnvironment extends ProjectEnvironment {
    /**
     * 业务实现类的名字
     */
    private String serviceName;
    /**
     * 业务实现类的存放路径 -
     */
    private String servicePath;
}
