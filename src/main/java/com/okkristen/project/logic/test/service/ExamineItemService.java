package com.okkristen.project.logic.test.service;

import com.okkristen.project.core.service.CommonService;
import com.okkristen.project.logic.test.dto.ExamineItemDTO;
import com.okkristen.project.logic.test.entity.ExamineItem;
import java.util.List;

/**
 * 考核项信息Service
 *
 * @author zpy
 * @create 2018/5/4
 */
public interface ExamineItemService extends CommonService<ExamineItem, ExamineItemDTO> {
   public List<String> findDistinctByGroupName(String type);
}
