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
import java.util.List;

/**
 * 区级考核项信息
 *
 * @author zpy
 * @create 2018/5/4
 */
@Entity
@Table(name = "student")
@DynamicUpdate
@DynamicInsert
public class Student extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 9094468645786099252L;

    private  String name;

    private  Integer age;

    private  BigDecimal sex;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Teather.class)
    private Teather teather;

    @OneToMany(fetch = FetchType.LAZY,cascade ={CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "student")
    private List<Parent> parents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getSex() {
        return sex;
    }

    public void setSex(BigDecimal sex) {
        this.sex = sex;
    }

    public Teather getTeather() {
        return teather;
    }

    public void setTeather(Teather teather) {
        this.teather = teather;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }
}
