package com.okkristen.project.logic.user.fisrt.tag.entity;

import com.okkristen.project.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author ysj
 * @create 2018-12-27
 **/
@Entity
@Table(name = "fisrt_tag")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Tag extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 标签名
     */
    private  String tagName;

}
