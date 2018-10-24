package com.okkristen.project.logic.test.entity;

import com.okkristen.project.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 考核项信息
 *
 * @author zpy
 * @create 2018/5/4
 */
@Entity
@Table(name = "examine_item_base")
@DynamicUpdate
@DynamicInsert
public class ExamineItemBase extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2924488819526314878L;

    /**
     * 类别（区级河长考核/街道河长考核）
     * 0区级河长考核 1街道河长考核
     */
    @Column(columnDefinition = "int(1) comment '类别（0区级河长考核/1街道河长考核）'")
    private Integer examineType;

    /**
     * 分组名（附加分...)
     */
    @Column(columnDefinition = "varchar(255) comment '分组名（附加分...)'")
    @NotEmpty(message = "分组名不能为空")
    private String groupName;

    /**
     * 评分项（断面水质...)
     */
    @Column(columnDefinition = "varchar(255) comment '评分项（断面水质...)'")
    @NotEmpty(message = "评分项不能为空")
    private String gradeNape;

    /**
     * 可选分数（0,5,15）
     */
    @Column(columnDefinition = "varchar(500) comment '可选分数（0,5,15）'")
    @NotEmpty(message = "可选分数不能为空")
    private String grade;

    /**
     * 说明
     */
    @Column(columnDefinition = "varchar(500) comment '说明'")
    private String illustrate;

    /**
     * 排序
     */
    @Column(columnDefinition = "varchar(10) comment '排序'")
    private String sort;
    /**
     * 是否必填
     * 0:否1:是
     */
    private  Boolean isRequire;
    /**
     * 启用
     * 0:否1:是
     */
    @Column(columnDefinition = "bit(1) default true comment '是否启用'")
    private Boolean enable;

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
