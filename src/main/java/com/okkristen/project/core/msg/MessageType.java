package com.okkristen.project.core.msg;
/**
 * ajax信息类型
 * @author fengw
 * @create 2016年11月9日
 */
public enum MessageType {
	//	请求失败
	ERROR("ERROR", 9999),
	//	请求成功
	SUCCESS("SUCCESS", 10000);

	private String type;

	private Integer typeCode;

	MessageType(String type, Integer typeCode) {
		this.type = type;
		this.typeCode = typeCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.type.toString();
	}
}
