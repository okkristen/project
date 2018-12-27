package com.okkristen.project.logic.other.database.dto;

import com.okkristen.project.common.dto.BaseDTO;

import java.io.Serializable;

/**
 * 字段表
 */
public class DatabaseFiledDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 字段名
     */
    private  String filedName;
    /**
     * 字段类型
     */
    private String fieldType;

    /**
     * 字段长度
     */
    private String fieldLength;

    /**
     * 字段描述
     */
    private String filedExplain;

    /**
     * table_id 表的关联id
     */
    private  Boolean filedIsNull;
    /**
     * 是否主键
     */
    private String filedIsKey;
    /**
     * 字段默认值
     */
    private String filedDefault;


    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getFiledExplain() {
        return filedExplain;
    }

    public void setFiledExplain(String filedExplain) {
        this.filedExplain = filedExplain;
    }

    public Boolean getFiledIsNull() {
        return filedIsNull;
    }

    public void setFiledIsNull(Boolean filedIsNull) {
        this.filedIsNull = filedIsNull;
    }

    public String getFiledIsKey() {
        return filedIsKey;
    }

    public void setFiledIsKey(String filedIsKey) {
        this.filedIsKey = filedIsKey;
    }

    public String getFiledDefault() {
        return filedDefault;
    }

    public void setFiledDefault(String filedDefault) {
        this.filedDefault = filedDefault;
    }
}
