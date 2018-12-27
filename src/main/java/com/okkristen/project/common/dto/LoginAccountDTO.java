package com.okkristen.project.common.dto;

import com.okkristen.project.core.logic.sys.dto.SysRoleDTO;
import com.okkristen.project.core.logic.sys.entity.SysRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ysj
 * @create 2018-12-27
 **/
@Getter
@Setter
public class LoginAccountDTO extends  BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;// 帐号

    private String name;// 名称（昵称或者真实姓名，不同系统不同定义）

    private String password; // 密码;

    private String salt;// 加密密码的盐

    private Integer state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,

    // 1:正常状态,2：用户被锁定.
    private String city;



    private List<SysRoleDTO> roleList;

    /**
     * 获取所有的角色信息
     * @return
     */
    private  List<String> getSysRoleName () {
        List<String> list = new ArrayList<>();
        if (this.roleList == null || this.roleList.isEmpty()) {
            return list;
        }
        // TODO 以后换成lambda 表达式 现在接触不深
        for (SysRoleDTO sysRole: this.roleList) {
            list.add(sysRole.getDescription());
        }
        return  list;
    }
    // 获取所有权限

}
