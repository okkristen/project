package com.okkristen.project.logic.user.fisrt.article.entity;

import com.okkristen.project.common.entity.BaseEntity;
import com.okkristen.project.core.logic.base.entity.BaseAttach;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author ysj
 * @create 2018-12-27
 **/
@Entity
@Table(name = "fisrt_article")
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class Article extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private  String author;


    /**
     * 文章内容
     */
    private String conent;

    /**
     * 标签
     */

    /**
     * 文章下的留言 --- 集合
     */

    /**
     * 文章的照片
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fisrt_article_attach", joinColumns = {@JoinColumn(name = "fisrt_article_id")}, inverseJoinColumns = {@JoinColumn(name = "attach_id")})
    private List<BaseAttach> baseAttaches;

}
