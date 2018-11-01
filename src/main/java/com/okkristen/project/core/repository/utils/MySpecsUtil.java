package com.okkristen.project.core.repository.utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MySpecsUtil {



    public static  <T>Specification<T>  getSpecbyEntity(final T example) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return MyPrediceUtil.getPredicate(root,criteriaQuery,criteriaBuilder,example);
            }
        };
    }
}
