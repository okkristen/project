package com.okkristen.project.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.logic.sys.entity.SysPermission;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 */
public class SysModuleDTO extends SysModuleMinDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;



    private List<SysPermissionMinDTO> permissionList;
}
