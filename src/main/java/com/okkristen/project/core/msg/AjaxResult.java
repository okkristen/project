package com.okkristen.project.core.msg;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Ajax返回数据模板
 * @author fengw
 * @create 2016年11月10日
 */
public class AjaxResult implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1911108045233238778L;
	/**
	 * 数据类型
	 */
	@JSONField(serialize= false)
	private MessageType type;
	/**
	 *类型编码
	 */
	private Integer typeCode;

	/**
	 * 信息代码
	 */
	private MessageCode code;
	/**
	 * 数据内容
	 */
	private Object data;
	/**
	 * 附加消息
	 */
	private String message;

	/**
	 * 错误编码
	 */
	private  Integer errCode;


	/**
	 * 
	 */
	private AjaxResult() {
		super();
	}
	/**
	 * @param type
	 * @param code
	 * @param data
	 * @param message
	 */
	private AjaxResult(MessageType type, MessageCode code, Object data, String message) {
		super();
		this.type = type;
		this.code = code;
		this.data = data;
		this.message = message;
		if (code != null) {
			this.errCode = code.getCode();
		}
		this.typeCode = type.getTypeCode();
	}
	/**
	 * 创建请求成功的返回值
	 * @param code
	 * @param data
	 * @param message
	 * @return
	 * @author fengw
	 * @create 2016年11月10日
	 */
	public static AjaxResult createSuccessResult(Object data, String message) {
		return new AjaxResult(MessageType.SUCCESS, null, data, message);
	}
	public static AjaxResult createSuccessResult(Object data) {
		return new AjaxResult(MessageType.SUCCESS, null, data, null);
	}
	public static AjaxResult createSuccessResultWithCode(MessageCode code) {
		return new AjaxResult(MessageType.SUCCESS, code, null, code.getCodeInfo());
	}
	public static AjaxResult createSuccessResultWithCode(MessageCode code, String message) {
		if (message == null) {
			message = code.getCodeInfo();
		}
		return new AjaxResult(MessageType.SUCCESS, code, null, message);
	}
	public static AjaxResult createSuccessResultWithCode(MessageCode code, Object data) {
		return new AjaxResult(MessageType.SUCCESS, code, data, code.getCodeInfo());
	}
	public static AjaxResult createSuccessResultWithCode(MessageCode code, Object data, String message) {
		if (message == null) {
			message = code.getCodeInfo();
		}
		return new AjaxResult(MessageType.SUCCESS, code, data, message);
	}
	/**
	 * 创建请求失败对象
	 * @param code
	 * @param message
	 * @return
	 * @author fengw
	 * @create 2016年11月16日
	 */
	public static AjaxResult createErrorResult(MessageCode code, String message) {
		if (message == null) {
			message = code.getCodeInfo();
		}
		return new AjaxResult(MessageType.ERROR, code, null, message);
	}
	public static AjaxResult createErrorResult(MessageCode code) {
		return new AjaxResult(MessageType.ERROR, code, null, code.getCodeInfo());
	}
	public static AjaxResult createErrorResult(MessageCode code,Exception e) {
		return new AjaxResult(MessageType.ERROR, code, null, code.getCodeInfo());
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public MessageCode getCode() {
		return code;
	}
	public void setCode(MessageCode code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	public Integer geterrCode() {
		return errCode;
	}

	public void seterrCode(Integer errCode) {
		this.errCode = errCode;
	}
}
