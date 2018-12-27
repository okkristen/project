package com.okkristen.project.core.logic.base.entity;

import com.okkristen.project.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 基础附件
 * @author ysj
 * @create 2018-12-27
 **/
@Entity
@Table(name = "base_attach")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class BaseAttach extends BaseEntity implements Serializable{

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
