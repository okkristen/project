package com.okkristen.project.logic.user.fisrt.tag.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author ysj
 * @create 2018-12-27
 **/
public class TagDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    /**
     * 标签名
     */
    private String tagName;
}
