package com.okkristen.project.core.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.core.logic.sys.entity.SysAccount;
import com.okkristen.project.core.logic.sys.entity.SysPermission;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户角色
 */
@Getter
@Setter
public class SysRoleMinDTO extends BaseDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:

    private String description; // 角色描述,UI界面显示使用

    private Boolean available; // 是否可用,如果不可用将不会添加给用户


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
