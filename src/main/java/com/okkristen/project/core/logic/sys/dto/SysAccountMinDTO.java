package com.okkristen.project.core.logic.sys.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.core.logic.sys.entity.SysRole;
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
public class SysAccountMinDTO extends BaseDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    private String username;// 帐号

    private String name;// 名称（昵称或者真实姓名，不同系统不同定义）

    private String password; // 密码;
    private String salt;// 加密密码的盐

    private Integer state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
    // 1:正常状态,2：用户被锁定.
    private String city;

    
}