package com.okkristen.project.logic.test.entity;

import com.okkristen.project.common.entity.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 区级考核项信息
 *
 * @author zpy
 * @create 2018/5/4
 */
@Entity
@Table(name = "examine_district")
@DynamicUpdate
@DynamicInsert
public class ExamineDistrict<JSONField> extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -691139571673316144L;
    /**
     * 河道（断面）
     */

    /**
     * 河长名称
     */
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BaseAccount.class)
//    @JoinColumn(name = "account_id")
//    @NotNull(message = "河长名称不能为空")
//    private BaseAccount baseAccount;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ExamineStreet.class)
    @JoinColumn(name = "account_id")
    @NotNull(message = "河长名称不能为空")
    private ExamineStreet examineStreet;

    /**
     * 河长单位
     */
    @Column(columnDefinition = "varchar(255) comment '河长单位'")
    @NotEmpty(message = "河长单位不能为空")
    private String riverLengthUnit;

    /**
     * 考核时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private Date examineTime;

    /**
     * 总分
     */
    @Column(columnDefinition = "decimal(10,7) comment '总分'")
    @NotNull(message = "总分不能为空")
    private BigDecimal score;


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

    public ExamineStreet getExamineStreet() {
        return examineStreet;
    }

    public void setExamineStreet(ExamineStreet examineStreet) {
        this.examineStreet = examineStreet;
    }
}
