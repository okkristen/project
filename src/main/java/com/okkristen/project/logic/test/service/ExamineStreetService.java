package com.okkristen.project.logic.test.service;

import com.okkristen.project.core.service.CommonService;
import com.okkristen.project.logic.test.dto.ExamineStreetDTO;
import com.okkristen.project.logic.test.entity.ExamineStreet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 街道考核项信息Service
 *
 * @author zpy
 * @create 2018/5/4
 */
public interface ExamineStreetService extends CommonService<ExamineStreet, ExamineStreetDTO> {

    Page<Map<String, Integer>> findByParamAndTime(String time, Pageable pageable);

}
