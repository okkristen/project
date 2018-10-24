package com.okkristen.project.logic.test.dto;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 考核表信息DTO
 *
 * @author zpy
 * @create 2018/5/4
 */
public class ExamineGradeDTO implements Serializable {

    private static final long serialVersionUID = -593182536225239998L;

    private String id;

    /**
     * 考核id
     */
    private ExamineItemDTO examineItem;

    /**
     * 街道河长id
     */
    private ExamineStreetDTO examineStreet;

    /**
     * 区级河长id
     */
    private ExamineDistrictDTO examineDistrict;

    /**
     * 类别（区级河长考核/街道河长考核）
     * 0区级河长考核 1街道河长考核
     */
    private Integer examineType;

    /**
     * 考核分数
     */
    private String examineGrade;

    /**
     * 理由
     */
    private String reason;

    /**
     * 启用
     * 0:否1:是
     */
    private Boolean enable;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExamineItemDTO getExamineItem() {
        return examineItem;
    }

    public void setExamineItem(ExamineItemDTO examineItem) {
        this.examineItem = examineItem;
    }

    public ExamineStreetDTO getExamineStreet() {
        return examineStreet;
    }

    public void setExamineStreet(ExamineStreetDTO examineStreet) {
        this.examineStreet = examineStreet;
    }

    public ExamineDistrictDTO getExamineDistrict() {
        return examineDistrict;
    }

    public void setExamineDistrict(ExamineDistrictDTO examineDistrict) {
        this.examineDistrict = examineDistrict;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public String getExamineGrade() {
        if(StringUtils.isEmpty(examineGrade)) {
            this.examineGrade = "0";
        }
        return examineGrade;
    }

    public void setExamineGrade(String examineGrade) {
        this.examineGrade = examineGrade;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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

}
