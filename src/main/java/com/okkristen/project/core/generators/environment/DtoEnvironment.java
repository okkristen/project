package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 实体类的环境
 */
@Getter
@Setter
public class DtoEnvironment extends  ProjectEnvironment {

    /**
     * DTO类的 名称
     */
    private  String dtoName;

    /**
     *  DTO类的存放路径
     */
    private String dtoPath;
    /**
     * DTO的全限定名
     */
    private String dtoAllName;
    /**
     * 属性环境
     */
    private List<ClassPropertyEnvironment> propertyEnvironments;


}
