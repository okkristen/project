package com.okkristen.project.core.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.core.logic.sys.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 */
@Getter
@Setter
public class SysModuleDTO extends SysModuleMinDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<SysPermissionMinDTO> permissionList;
}
