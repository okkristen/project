package com.okkristen.project.core.service.impl;

import com.okkristen.project.core.repository.MyRepository;
import com.okkristen.project.core.service.CommonService;
import com.okkristen.project.core.utils.MyBeanUtil;
import com.okkristen.project.core.utils.MyReflectionUtil;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

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
    public E findById(String id) {
        Optional<E> optional = myRepository.findById(id);
        MyReflectionUtil.getParameterizedType(this.getClass());
        try {
            E entity = optional.get();
            return  entity;
        } catch (NoSuchElementException e) {
            System.out.println("数据库中并没有相对应的值");
            return null;
        }
    }

    @Override
    @Transactional
    public D findDTOById(String id) {
        E  entity = findById(id);
        if (entity == null) {
            return null;
        }
        return getDTO(entity);
    }

    @Override
    public <T> T findDTOById(String id, Class<T> tClass) {
        E  entity = findById(id);
        if (entity == null) {
            return  null;
        }
        return getTargetDTO(entity, tClass);
    }

    @Override
    @Transactional
    public List<D> findListByDTO(D dto) {
        List<E> entityList =  findListByEntity(getOriginalEntity(dto));
        return getDTOList(entityList);
    }

    @Override
    public <T> List<T> findListByDTO(D dto, Class<T> tClass) {
        List<E> entityList = findListByEntity(getOriginalEntity(dto));
        return getDTOList(entityList,tClass);
    }

    @Override
    public List<E> findListByEntity(E entity) {
        return myRepository.findByEntity(entity);
    }

    @Override
    public List<D> findListByDTO(D dto, Sort sort) {
        List<E> enitity = findListByEntity(getOriginalEntity(dto),sort);
        return getDTOList(enitity);
    }

    @Override
    public <T> List<T> findListByDTO(D dto, Sort sort, Class<T> tClass) {
        List<E> enitity = findListByEntity(getOriginalEntity(dto),sort);
        return getDTOList(enitity,tClass);
    }

    @Override
    @Transactional
    public List<E> findListByEntity(E entity, Sort sort) {
        return myRepository.findByEntity(entity,sort);
    }

    @Override
    @Transactional
    public Page<E> findPageByEntity(E entity, Pageable pageable) {
        return myRepository.findByEntityAccurate(entity, pageable);
    }

    @Override
    @Transactional
    public Page<D> findPageByDTO(D dto, Pageable pageable) {
        Page<E> entityPage = findPageByEntity(getOriginalEntity(dto),pageable);
        return getDTOPage(entityPage);
    }

    @Override
    @Transactional
    public <T> Page<T> findPageByDTO(D dto, Pageable pageable, Class<T> tClass) {
        Page<E> entityPage = findPageByEntity(getOriginalEntity(dto),pageable);
        return getDTOPage(entityPage,tClass);
    }

    @Override
    @Transactional
    public D getDTO(E entity) {
        D dtoObject = (D)MyReflectionUtil.getDTOByServiceClass(this.getClass());
//        MyBeanUtil.copyObjectProperties(entity,dtoObject);
//        dtoObject =(D)MyBeanUtil.copyJsonObjectProperties(entity,dtoObject.getClass());
        return (D) getTargetDTO(entity,dtoObject.getClass());
    }

    @Override
    @Transactional
    public <T> T getTargetDTO(E entity, Class<T> tClass) {
//        try {
//            T tObject = tClass.newInstance();
//            MyBeanUtil.copyObjectProperties(entity,tObject);
//            return tObject;
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        return (T) MyBeanUtil.copyJsonObjectProperties(entity,tClass);
    }

    @Override
    public  E getSourceEntity(D dto) {
        E entity = (E)MyReflectionUtil.getEntityByServiceClass(this.getClass());
//        MyBeanUtil.copyObjectProperties(dto,entity);
//        return entity;
        return (E) MyBeanUtil.copyJsonObjectProperties(dto,entity.getClass());
    }
    @Override
    public  List<E> getSourceEntityList(List<D> dtoList) {
        List<E> entityList = new ArrayList<>();
        for (D dto: dtoList) {
            E entity = getSourceEntity(dto);
            entityList.add(entity);
        }
        return entityList;
    }
    @Override
    public <T> E getOriginalEntity(T tDTO) {
        E entity = (E)MyReflectionUtil.getEntityByServiceClass(this.getClass());
//        MyBeanUtil.copyObjectProperties(tDTO,entity);
        return (E) MyBeanUtil.copyJsonObjectProperties(tDTO,entity.getClass());
    }

    @Override
    public <T> List<E> getOriginalEntityList(List<T> dtoList) {
        List<E> entityList = new ArrayList<>();
        for (T dto: dtoList) {
            E entity = getOriginalEntity(dto);
            entityList.add(entity);
        }
        return entityList;
    }
    @Override
    public List<D> getDTOList(List<E> entityList) {
        List<D> dtoList = new ArrayList<>();
        if (entityList == null || entityList.isEmpty()) {
            return  dtoList;
        }
        for (E entity: entityList) {
            dtoList.add(getDTO(entity));
        }
        return dtoList;
    }

    @Override
    public <T> List<T> getDTOList(List<E> entityList, Class<T> tClass) {
        List<T> dtoList = new ArrayList<>();
        for (E entity: entityList) {
            dtoList.add(getTargetDTO(entity,tClass));
        }
        return dtoList;
    }

    @Override
    public Page<D> getDTOPage(Page<E> entityPage) {
        Page<D> dtoPage = new PageImpl<>(getDTOList(entityPage.getContent()),entityPage.getPageable(),entityPage.getTotalElements());
        return dtoPage;
    }

    @Override
    public <T> Page<T> getDTOPage(Page<E> entityPage, Class<T> tClass) {
        Page<T> dtoPage = new PageImpl<T>(getDTOList(entityPage.getContent(),tClass),entityPage.getPageable(),entityPage.getTotalElements());
        return dtoPage;
    }

    @Override
    public E saveByEntity(E entity) {
        return  myRepository.saveAndFlush(entity);
    }

    @Override
    public D saveByDTO(D dto) {
        E entity = getSourceEntity(dto);
        return getDTO(saveByEntity(entity));
    }

    @Override
    @Transactional
    public List<E> saveByEntityList(List<E> entityList) {
        return  myRepository.saveAll(entityList);
    }

    @Override
    @Transactional
    public List<D> saveByDTOList(List<D> dtoList) {
        return getDTOList(saveByEntityList(getSourceEntityList(dtoList)));
    }

    @Override
    public E updateByEntity(E entity) {
        // 查询原来的 合并传入的 修改成最新的额 getObjectId
        String id = getObjectId(entity);
        if (!StringUtils.isEmpty(id)) {
            entity = (E)  MyBeanUtil.mergeJsonObjectProperties(entity, findById(id), entity.getClass());
        }
        return myRepository.saveAndFlush(entity);
    }

    @Override
    public D updateByDTO(D dto) {
        return getDTO(updateByEntity(getSourceEntity(dto)));
    }

    @Override
    public List<E> updateEntityList(List<E> entityList) {
        List<E> entitys = new ArrayList<>();
        for (E entity: entityList) {
            entitys.add(updateByEntity(entity));
        }
        return entitys;
    }

    @Override
    public List<D> updateDTOList(List<D> dtoList) {
        List<D> dtos = new ArrayList<>();
        for (D dto: dtoList) {
            dtos.add(updateByDTO(dto));
        }
        return dtos;
    }

    @Override
    public Integer deleteByEntity(E entity) {
        try {
            myRepository.delete(entity);
            return  1;
        } catch (Exception e) {
            return  0;
        }
    }

    @Override
    public Integer deleteByDTO(D dto) {
        return deleteByEntity(getSourceEntity(dto));
    }

    @Override
    public Integer deleteByEntityList(List<E> entityList) {
        Integer integer = 0;
        for (E entity: entityList) {
            integer += deleteByEntity(entity);
        }
        return integer;
    }

    @Override
    public Integer deleteByDTOList(List<D> entityList) {
        Integer integer = 0;
        for (D dto: entityList) {
            integer += deleteByDTO(dto);
        }
        return integer;
    }

    @Override
    public Integer deleteByIdList(List<String> ids) {
        Integer integer = 0;
        for (String id: ids) {
            integer += deleteById(id);
        }
        return integer;
    }

    @Override
    public Integer deleteById(String id) {
        try {
            myRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 获取用户id
     */
    public String getObjectId(Object object) {
        if (object != null) {
            String id = (String)MyReflectionUtil.invokeMethod(object, "getId");
            return id;
        }
        return "";
    }
    /**
     * 查询 并且复制到目标对象
     */
    public E copyPer() {
        return  null;
    }



}