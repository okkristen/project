package com.okkristen.project.core.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface MyRepository<E, ID extends Serializable> extends JpaRepository<E, ID>,JpaSpecificationExecutor<E> {
    /**
     * 根据Entity属性值自动构建查询
     */
    List<E> findByEntity(E example);
    /**
     * 根据Entity属性自动构建精确查询
     */
    List<E> findByEntityAccurate(E example);
    /**
     * 根据Entity属性值自动构建有排序的查询
     */
    List<E> findByEntity(E example, Sort sort);
    /**
     * 根据Entity属性值自动构建有排序的精确查询
     */
    List<E> findByEntityAccurate(E example, Sort sort);
    /**
     * 根据Entity属性值自动构建分页查询
     */
    Page<E> findByEntity(E example, Pageable pageable);
    /**
     * 根据Entity属性值自动构建分页精确查询
     */
    Page<E> findByEntityAccurate(E example, Pageable pageable);

}
