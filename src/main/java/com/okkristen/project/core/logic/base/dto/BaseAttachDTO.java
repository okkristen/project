package com.okkristen.project.core.logic.base.dto;

import com.okkristen.project.common.dto.BaseDTO;

import java.io.Serializable;

/**
 * @author ysj
 * @create 2018-12-27
 **/
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
}
