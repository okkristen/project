package com.okkristen.project.logic.sys.entity;

import com.okkristen.project.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 */
@Getter
@Setter
@Entity
public class SysRole extends BaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available; // 是否可用,如果不可用将不会添加给用户

    // 角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
            @JoinColumn(name = "permission_id") })
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name = "sys_account_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
            @JoinColumn(name = "account_id") })
    private List<SysAccount> sysAccounts;// 一个角色对应多个用户

}
