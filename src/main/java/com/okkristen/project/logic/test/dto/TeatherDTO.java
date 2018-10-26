package com.okkristen.project.logic.test.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.logic.test.entity.Student;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 区级考核项信息
 *
 * @author zpy
 * @create 2018/5/4
 */

public class TeatherDTO extends BaseDTO implements Serializable {
    private  String name;

    private  Integer age;

    private BigDecimal sex;

    private List<StudentDTO> student;

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

    public List<StudentDTO> getStudent() {
        return student;
    }

    public void setStudent(List<StudentDTO> student) {
        this.student = student;
    }
}
