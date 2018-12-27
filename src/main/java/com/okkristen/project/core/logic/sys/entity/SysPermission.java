package com.okkristen.project.core.logic.sys.entity;

import com.okkristen.project.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 权限
 */
@Getter
@Setter
@Entity
public class SysPermission extends BaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 更新权限
     */
    @Column(columnDefinition = "bit(1) default false comment '更新权限'")
    private Boolean updatePermission;
    /**
     * 查看权限
     */
    @Column(columnDefinition = "bit(1) default true comment '查看权限'")
    private Boolean readPermission;
    /**
     * 导出Excel权限
     */
    @Column(columnDefinition = "bit(1) default false comment '导出权限'")
    private Boolean exportPermission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @NotNull
    private SysRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    @NotNull
    private SysModule module;

}
