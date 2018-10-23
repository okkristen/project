package com.okkristen.project.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 通用Service
 * 定义了通用的接口方法定义
 */
public interface CommonService<E, D> {
    /**
     * 根据id 查询 实体类
     */
    public E findById(Serializable id);
    /**
     * 根据id 查询DTO
     */
    public D findDTOById(Serializable id);

    /**
     *根据传类型  返回相对应的类型
     */
    public <T>T findDTOById(Serializable id,Class<T> tClass);
    /**
     * 根据 dto 查询 list
     */
    public List<D> findListByDTO(D dto);
    /**
     * 根据 dto 查询 list
     */
    public <T>List<T> findListByDTO(D dto,Class<T> tClass);
    /**
     * 根据 entity 查询 list
     */
    public List<E> findListByEntity(E entity);

    /**
     * 根据 dto 查询 list
     */
    public List<D> findListByDTO(D dto,Sort sort);
    /**
     * 根据 dto 查询 list
     */
    public <T>List<T> findListByDTO(D dto,Sort sort,Class<T> tClass);
    /**
     * 根据 entity 查询 list
     */
    public List<E> findListByEntity(E entity,Sort sort);

    /**
     * 分页 entity
     */
    public  Page<E> findPageByEntity(E entity, Pageable pageable);


    /**
     * 分页 DTO
     */
    public  Page<D> findPageByDTO(D dto, Pageable pageable);
    /**
     * 分页 DTO
     */
    public  <T>Page<T> findPageByDTO(D dto, Pageable pageable,Class<T> tClass);
    /**
     * 实体类转成 DTO
     */
    public D getDTO(E entity);

    /**
     * 实体类转成 目标类型
     */
    public <T>T getTargetObject(E entity,Class<T> tClass);

    /**
     * 实体类list 转 DTO list
     */
    public List<D> getDTOList(List<E> entityList);
    /**
     * 实体类list 转 DTO list
     */
    public <T>List<T> getDTOList(List<E> entityList,Class<T> tClass);

    /**
     * 实体类Page 转 DTO Page
     */
    public Page<D> getDTOPage(Page<E> entityPage);
    /**
     * 实体类Page 转 DTO Page
     */
    public <T>Page<T> getDTOPage(Page<E> entityPage,Class<T> tClass);


}

