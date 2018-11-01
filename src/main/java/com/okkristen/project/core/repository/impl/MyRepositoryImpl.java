package com.okkristen.project.core.repository.impl;

import com.okkristen.project.core.repository.MyRepository;
import com.okkristen.project.core.repository.utils.MySpecsUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @author fengw
 * @create 2016年11月16日
 * @param <E>
 * @param <ID>
 */
public class MyRepositoryImpl<E, ID extends Serializable> extends SimpleJpaRepository<E, ID>
        implements MyRepository<E, ID> {

    private final EntityManager entityManager;

    public MyRepositoryImpl(JpaEntityInformation<E, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<E> findByEntity(E example, Pageable pageable) {
        if (null == example) {
            return findAll(pageable);
        }
        return findAll(MySpecsUtil.getSpecbyEntity(example),pageable);
    }

    @Override
    public List<E> findByEntity(E example) {
        if (null == example) {
            return findAll();
        }
        return findAll(MySpecsUtil.getSpecbyEntity(example));
    }

    @Override
    public List<E> findByEntity(E example, Sort sort) {
        if (null == example) {
            return findAll(sort);
        }
        return findAll(MySpecsUtil.getSpecbyEntity(example),sort);
    }

    @Override
    public List<E> findByEntityAccurate(E example) {
        if (null == example) {
            return findAll();
        }
        return findAll(MySpecsUtil.getSpecbyEntity(example));
    }


    @Override
    public List<E> findByEntityAccurate(E example, Sort sort) {
        if (null == example) {
            return findAll(sort);
        }
        return findByEntity(example,sort);
    }

    @Override
    public Page<E> findByEntityAccurate(E example, Pageable pageable) {
        if (null == example) {
            return findAll(pageable);
        }
        return findByEntity(example,pageable);
    }

}
