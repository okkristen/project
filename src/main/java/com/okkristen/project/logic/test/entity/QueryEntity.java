package com.okkristen.project.logic.test.entity;

import com.okkristen.project.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ysj
 * @create 2018-10-29
 **/
@Entity
@Table(name = "queryEntity")
@DynamicUpdate
@DynamicInsert
public class QueryEntity extends BaseEntity implements Serializable {

    private String name;

    private Integer age;

    private BigDecimal money;

    private Date happyTime;
    @Column(name = "num1", columnDefinition="float(10,2) default '0.00'")
    private Float num1;

    private Boolean bool;

    private Long longId;
    @Column(name = "aDouble", columnDefinition="double(10,2) default '0.00'")
    private Double aDouble;

    private Byte aByte;

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
}
