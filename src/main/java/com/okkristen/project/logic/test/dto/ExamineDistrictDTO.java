package com.okkristen.project.logic.test.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 区级考核项信息DTO
 *
 * @author zpy
 * @create 2018/5/4
 */
public class ExamineDistrictDTO implements Serializable {
    private static final long serialVersionUID = -9011093791340867117L;

    private String id;

    /**
     * 河长单位
     */
    private String riverLengthUnit;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date examineTime;

    /**
     * 总分
     */
    private BigDecimal score;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private  ExamineStreetDTO examineStreet;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRiverLengthUnit() {
        return riverLengthUnit;
    }

    public void setRiverLengthUnit(String riverLengthUnit) {
        this.riverLengthUnit = riverLengthUnit;
    }

    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ExamineStreetDTO getExamineStreet() {
        return examineStreet;
    }

    public void setExamineStreet(ExamineStreetDTO examineStreet) {
        this.examineStreet = examineStreet;
    }
}
