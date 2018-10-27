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
@Table(name = "parent")
@DynamicUpdate
@DynamicInsert
public class Parent extends BaseEntity implements Serializable {
    private  String name;

    private  Integer age;

    private BigDecimal sex;
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Student.class)
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
