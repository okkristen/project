package com.okkristen.project.core.service.impl;

import com.okkristen.project.core.repository.MyRepository;
import com.okkristen.project.core.service.CommonService;
import com.okkristen.project.core.utils.MyBeanUtil;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CommonService接口实现
 * 如果需要使用该实现，需要继承该Service
 * @author fengw
 * @create 2016年11月17日
 * @param <E>
 */
public class CommonServiceImpl<E, D> implements CommonService<E, D> {

    @Autowired
    private MyRepository<E, String> myRepository;

    public void setRepository(MyRepository<E, String> repository) {
        this.myRepository = repository;
    }

    @Override
    public E findById(Serializable id) {
        return null;
    }

    @Override
    public D findDTOById(Serializable id) {
        return null;
    }

    @Override
    public <T> T findDTOById(Serializable id, Class<T> tClass) {
        return null;
    }

    @Override
    public List<D> findListByDTO(D dto) {
        return null;
    }

    @Override
    public <T> List<T> findListByDTO(D dto, Class<T> tClass) {
        return null;
    }

    @Override
    public List<E> findListByEntity(E entity) {
        return null;
    }

    @Override
    public List<D> findListByDTO(D dto, Sort sort) {
        return null;
    }

    @Override
    public <T> List<T> findListByDTO(D dto, Sort sort, Class<T> tClass) {
        return null;
    }

    @Override
    public List<E> findListByEntity(E entity, Sort sort) {
        return null;
    }

    @Override
    public Page<E> findPageByEntity(E entity, Pageable pageable) {
        return null;
    }

    @Override
    public Page<D> findPageByDTO(D dto, Pageable pageable) {
        return null;
    }

    @Override
    public <T> Page<T> findPageByDTO(D dto, Pageable pageable, Class<T> tClass) {
        return null;
    }

    @Override
    public D getDTO(E entity) {
        return null;
    }

    @Override
    public <T> T getTargetObject(E entity, Class<T> tClass) {
        return null;
    }

    @Override
    public List<D> getDTOList(List<E> entityList) {
        return null;
    }

    @Override
    public <T> List<T> getDTOList(List<E> entityList, Class<T> tClass) {
        return null;
    }

    @Override
    public Page<D> getDTOPage(Page<E> entityPage) {
        return null;
    }

    @Override
    public <T> Page<T> getDTOPage(Page<E> entityPage, Class<T> tClass) {
        return null;
    }

    @Override
    public E saveByEntity(E entity) {
        return null;
    }

    @Override
    public D saveByDTO(D dto) {
        return null;
    }

    @Override
    public List<E> saveByEntityList(List<E> entityList) {
        return null;
    }

    @Override
    public List<D> saveByDTOList(List<D> entityList) {
        return null;
    }

    @Override
    public E updateByEntity(E entity) {
        return null;
    }

    @Override
    public D updateByDTO(D entity) {
        return null;
    }

    @Override
    public List<E> updateEntityList(List<E> entityList) {
        return null;
    }

    @Override
    public List<D> updateDTOList(List<D> dtoList) {
        return null;
    }

    @Override
    public Integer delectByEntity(E entity) {
        return null;
    }

    @Override
    public Integer delectByDTO(D dto) {
        return null;
    }

    @Override
    public Integer delectByEntityList(List<E> entityList) {
        return null;
    }

    @Override
    public Integer delectByDTOList(List<D> entityList) {
        return null;
    }
}