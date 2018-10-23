package com.okkristen.project.core.service.impl;

import com.okkristen.project.core.repository.MyRepository;
import com.okkristen.project.core.service.CommonService;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;

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
    public E findById(String id) {
        Optional<E> optional = myRepository.findById(id);
        // 增加判空条件 不然会抛出 new NoSuchElementException("No value present");
        if (optional != null) {
            return optional.get();
        } else {
            return  null;
        }
    }

    @Override
    public D findDTOById(String id) {
        return getDTO(findById(id));
    }
    @Override
    public <T> T findDTOById(String id, Class<T> dtoClass) {
        return getDTO(findById(id), dtoClass);
    }

    @Override
    public List<E> findByEntity(E e) {
        return myRepository.findByEntityAccurate(e);
    }

    //	@Override
//	public List<E> findByEntity(E entity) {
//		return myRepository.findByEntity(entity);
//	}
    @Override
    public List<D> findByDTO(D dto) {
       return  getDTOList(myRepository.findByEntityAccurate(getEntity(dto)));
//        return findByDTO(dto, false);
    }

    @Override
    public boolean findExist(D dto) {
        return myRepository.exists(Example.of(getEntity(dto)));
    }

    @Override
    public List<D> findByDTO(D dto, boolean accurateQuery) {
        if (accurateQuery)
            return getDTOList(myRepository.findByEntityAccurate(getEntity(dto)));
        else
            return getDTOList(myRepository.findByEntity(getEntity(dto)));
    }

    @Override
    public <T> List<T> findByDTO(T dto, Class<T> dtoClass) {
        return findByDTO(dto, dtoClass, false);
    }

    @Override
    public <T> List<T> findByInitDTO(D dto, Class<T> dtoClass, boolean accurateQuery) {
        if (accurateQuery)
            return getDTOList(myRepository.findByEntityAccurate(getEntityBySourceDTO(dto)), dtoClass);
        else
            return getDTOList(myRepository.findByEntity(getEntityBySourceDTO(dto)), dtoClass);
    }

    @Override
    public <T> List<T> findByDTO(T dto, Class<T> dtoClass, boolean accurateQuery) {
        if (accurateQuery)
            return getDTOList(myRepository.findByEntityAccurate(getEntityBySourceDTO(dto)), dtoClass);
        else
            return getDTOList(myRepository.findByEntity(getEntityBySourceDTO(dto)), dtoClass);
    }

    @Override
    public List<D> findByDTO(D dto, Sort sort) {
        return findByDTO(dto, sort, false);
    }
    @Override
    public List<D> findByDTO(D dto, Sort sort, boolean accurateQuery) {
        if (accurateQuery)
            return getDTOList(myRepository.findByEntityAccurate(getEntity(dto), sort));
        else
            return getDTOList(myRepository.findByEntity(getEntity(dto), sort));
    }

    @Override
    public <T> List<T> findByDTO(T dto, Sort sort, Class<T> dtoClass) {
        return findByDTO(dto, sort, dtoClass, false);
    }
    @Override
    public <T> List<T> findByDTO(T dto, Sort sort, Class<T> dtoClass, boolean accurateQuery) {
        if (accurateQuery)
            return getDTOList(myRepository.findByEntityAccurate(getEntityBySourceDTO(dto), sort), dtoClass);
        else
            return getDTOList(myRepository.findByEntity(getEntityBySourceDTO(dto), sort), dtoClass);
    }

    @Override
    public Page<D> findByDTO(D dto, Pageable pageable) {
        return findByDTO(dto, pageable, false);
    }

    @Override
    public Page<D> findByDTO(D dto, Pageable pageable, boolean accurateQuery) {
        if (accurateQuery)
            return getDTOPage(myRepository.findByEntityAccurate(getEntity(dto), pageable), pageable);
        else
            return getDTOPage(myRepository.findByEntity(getEntity(dto), pageable), pageable);
    }

    @Override
    public <T> Page<T> findByInitDTO(D dto, Pageable pageable, Class<T> dtoClass) {
        Page<E> page = myRepository.findByEntity(getEntityBySourceDTO(dto), pageable);
        return getDTOPage(page, pageable, dtoClass);
    }

    @Override
    public <T> Page<T> findByDTO(T dto, Pageable pageable, Class<T> dtoClass) {
        Page<E> page = myRepository.findByEntity(getEntityBySourceDTO(dto), pageable);
        return getDTOPage(page, pageable, dtoClass);
    }
    @Override
    public <T> Page<T> findByDTO(T dto, Pageable pageable, Class<T> dtoClass, boolean accurateQuery) {
        if (accurateQuery)
            return getDTOPage(myRepository.findByEntityAccurate(getEntityBySourceDTO(dto), pageable), pageable, dtoClass);
        else
            return getDTOPage(myRepository.findByEntity(getEntityBySourceDTO(dto), pageable), pageable, dtoClass);
    }

    @Override
    public E save(E entity) {
        if (entity != null) {
            try {
                return myRepository.saveAndFlush(entity);
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public D saveByDTO(D dto) {
        return getDTO(save(getEntity(dto)));
    }
    @Override
    public <T> T saveByDTO(T dto, Class<T> dtoClass) {
        return getDTO(save(getEntityBySourceDTO(dto)), dtoClass);
    }
    @Override
    public List<E> save(List<E> list) {
        if (list != null && !list.isEmpty())
            return myRepository.saveAll(list);
        return null;
    }
    @Override
    public List<D> saveByDTO(List<D> list) {
        return getDTOList(save(getEntityList(list)));
    }
    @Override
    public <T> List<T> saveByDTO(List<T> list, Class<T> dtoClass) {
        return getDTOList(save(getEntityListBySourceDTO(list)), dtoClass);
    }

    @Override
    public Integer update(E entity) {
        String id = null;// (String) ReflectionUtils.invokeMethod(entity.getClass(), "getId", entity);
        return updateEntity(entity, id);
    }

    @Override
    public Integer updateByDTO(D dto) {
        String id = null;//(String) ReflectionUtils.invokeMethod(dto.getClass(), "getId", dto);
        return updateEntity(dto, id);
    }
    @Override
    public <T> Integer updateByDTO(T dto, Class<T> dtoClass) {
        String id = null;//(String) ReflectionUtils.invokeMethod(dto.getClass(), "getId", dto);
        return updateEntity(dto, id);
    }

    private int updateEntity(Object source, String id) {
        if (!StringUtils.isEmpty(id)) {
            E entity = this.findById(id);
            if (entity != null) {
//                MyBeanUtils.copyProperties(source, entity);
                if (myRepository.saveAndFlush(entity) != null) {
                    return 1;
                }
            }
        }
        return 0;
    }
    @Override
    public Integer updateByDTO(List<D> list) {
        int i = 0;
        for (D d: list) {
            i += updateByDTO(d);
        }
        return i;
    }
    @Override
    public <T> Integer updateByDTO(List<T> list, Class<T> dtoClass) {
        int i = 0;
        for (T d: list) {
            i += updateByDTO(d, dtoClass);
        }
        return i;
    }
    @Override
    public Integer delete(D dto) {
        if (dto != null) {
            E e = getEntity(dto);
            try {
                List<E> list = myRepository.findByEntity(e);
                if (list != null && !list.isEmpty()) {
                    myRepository.deleteAll(list);
                    String id = null; //(String) ReflectionUtils.invokeMethod(e.getClass(), "getId", e);
                    if (!StringUtils.isEmpty(id)) {
                        E repeat = findById(id);
//                        if (repeat == null || StringUtils.isEmpty(ReflectionUtils.invokeMethod(repeat.getClass(), "getId", repeat))) {
                        if (repeat == null || StringUtils.isEmpty(null)) {
                            return 1;
                        }
                    } else {
                        List<E> repeat = myRepository.findByEntity(e);
                        if (repeat == null || repeat.isEmpty()) {
                            return 1;
                        }
                    }
                }
            } catch (StaleStateException exception) {
                exception.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    @Override
    public Integer delete(List<D> list) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            for (D d: list) {
                i += delete(d);
            }
        }
        return i;
    }

    @Override
    public Integer delete(String id) {
        if (!StringUtils.isEmpty(id)) {
            try {
                myRepository.deleteById(id);
                E e = findById(id);
//                if (MyBeanUtils.objectIsNull(e)) {
                if (true) {
                    return 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    @Override
    public Integer deleteByIdArray(String[] ids) {
        int i = 0;
        if (ids != null && ids.length > 0) {
            for (String str: ids) {
                i += delete(str);
            }
        }
        return i;
    }

    @Override
    public Integer deleteByIdList(List<String> list) {
        int i = 0;
        if (list != null && !list.isEmpty()) {
            for (String str: list) {
                i += delete(str);
            }
        }
        return i;
    }

    @Override
    public List<E> getEntityList(List<D> dtoList) {
        List<E> entityList = new ArrayList<E>();
        if(dtoList!=null){
            for(D dto : dtoList) {
                entityList.add(getEntity(dto));
            }
        }
        return entityList;
    }
    @Override
    public <T> List<E> getEntityListBySourceDTO(List<T> dtoList) {
        List<E> entityList = new ArrayList<E>();
        if(dtoList!=null){
            for(T dto : dtoList) {
                entityList.add(getEntityBySourceDTO(dto));
            }
        }
        return entityList;
    }
    @Override
    public E getEntity(D dto) {
        return getEntityFromSourceDTO(dto);
    }
    @Override
    public <T> E getEntityBySourceDTO(T dto) {
        return getEntityFromSourceDTO(dto);
    }
    @Override
    public List<D> getDTOList(List<E> entityList) {
        List<D> dtoList = new ArrayList<D>();
        if(entityList!=null){
            for(E entity : entityList) {
                dtoList.add(getDTO(entity));
            }
        }
        return dtoList;
    }
    @Override
    public D getDTO(E entity) {
        //1、通过反射获取注解“D”（即模型对象）的类类型
        Type[] types = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
        @SuppressWarnings("unchecked")
        Class<D> dtoClass = (Class<D>)types [1];
        D dto = null;
        try {
            dto = (D) dtoClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(entity==null){
            return null;
        }else{
//            MyBeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
    @Override
    public Page<D> getDTOPage(Page<E> page, Pageable pageable) {
        Page<D> dtoPage = new PageImpl<D>(getDTOList(page.getContent()), pageable, page.getTotalElements());
        return dtoPage;
    }

    @Override
    public <T> T getDTO(E entity, Class<T> dtoClass) {
        T dto = null;
        try {
            dto = (T) dtoClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(entity==null){
            return null;
        }else{
//            MyBeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }

    @Override
    public <T> List<T> getDTOList(List<E> entityList, Class<T> dtoClass) {
        List<T> dtoList = new ArrayList<>();
        if(entityList!=null){
            for(E entity : entityList) {
                dtoList.add(getDTO(entity, dtoClass));
            }
        }
        return dtoList;
    }

    @Override
    public <T> Page<T> getDTOPage(Page<E> page, Pageable pageable, Class<T> dtoClass) {
        Page<T> dtoPage = new PageImpl<T>(getDTOList(page.getContent(), dtoClass), pageable, page.getTotalElements());
        return dtoPage;
    }
    @SuppressWarnings("unchecked")
    private E getEntityFromSourceDTO(Object dto) {
        //1、通过反射获取注解“E”（即模型对象）的类类型
        Class<E> entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        E entity = null;
        try {
            entity = (E) entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(dto==null){
            return null;
        }else{
//            MyBeanUtils.copyProperties(dto, entity);
        }
        return entity;
    }
}