package com.okkristen.project.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.logic.sys.entity.SysRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 */
@Getter
@Setter
public class SysAccountDTO extends SysAccountMinDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<SysRoleMinDTO> roleList;// 一个用户具有多个角色

}
