package com.okkristen.project.common.entity.velocity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author ysj
 * @create 2018-12-27
 **/
@Setter
@Getter
public class velocityDto implements Serializable {
    /**
     * 程序名
     */
    private String projectName;

    /**
     * 顶级包名就是相当于首字母小写类名
     */
    private String packageName;

    /**
     * 这个类的类名
     */
    private  String className;
    /**
     * 自定义路径
     */
    private String customPath;

    /**
     * 自定义URL
     */
    private String customURL;
    /**
     * DTO下面的属性值
     */
    private List<velocityProperty> properties;
}
