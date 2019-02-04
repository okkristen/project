package com.okkristen.project.logic.test.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.logic.test.entity.Parent;
import com.okkristen.project.logic.test.entity.Teather;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
public class StudentDTO  extends BaseDTO implements Serializable {
    private  String name;

    private  Integer age;

    private BigDecimal sex;

    private TeatherBaseDTO teather;

    private List<ParentBaseDTO> parents;

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

    public TeatherBaseDTO getTeather() {
        return teather;
    }

    public void setTeather(TeatherBaseDTO teather) {
        this.teather = teather;
    }

    public List<ParentBaseDTO> getParents() {
        return parents;
    }

    public void setParents(List<ParentBaseDTO> parents) {
        this.parents = parents;
    }
}
