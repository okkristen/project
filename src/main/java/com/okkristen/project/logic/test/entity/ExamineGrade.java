package com.okkristen.project.logic.test.entity;

import com.okkristen.project.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 考核表信息
 *
 * @author zpy
 * @create 2018/5/4
 */
@Entity
@Table(name = "examine_grade")
@DynamicUpdate
@DynamicInsert
public class ExamineGrade extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1921890509533309137L;

    /**
     * 考核id
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ExamineItem.class)
    @JoinColumn(name = "examine_id")
    @NotNull(message = "考核id不能为空")
    private ExamineItem examineItem;

    /**
     * 街道河长考核主表id
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ExamineStreet.class)
    @JoinColumn(name = "examine_street_id")
    private ExamineStreet examineStreet;

    /**
     * 区级河长考核主表id
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ExamineDistrict.class)
    @JoinColumn(name = "examine_district_id")
    private ExamineDistrict examineDistrict;

    /**
     * 类别（区级河长考核/街道河长考核）
     * 0区级河长考核 1街道河长考核
     */
    @Column(columnDefinition = "int(1) comment '类别（0区级河长考核/1街道河长考核）'")
    private Integer examineType;

    /**
     * 考核分数
     */
    @Column(columnDefinition = "varchar(10) comment '考核分数'")
    @NotEmpty(message = "考核分数不能为空")
    private String examineGrade;



    /**
     * 理由
     */
    @Column(columnDefinition = "varchar(500) comment '理由'")
    private String reason;

    /**
     * 启用
     * 0:否1:是
     */
    @Column(columnDefinition = "bit(1) default true comment '是否启用'")
    private Boolean enable;


    public ExamineItem getExamineItem() {
        return examineItem;
    }

    public void setExamineItem(ExamineItem examineItem) {
        this.examineItem = examineItem;
    }

    public ExamineStreet getExamineStreet() {
        return examineStreet;
    }

    public void setExamineStreet(ExamineStreet examineStreet) {
        this.examineStreet = examineStreet;
    }

    public ExamineDistrict getExamineDistrict() {
        return examineDistrict;
    }

    public void setExamineDistrict(ExamineDistrict examineDistrict) {
        this.examineDistrict = examineDistrict;
    }

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public String getExamineGrade() {
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
}
