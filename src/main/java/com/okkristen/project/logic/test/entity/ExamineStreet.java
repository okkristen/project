package com.okkristen.project.logic.test.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.okkristen.project.common.entity.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 街道考核项信息
 *
 * @author zpy
 * @create 2018/5/4
 */
@Entity
@Table(name = "examine_street")
@DynamicUpdate
@DynamicInsert
public class ExamineStreet extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 9094468645786099258L;



    /**
     * 河长单位
     */
    @Column(columnDefinition = "varchar(255) comment '河长单位'")
    private String riverLengthUnit;

    /**
     * 考核时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss,SSS")
    private Date examineTime;
    /**
     * 具体考核项考核分数
     */
    @OneToMany(fetch = FetchType.LAZY,cascade ={CascadeType.ALL},mappedBy = "examineStreet")
    private List<ExamineGrade> examineGradeList;

    /**
     * 总分
     */
    @Column(columnDefinition = "decimal(10,7) comment '总分'")
    @NotNull(message = "总分不能为空")
    private int score;

    /**
     * 考核状态
     * 0未考核/1已考核
     */
    @Column(columnDefinition = "bit(1) default false comment '考核状态'")
    private Integer examineState;

    @Transient
    private Date startExamineTime;

    @Transient
    private Date endExamineTime;


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

    public List<ExamineGrade> getExamineGradeList() {
        return examineGradeList;
    }

    public void setExamineGradeList(List<ExamineGrade> examineGradeList) {
        this.examineGradeList = examineGradeList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getExamineState() {
        return examineState;
    }

    public void setExamineState(Integer examineState) {
        this.examineState = examineState;
    }

    public Date getStartExamineTime() {
        return startExamineTime;
    }

    public void setStartExamineTime(Date startExamineTime) {
        this.startExamineTime = startExamineTime;
    }

    public Date getEndExamineTime() {
        return endExamineTime;
    }

    public void setEndExamineTime(Date endExamineTime) {
        this.endExamineTime = endExamineTime;
    }
}
