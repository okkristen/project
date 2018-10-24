package com.okkristen.project.logic.test.dao;

import com.okkristen.project.core.repository.MyRepository;
import com.okkristen.project.logic.test.entity.ExamineStreet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

/**
 * 考核项信息Repository
 *
 * @author zpy
 * @create 2018/5/4
 */
public interface ExamineStreetRepository extends MyRepository<ExamineStreet, String> {

    @Query(value = "SELECT a.id AS id,a.score as score,b.username as username,c.street_name as street_name,d.river_name AS river_name FROM examine_street a LEFT JOIN base_account b on b.id = a.account_id LEFT JOIN base_street c on c.id = a.street_id LEFT JOIN base_river d ON d.id = a.river_id WHERE examine_time BETWEEN ?1 AND DATE_ADD( ?1, INTERVAL 1 MONTH ) ORDER BY a.score DESC",nativeQuery = true)
    Page<Map<String, Integer>> findByParamAndTime(String time, Pageable pageable);

}
