package com.okkristen.project.common.shrio.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.msg.MessageCode;
import com.okkristen.project.logic.sys.dto.SysAccountDTO;
import com.okkristen.project.logic.sys.entity.SysAccount;
import com.okkristen.project.logic.sys.service.SysAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ysj
 * @create 2018-12-25
 **/
@RestController
public class LoginController {
    @Autowired
    SysAccountService sysAccountService;

    @RequestMapping(value = "/guest/enter", method = RequestMethod.GET)
    public AjaxResult login() {
        return AjaxResult.createSuccessResult("您的权限是游客");
    }
    @RequestMapping(value = "/guest/getMessage", method = RequestMethod.GET)
    public AjaxResult submitLogin() {
        return AjaxResult.createSuccessResult("您获得该接口的信息");
    }
    @RequestMapping(value = "/user/getMessage", method = RequestMethod.GET)
    public AjaxResult usergetMessage() {
        return AjaxResult.createSuccessResult("您的权限是用户");
    }
    @RequestMapping(value = "/admin/getMessage", method = RequestMethod.GET)
    public AjaxResult admingetMessage() {
        return AjaxResult.createSuccessResult("您的权限是管理");
    }
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public AjaxResult notLogin() {
        return AjaxResult.createSuccessResult("您没未登陆");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public AjaxResult notRole() {
        return AjaxResult.createSuccessResult("您没权限");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public AjaxResult logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return AjaxResult.createSuccessResult("注销");
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AjaxResult login(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password =  jsonObject.getString("password");
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        SysAccountDTO sysAccountDTO = sysAccountService.findByPasswordAndUserName(password,username);
      List<String> list =  sysAccountDTO.getRoleListName();
        if (list.contains("user")) {
            return AjaxResult.createSuccessResult("用户您好");
        }
        if (list.contains("admin")) {
            return AjaxResult.createSuccessResult("管理员您好");
        }
        return AjaxResult.createSuccessResult("权限错误");
    }
}
