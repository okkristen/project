package com.okkristen.project.logic.test.dao;

import com.okkristen.project.core.repository.MyRepository;
import com.okkristen.project.logic.test.entity.ExamineItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 考核项信息Repository
 *
 * @author zpy
 * @create 2018/5/4
 */
public interface ExamineItemRepository extends MyRepository<ExamineItem, String> {

//    @Query(value = "SELECT DISTINCT(group_name) FROM examine_item WHERE examine_type = ?1",nativeQuery = true)
//    List<String> findDistinctByGroupName(String examineType);
}
