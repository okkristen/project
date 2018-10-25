package com.okkristen.project.logic.test.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 考核项信息DTO
 *
 * @author zpy
 * @create 2018/5/4
 */
public class ExamineItemBaseDTO implements Serializable {

    private static final long serialVersionUID = 7405468907939282397L;

    private String id;

    /**
     * 类别（区级河长考核/街道河长考核）
     * 0区级河长考核 1街道河长考核
     */
    private Integer examineType;

    /**
     * 分组名（附加分...)
     */
    private String groupName;

    /**
     * 评分项（断面水质...)
     */
    private String gradeNape;

    /**
     * 可选分数（0,5,15）
     */
    private String grade;

    /**
     * 说明
     */
    private String illustrate;

    /**
     * 排序
     */
    private String sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除
     */
    private Boolean deleteFlag;

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

    /**
     * 启用
     * 0:否1:是
     */
    private Boolean enable;

    private  Boolean isRequire;

    private ExamineItemBaseDTO examineItemDTO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGradeNape() {
        return gradeNape;
    }

    public void setGradeNape(String gradeNape) {
        this.gradeNape = gradeNape;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getIsRequire() {
        return isRequire;
    }

    public void setIsRequire(Boolean require) {
        isRequire = require;
    }
}
