package com.okkristen.project.core.generators.environment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DaoEnvironment extends ProjectEnvironment {

    /**
     * dao名称
     */
    private String daoName;

    /**
     * dao 路径
     */
    private String daoPath;

}
