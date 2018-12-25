package com.okkristen.project.core.shrio.MyRealm;

import com.okkristen.project.logic.sys.dto.SysAccountDTO;
import com.okkristen.project.logic.sys.entity.SysAccount;
import com.okkristen.project.logic.sys.service.SysAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class MyShrioRealm  extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(MyShrioRealm.class);
    @Autowired
    SysAccountService sysAccountService;
    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        String password = sysAccountService.findByUserName(token.getUsername()).getPassword();
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
      return new SimpleAuthenticationInfo(token.getUsername(),password, ByteSource.Util.bytes(token.getUsername()),getName());
//        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("username" + username);
        SysAccountDTO sysAccountDTO = sysAccountService.findByUserName(username);
        //获得该用户角色
        List<String> roleNames = sysAccountDTO.getRoleListName();
        Set<String> set = new HashSet<>(roleNames);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }
}
