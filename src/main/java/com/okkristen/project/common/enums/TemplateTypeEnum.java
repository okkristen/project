package com.okkristen.project.common.enums;

/**
 *  模板类型地址
 */
public enum TemplateTypeEnum {

    Hibernate("/hibernate/"),Mybatis("/mybatis/") ;


    private String path;

    TemplateTypeEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
