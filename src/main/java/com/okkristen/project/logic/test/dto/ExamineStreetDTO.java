package com.okkristen.project.logic.test.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 街道考核项信息DTO
 *
 * @author zpy
 * @create 2018/5/4
 */
public class ExamineStreetDTO implements Serializable {

    private static final long serialVersionUID = 8863755392609683285L;

    private String id;
    /**
     * 具体考核项考核分数
     */
    private List<ExamineGradeDTO> examineGradeList;
    /**
     * 河长单位
     */
    private String riverLengthUnit;

    /**
     * 考核时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date examineTime;

    /**
     * 总分
     */
    private int score;

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

    /**
     * 考核状态
     * 0未考核/1已考核
     */
    private Integer examineState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }





    public List<ExamineGradeDTO> getExamineGradeList() {
        return examineGradeList;
    }

    public void setExamineGradeList(List<ExamineGradeDTO> examineGradeList) {
        this.examineGradeList = examineGradeList;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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

    public Integer getExamineState() {
        return examineState;
    }

    public void setExamineState(Integer examineState) {
        this.examineState = examineState;
    }
}
