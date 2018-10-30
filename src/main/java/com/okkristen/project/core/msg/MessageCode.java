package com.okkristen.project.core.msg;
/**
 * ajax返回信息代码
 * @author fengw
 * @create 2016年11月9日
 */
public enum MessageCode {
	/** 请求异常*/
	//	404错误
	PAGE_NOT_FOUND("请求地址无效",101),
	/**	账户异常 */
	//	未登录
	ACCOUNT_NO_LOGIN("您还未登录或登录失效",102),
	// 手机号码格式错误或者为空
	SMS_ERROR("手机号码错误",103),
	SMS_SUCCESS("发送成功",104),

	PIN_ERROR("验证码错误",105),
	/** 小程序登录*/
	WX_CODE_ERROR("小程序Code不能为空",106),
	WX_LOGIN_ERROR("小程序登录失败",107),
	//	账户信息错误
	ACCOUNT_INFO_ERROR("账户/密码错误",108),
	//	账户锁定
	ACCOUNT_LOCKED("账户已被锁定",109),
	//	未知错误
	ACCOUNT_UNKNOWN_ERROR("未知账户错误",110),
	//  用户登出
	ACCOUNT_LOGOUT("您已注销登录",111),
	// 该用户无角色
	ACCOUNT_NO_ROLE("无可用角色，请联系管理员",112),
	
	/** 权限异常 */
	//	无操作权限
	ACCESS_DENIED("无权限执行此操作",113),
	
	/**	操作提示*/
	FORM_NOT_COMPLETE("请完善表单信息后再提交",114),
	
	OPERATE_SUCCESS("操作成功",115),
	OPERATE_FAILED("操作失败",116),
	
	SAVE_SUCCESS("保存成功",117),
	SAVE_FAILED("保存失败",118),
	
	UPDATE_SUCCESS("更新成功",119),
	UPDATE_FAILED("更新失败",120),
	
	DELETE_SUCCESS("删除成功",121),
	DELETE_FAILED("删除失败",122),
	// 上传
	UPLOAD_SUCCESS("上传成功",124),
	UPLOAD_FAILED("上传失败",125),
	/** 支付提示 */
	PAY_SUCCESS("支付成功",126),
	PAY_FAILED("支付失败",127),
	QUERY_SUCCESS("查询成功",128),
	QUERY_FAILED("查询失败",129),
	// 注册成功
	REGISTER_SUCCESS("注册成功",130);
	// 查询失败

	private String codeInfo;

	private Integer code;

	MessageCode(String codeInfo, Integer code) {
		this.codeInfo = codeInfo;
		this.code = code;
	}


	public String getCodeInfo() {
		return codeInfo;
	}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
