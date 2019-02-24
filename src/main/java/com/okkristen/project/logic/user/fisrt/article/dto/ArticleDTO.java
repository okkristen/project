package com.okkristen.project.logic.user.fisrt.article.dto;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.core.logic.base.dto.BaseAttachDTO;
import com.okkristen.project.core.logic.base.entity.BaseAttach;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author ysj
 * @create 2018-12-27
 **/
@Getter
@Setter
public class ArticleDTO  extends BaseDTO implements Serializable {

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
    private List<BaseAttachDTO> baseAttaches;
}
