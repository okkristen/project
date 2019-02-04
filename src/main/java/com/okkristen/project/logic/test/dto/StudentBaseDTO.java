package com.okkristen.project.logic.test.dto;

import com.okkristen.project.common.dto.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 区级考核项信息
 *
 * @author zpy
 * @create 2018/5/4
 */
public class StudentBaseDTO extends BaseDTO implements Serializable {
    private  String name;

    private  Integer age;

    private BigDecimal sex;


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

}
