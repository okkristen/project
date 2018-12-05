package com.okkristen.project.logic.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.okkristen.project.common.entity.BaseEntity;
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
@Entity
public class SysModule extends BaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模块名称
     */
    @NotEmpty(message = "模块名称不能为空")
    private String moduleName;
    /**
     * 模块排序
     */
    @NotNull
    private Integer moduleIndex;
    /**
     * 模块别名
     * （资源名）
     */
    @NotNull
    @Column(unique = true)
    private String moduleAlias;
    /**
     * 模块层级
     */
    private Integer moduleLevel;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "module", cascade = CascadeType.REMOVE)
    @JSONField(serialize = false)
    private List<SysPermission> permissionList;
}
