package com.okkristen.project.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.logic.sys.entity.SysModule;
import com.okkristen.project.logic.sys.entity.SysRole;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * 权限
 */
public class SysPermissionDTO extends SysPermissionMinDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private SysRoleMinDTO role;

    private SysModuleMinDTO module;





}
