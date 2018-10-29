package com.okkristen.project.logic.test.entity;

import com.okkristen.project.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 区级考核项信息
 *
 * @author zpy
 * @create 2018/5/4
 */
@Entity
@Table(name = "teather")
@DynamicUpdate
@DynamicInsert
public class Teather extends BaseEntity implements Serializable {
    private  String name;

    private  Integer age;

    private BigDecimal sex;

    @OneToMany(fetch = FetchType.LAZY,cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},orphanRemoval =  true,mappedBy = "teather")
    private List<Student> student;

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

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
}
