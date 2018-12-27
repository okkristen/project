package com.okkristen.project.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.Persistent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hmily
 * @create 2018-04-25
 **/
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "varchar(32) comment 'ID'")
    private String id;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Transient
    private Date startCreateTime;
    @Transient
    private Date endCreateTime;
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JSONField(format = "yyyy-MM-dd HH:mm:ss,SSS")
    @Column(columnDefinition = "TIMESTAMP comment '修改时间'")
    private Date updateTime;

    /**
     * 是否删除
     */
    @Column(columnDefinition = "bit(1) default false comment '是否删除'")
    private Boolean deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
