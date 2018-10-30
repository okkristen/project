package com.okkristen.project.core.msg;

/**
 * @author ysj
 * @create 2018-10-30
 **/
public enum  ErrCode {
    FAIL("失败",101);

    private String errCodeMessage;

    private Integer errCode;

    ErrCode(String errCodeMessage, Integer errCode) {
        this.errCodeMessage = errCodeMessage;
        this.errCode = errCode;
    }

    public String getErrCodeMessage() {
        return errCodeMessage;
    }

    public void setErrCodeMessage(String errCodeMessage) {
        this.errCodeMessage = errCodeMessage;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}
