package com.okkristen.project.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * 通用Service
 * 定义了通用的接口方法定义
 */
@Transactional
public interface CommonService<E, D> {
    /**
     * 根据id 查询 实体类
     */
    public E findById(String id);
    /**
     * 根据id 查询DTO
     */
    public D findDTOById(String id);

    /**
     *根据传类型  返回相对应的类型
     */
    public <T>T findDTOById(String id,Class<T> tClass);

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
    public <T>T getTargetDTO(E entity,Class<T> tClass);
    /**
     * DTO类转成 实体类类型
     */
    public E getSourceEntity(D dto);

    /**
     *  DTO list 转成实体类 L
     */
    public  List<E> getSourceEntityList(List<D> dtoList);
    /**
     *传入任意与实体类相关联的DTO  获取 原始的实体类
     * @return
     */
    public <T>E getoriginalEntity(T tDTO);
    /**
     *  DTO list 转成实体类 L
     */
    public  <T>List<E> getoriginalEntityList(List<T> dtoList);
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

    /******************************更新修改 删除数据****************************************/
    /**
     * 添加 数据 entity
     */
    public E saveByEntity(E entity);
    /**
     * 添加 数据 entity
     */
    public D saveByDTO(D dto);
    /**
     * 添加 entity list
     */
    public List<E> saveByEntityList(List<E> entityList);
    /**
     * 添加 DTO list
     */
    public List<D> saveByDTOList(List<D> dtoList);
    /**
     * 修改  entity
     */
    public E updateByEntity(E entity);
    /**
     * 修改  Dto
     */
    public D updateByDTO(D entity);
    /**
     * 修改 entityList
     */
    public List<E> updateEntityList(List<E> entityList);

    /**
     * 修改 entityList
     */
    public List<D> updateDTOList(List<D> dtoList);
    /**
     * 删除 entity
     */
    public Integer deleteByEntity(E entity);
    /**
     * 删除DTO
     */
    public Integer deleteByDTO(D dto);

    /**
     * 删除entityList
     */
    public Integer deleteByEntityList(List<E> entityList);

    /**
     * 删除DTOLsit
     */
    public Integer deleteByDTOList(List<D> entityList);

    /**
     * 根据 ids 删除
     */
    public Integer deleteByIdList(List<String> ids);

    /**
     * 根据id 删除
     * @param id
     * @return
     */
    public Integer deleteById(String id);
}

