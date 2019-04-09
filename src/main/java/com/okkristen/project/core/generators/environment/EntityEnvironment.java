package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 实体类的环境
 */
@Getter
@Setter
public class EntityEnvironment extends  ProjectEnvironment {

    /**
     * 实体类的 名称
     */
    private  String entityName;

    /**
     * 类的全限定名
     */
    private String entityAllName;

    /**
     * 实体类的存放路径
     */
    private String entityPath;

    /**
     * 属性环境
     */
    private List<ClassPropertyEnvironment> propertyEnvironments;
}
