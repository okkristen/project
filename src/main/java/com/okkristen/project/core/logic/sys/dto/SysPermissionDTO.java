package com.okkristen.project.core.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.core.logic.sys.entity.SysModule;
import com.okkristen.project.core.logic.sys.entity.SysRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * 权限
 */
@Getter
@Setter
public class SysPermissionDTO extends SysPermissionMinDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private SysRoleMinDTO role;

    private SysModuleMinDTO module;





}
