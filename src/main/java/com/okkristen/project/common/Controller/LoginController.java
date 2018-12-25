package com.okkristen.project.common.Controller;

/**
 * 登陆登出 控制层
 */
//@RestController
public class LoginController {
//    // 这里如果不写method参数的话，默认支持所有请求，如果想缩小请求范围，还是要添加method来支持get, post等等某个请求。
//    @PostMapping("/login")
//    public AjaxResult login(@RequestBody  JSONObject jsonObject) throws Exception {
//        Subject subject = SecurityUtils.getSubject();
//        System.out.println("登陆");
//        //数据库的密码我进行了Md5加密。如果没有进行加密的无需这个
//        //  user.setUserPassword(MD5Util.getPwd(user.getUserPassword()));
//        UsernamePasswordToken token = new UsernamePasswordToken("admin","123456");
//        token.setRememberMe(true);
//        try {
//            subject.login(token);
//            return AjaxResult.createSuccessResultWithCode(MessageCode.UPDATE_SUCCESS);
//        } catch (UnknownAccountException e){
//            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED);
//        } catch (IncorrectCredentialsException e){
//            e.printStackTrace();
//            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED);
//        } catch (LockedAccountException e){
//            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED);
//        }catch (DisabledAccountException e){
//            return  AjaxResult.createErrorResult(MessageCode.QUERY_FAILED);
//        } catch (Exception e){
//            e.printStackTrace();
//            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED);
//        }
//    }
}
