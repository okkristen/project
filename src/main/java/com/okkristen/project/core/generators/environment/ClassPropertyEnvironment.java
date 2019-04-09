package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

/**
 * 类属性环境
 */
@Getter
@Setter
public class ClassPropertyEnvironment {

    /**
     * 属性名称
     */
    private String propertyName;
    /**
     * 属性类型 ---- 全限定路径名
     */
    private String proertyPath;

    /**
     * 属性权限 私有公有
     */
    private String proertyPermission;
}
