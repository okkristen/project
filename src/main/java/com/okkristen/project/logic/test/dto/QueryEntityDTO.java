package com.okkristen.project.logic.test.dto;

import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.logic.test.entity.Parent;
import com.okkristen.project.logic.test.entity.Student;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ysj
 * @create 2018-10-29
 **/
public class QueryEntityDTO extends BaseEntity implements Serializable {

    private String name;

    private Integer age;

    private BigDecimal money;

    private Date happyTime;
    
    private Float num1;

    private Boolean bool;

    private Long longId;

    private Double aDouble;

    private Byte aByte;

    private StudentDTO student;

    private List<ParentDTO> parentList;

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getHappyTime() {
        return happyTime;
    }

    public void setHappyTime(Date happyTime) {
        this.happyTime = happyTime;
    }

    public Float getNum1() {
        return num1;
    }

    public void setNum1(Float num1) {
        this.num1 = num1;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public List<ParentDTO> getParentList() {
        return parentList;
    }

    public void setParentList(List<ParentDTO> parentList) {
        this.parentList = parentList;
    }
}
