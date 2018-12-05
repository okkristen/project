package com.okkristen.project.core.shrio.MyRealm;

import com.okkristen.project.logic.sys.service.SysAccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class MyShrioRealm  extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(MyShrioRealm.class);

    @Autowired
    private SysAccountService sysAccountService;





    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        Object userName = principalCollection.getPrimaryPrincipal();
        System.out.println("登陸用戶名" + userName);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

//        添加角色 步骤
        //添加角色
//        simpleAuthorizationInfo.addRole("admin");
//       添加 权限步骤
        //添加权限
//        simpleAuthorizationInfo.addStringPermission("create");

        return simpleAuthorizationInfo;
    }



    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
//        SysUser user = userService.selectByAccount(username);
//        if(null == user){
//            throw new UnknownAccountException();
//        }else {
//            if(password.equals(user.getUserPassword())){
//                if(0 == user.getUserState()){
//                    throw new LockedAccountException();
//                }else if (2 == user.getUserState()){
//                    throw new DisabledAccountException();
//                }else{
//                    SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(user,user.getUserPassword().toCharArray(),getName());
//                    return authorizationInfo;
//                }
//            } else {
//                throw new IncorrectCredentialsException();
//            }
//        }
        return  new SimpleAuthenticationInfo();
    }
}
