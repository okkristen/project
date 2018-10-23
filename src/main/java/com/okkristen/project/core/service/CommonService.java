package com.okkristen.project.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * 通用Service
 * 定义了通用的接口方法定义
 */
public interface CommonService<E, D> {
    /**
     * 根据ID查找对象
     * @param id
     */
    public E findById(String id);
    /**
     * 根据ID查找DTO对象
     * @param id
     */
    public D findDTOById(String id);
    /**
     * 根据ID查找指定类型DTO对象
     */
    public <T> T findDTOById(String id, Class<T> dtoClass);

    /**
     * 根据实体类查找
     */
    List<E> findByEntity(E e);
    /**
     * 根据DTO中有值的自动查询，并将结果转换为DTO
     * 模糊查询
     * @param dto
     */
    public List<D> findByDTO(D dto);

    /**
     * 查找元素是否存在
     * @param dto
     * @return
     */
    boolean findExist(D dto);
    /**
     * 根据DTO中有值的自动查询，并将结果转换为DTO
     * @param dto
     * @param accurateQuery 是否精确查找
     */
    public List<D> findByDTO(D dto, boolean accurateQuery);
    /**
     * 根据DTO中有值的自动查询，并将结果转换为指定类型DTO
     * @param dto
     * @param dtoClass
     */
    public <T> List<T> findByDTO(T dto, Class<T> dtoClass);
    /**
     * 根据DTO中有值的字段查询，并将结果转换为指定类型DTO
     * @param dto
     * @param dtoClass
     * @param <T>
     */
    <T> List<T> findByInitDTO(D dto, Class<T> dtoClass, boolean accurateQuery);
    /**
     * 根据DTO中有值的自动查询，并将结果转换为指定类型DTO
     * @param dto
     * @param dtoClass
     * @param accurateQuery 是否精确查找
     */
    public <T> List<T> findByDTO(T dto, Class<T> dtoClass, boolean accurateQuery);
    /**
     * 根据DTO中有值的字段进行查询并排序
     * @param dto
     * @param sort
     * @return List<D>
     */
    public List<D> findByDTO(D dto, Sort sort);
    /**
     * 根据DTO中有值的字段进行查询并排序
     * @param dto
     * @param sort
     * @param accurateQuery 是否精确查找
     * @return List<D>
     */
    public List<D> findByDTO(D dto, Sort sort, boolean accurateQuery);
    /**
     * 根据DTO中有值的字段查询并排序，并将结果转换为指定类型DTO
     * @param dto
     * @param sort
     * @param dtoClass
     * @return List<T>
     */
    public <T> List<T> findByDTO(T dto, Sort sort, Class<T> dtoClass);
    /**
     * 根据DTO中有值的字段查询并排序，并将结果转换为指定类型DTO
     * @param dto
     * @param sort
     * @param dtoClass
     * @param accurateQuery 是否精确查找
     */
    public <T> List<T> findByDTO(T dto, Sort sort, Class<T> dtoClass, boolean accurateQuery);
    /**
     * 根据DTO中有值的字段查询，并将结果转换为DTO
     * @param dto
     * @param pageable
     * @return Page<D>
     */
    Page<D> findByDTO(D dto, Pageable pageable);

