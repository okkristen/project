package com.okkristen.project.core.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.core.logic.sys.entity.SysModule;
import com.okkristen.project.core.logic.sys.entity.SysRole;
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
public class SysPermissionMinDTO extends BaseDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 更新权限
     */
    private Boolean updatePermission;
    /**
     * 查看权限
     */
    private Boolean readPermission;
    /**
     * 导出Excel权限
     */
    private Boolean exportPermission;

}
