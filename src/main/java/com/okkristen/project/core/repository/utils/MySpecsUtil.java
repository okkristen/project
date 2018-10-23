package com.okkristen.project.core.repository.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MySpecsUtil {



    public static  <T>Specification<T>  getSpecbyEntity(final EntityManager entityManager, final T example) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                System.out.println("测试");
                return null;
            }
        };
    }
}
