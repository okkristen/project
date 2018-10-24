package com.okkristen.project.logic.test.service.impl;

import com.okkristen.project.core.service.impl.CommonServiceImpl;
import com.okkristen.project.logic.test.dao.ExamineItemRepository;
import com.okkristen.project.logic.test.dto.ExamineItemDTO;
import com.okkristen.project.logic.test.entity.ExamineItem;
import com.okkristen.project.logic.test.service.ExamineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考核项信息ServiceImpl
 *
 * @author zpy
 * @create 2018/5/4
 */
@Service
public class ExamineItemServiceImpl extends CommonServiceImpl<ExamineItem, ExamineItemDTO> implements ExamineItemService {
    @Autowired
    private ExamineItemRepository examineItemRepository;
    @Override
    public List<String> findDistinctByGroupName(String type){
      return  examineItemRepository.findDistinctByGroupName(type);
    }
}
