package com.okkristen.project.core.logic.base.dto;

import com.okkristen.project.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ysj
 * @create 2018-12-27
 **/
@Getter
@Setter
public class BaseAttachDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String attachName;
    /**
     * 附件路径
     */
    private String attachPath;
    /**
     * 附件模块
     */
    private String attachModule;
    /**
     * 文件大小
     */
    private Long attachSize;

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getAttachModule() {
        return attachModule;
    }

    public void setAttachModule(String attachModule) {
        this.attachModule = attachModule;
    }

    public Long getAttachSize() {
        return attachSize;
    }

    public void setAttachSize(Long attachSize) {
        this.attachSize = attachSize;
    }
}
