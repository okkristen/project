package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

/**
 * 整个环境的环境
 */
@Getter
@Setter
public class ProjectEnvironment {

    /**
     * 顶级用户自定义路径
     */
    private String projectComPtah = "src\\\\main\\\\java";
    /**
     * 顶级用户自定义路径
     */
    private String projectPath = "com.okkristen.project";
    /**
     * 创建人姓名
     */
    private String createName = "hmily";

    /**
     * 程序请求路径前缀
     */
    private String prefix = "/api";

}
