package com.okkristen.project.common.entity.velocity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author ysj
 * @create 2018-12-27
 **/
@Setter
@Getter
public class velocityProperty implements Serializable {

    /**
     * 属性名
     */
    private String propertyName;
    /**
     * 注释
     */
    private String propertyNote;
    /**
     * 私有 公开 private public
     */
    private String propertyPermission;

    /**
     *  属性类型 String int 等等
     */
    private  String propertyType;

    /**
     * 注解
     */
    private List<String> propertyAnnotation;
}
