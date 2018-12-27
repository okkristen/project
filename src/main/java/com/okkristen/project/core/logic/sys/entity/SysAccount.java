package com.okkristen.project.core.logic.sys.entity;

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
public class SysAccount extends BaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Column(unique = true)
    private String username;// 帐号

    private String name;// 名称（昵称或者真实姓名，不同系统不同定义）

    private String password; // 密码;
    private String salt;// 加密密码的盐
    
    private String city;
    private byte state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
    // 1:正常状态,2：用户被锁定.

    @ManyToMany(fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
    @JoinTable(name = "sys_account_role", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private List<SysRole> roleList;// 一个用户具有多个角色




}
