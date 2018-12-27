package com.okkristen.project.common.Controller;

import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.common.dto.LoginAccountDTO;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.msg.MessageCode;
import com.okkristen.project.core.logic.sys.dto.SysAccountDTO;
import com.okkristen.project.core.logic.sys.service.SysAccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登陆登出 控制层
 */
@RestController
public class LoginController {
    @Autowired
    SysAccountService sysAccountService;

    @PostMapping("/nologin")
    public AjaxResult noLogin() {
        return AjaxResult.createErrorResult(MessageCode.ACCOUNT_NO_LOGIN);
    }
    @PostMapping("/notRole")
    public AjaxResult notRole() {
        return AjaxResult.createErrorResult(MessageCode.ACCOUNT_NO_ROLE);
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Boolean rembermer = Boolean.TRUE;
        if (jsonObject.containsKey("rembermer")) {
            rembermer = jsonObject.getBoolean("rembermer");
        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(rembermer);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        LoginAccountDTO sysAccountDTO = sysAccountService.findByPasswordAndUserName(password,username);
//        List<String> list =  sysAccountDTO.getRoleListName();
//        if (list.contains("user")) {
//            return AjaxResult.createSuccessResult(sysAccountDTO);
//        }
//        if (list.contains("admin")) {
//            return AjaxResult.createSuccessResult(sysAccountDTO);
//        }
        return AjaxResult.createSuccessResult("权限错误");
    }
}
