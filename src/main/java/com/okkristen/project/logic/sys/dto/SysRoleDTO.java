package com.okkristen.project.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.logic.sys.entity.SysAccount;
import com.okkristen.project.logic.sys.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 */
@Getter
@Setter
public class SysRoleDTO extends SysRoleMinDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<SysPermissionMinDTO> permissions;

    // 用户 - 角色关系定义;
    private List<SysAccountMinDTO> sysAccounts;// 一个角色对应多个用户

}
