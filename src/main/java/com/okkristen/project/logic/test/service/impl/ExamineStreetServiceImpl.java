package com.okkristen.project.logic.test.service.impl;

import com.okkristen.project.core.service.impl.CommonServiceImpl;
import com.okkristen.project.logic.test.dao.ExamineStreetRepository;
import com.okkristen.project.logic.test.dto.ExamineStreetDTO;
import com.okkristen.project.logic.test.entity.ExamineStreet;
import com.okkristen.project.logic.test.service.ExamineStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 街道考核项信息ServiceImpl
 *
 * @author zpy
 * @create 2018/5/4
 */
@Service
public class ExamineStreetServiceImpl extends CommonServiceImpl<ExamineStreet, ExamineStreetDTO> implements ExamineStreetService {
    @Autowired
    ExamineStreetRepository examineStreetRepository;

    @Override
    public Page<Map<String, Integer>> findByParamAndTime(String time, Pageable pageable) {
        return examineStreetRepository.findByParamAndTime(time, pageable);
    }

}
