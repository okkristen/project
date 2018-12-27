package com.okkristen.project.core.logic.sys.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.core.logic.sys.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 */
@Getter
@Setter
public class SysModuleMinDTO extends BaseDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 模块排序
     */
    private Integer moduleIndex;
    /**
     * 模块别名
     * （资源名）
     */
    private String moduleAlias;
    /**
     * 模块层级
     */
    private Integer moduleLevel;

}