    /**
     * 根据DTO中有值的字段查询，并将结果转换为DTO
     * @param dto
     * @param pageable
     * @param accurateQuery 是否精确查找
     * @return Page<D>
     */
    public Page<D> findByDTO(D dto, Pageable pageable, boolean accurateQuery);
    /**
     * 根据DTO中有值的字段查询，并将结果转换为指定类型DTO
     * @param dto
     * @param pageable
     * @param dtoClass
     * @return Page<T>
     */
    public <T> Page<T> findByInitDTO(D dto, Pageable pageable, Class<T> dtoClass);
    /**
     * 根据DTO中有值的字段查询，并将结果转换为指定类型DTO
     * @param dto
     * @param pageable
     * @param dtoClass
     * @return Page<T>
     */
    public <T> Page<T> findByDTO(T dto, Pageable pageable, Class<T> dtoClass);
    /**
     * 根据DTO中有值的字段查询，并将结果转换为指定类型DTO
     * @param dto
     * @param pageable
     * @param dtoClass
     * @param accurateQuery 是否精确查找
     * @return
     */
    public <T> Page<T> findByDTO(T dto, Pageable pageable, Class<T> dtoClass, boolean accurateQuery);
    /**
     * 保存实体类至数据库
     * @param entity
     * @return
     */
    @Transactional
    public E save(E entity);
    /**
     * 根据DTO保存至数据库
     * @param dto
     * @return
     */
    @Transactional
    public D saveByDTO(D dto);
    /**
     * 根据指定DTO保存至数据库
     * @param dto
     * @return
     */
    public <T> T saveByDTO(T dto, Class<T> dtoClass);
    /**
     * 批量保存实体类至数据库
     * @param list
     * @return
     */
    @Transactional
    public List<E> save(List<E> list);
    /**
     * 批量保存DTO至数据库
     * @param list
     * @return
     */
    @Transactional
    public List<D> saveByDTO(List<D> list);
    /**
     * 批量保存指定DTO至数据库
     * @param list
     * @param dtoClass
     * @return
     */
    public <T> List<T> saveByDTO(List<T> list, Class<T> dtoClass);

    /**
     * 更新实体至数据库
     * @param entity
     * @return
     */
    public Integer update(E entity);
    /**
     * 由DTO更新实体至数据库
     * @param dto
     * @return
     */
    @Transactional
    public Integer updateByDTO(D dto);
    /**
     * 由指定DTO更新实体至数据库
     * @param dto
     * @param dtoClass
     */
    public <T> Integer updateByDTO(T dto, Class<T> dtoClass);
    /**
     * 由DTO List批量更新实体至数据库
     * @param list
     */
    @Transactional
    public Integer updateByDTO(List<D> list);
    /**
     * 由指定DTO List批量更新实体至数据库
     * @param list
     * @param dtoClass
     */
    public <T> Integer updateByDTO(List<T> list, Class<T> dtoClass);
    /**
     * 根据实体删除相应数据
     */
    @Transactional
    public Integer delete(D dto);
    /**
     * 根据实体批量删除相应数据
     * @param list
     */
    @Transactional
    public Integer delete(List<D> list);
    /**
     * 根据数据ID删除相应数据
     * @param id
     * @return
     */
    @Transactional
    public Integer delete(String id);
    /**
     * 根据数据ID批量删除相应数据
     * @param ids
     * @return
     */
    @Transactional
    public Integer deleteByIdArray(String[] ids);
    /**
     * 根据数据ID批量删除相应数据
     * @param list
     * @return
     */
    @Transactional
    public Integer deleteByIdList(List<String> list);
    /**
     * DTO转Entity
     * @param dto
     * @return
     */
    public E getEntity(D dto);
    /**
     * 指定类型DTO转Entity
     * @param dto
     * @param dtoClasss
     * @return
     */
    public <T> E getEntityBySourceDTO(T dto);
    /**
     * DTO list转Entity list
     * @param dtoList
     * @return
     */
    public List<E> getEntityList(List<D> dtoList);
    /**
     * 指定类型DTO list转Entity list
     * @param dtoList
     * @param dtoClass
     * @return
     */
    public <T> List<E> getEntityListBySourceDTO(List<T> dtoList);
    /**
     * Entity转DTO
     * @param entity
     * @return
     */
    public D getDTO(E entity);
    /**
     * Entity转指定类型DTO
     * @param entity
     * @param dtoClass
     */
    public <T> T getDTO(E entity, Class<T> dtoClass);
    /**
     * Entity List 转 DTO list
     * @param entityList
     * @return
     */
    public List<D> getDTOList(List<E> entityList);
    /**
     * Entity List 转指定类型 DTO List
     * @param entityList
     * @param dtoClass
     */
    public <T> List<T> getDTOList(List<E> entityList, Class<T> dtoClass);
    /**
     * Entity Page转DTO Page
     * @param page
     * @return
     */
    public Page<D> getDTOPage(Page<E> page, Pageable pageable);
    /**
     * 获取指定DTO类型的Page
     * @param page
     * @param pageable
     * @param dtoClass
     */
    public <T> Page<T> getDTOPage(Page<E> page, Pageable pageable, Class<T> dtoClass);

}

