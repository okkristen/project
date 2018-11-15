package com.okkristen.project.logic.database.controller;

import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.common.Base.BaseController;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.page.PageParam;
import com.okkristen.project.core.utils.MyBeanUtil;
import com.okkristen.project.logic.database.dto.DatabaseFiledDTO;
import com.okkristen.project.logic.database.entity.DatabaseFiled;
import com.okkristen.project.logic.database.service.DatabaseFiledService;
import com.okkristen.project.logic.test.dto.ExamineDistrictDTO;
import com.okkristen.project.logic.test.dto.ExamineGradeDTO;
import com.okkristen.project.logic.test.dto.ExamineItemDTO;
import com.okkristen.project.logic.test.dto.ExamineStreetDTO;
import com.okkristen.project.logic.test.entity.ExamineGrade;
import com.okkristen.project.logic.test.service.ExamineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 字段名
 */
@RestController
@RequestMapping("/database/filed")
public class DatabaseFiledController extends BaseController<DatabaseFiledDTO> {

    @Autowired
    private DatabaseFiledService service;

    /**
     * 查询所有数据
     *
     * @param param
     * @return
     */
    @PostMapping("/findAll")
    public AjaxResult findAll(@RequestBody PageParam<DatabaseFiledDTO> param) {
        return super.findAll(service, param);
    }

    /**
     * 分页查询数据
     *
     * @param param
     * @return
     */
    @PostMapping("/findPage")
    public AjaxResult findPage(@RequestBody PageParam<DatabaseFiledDTO> param) {
        return super.findPage(service, param);
    }

    @RequestMapping("/findOne")
    public AjaxResult findOne(String id) {
        return super.findOne(service, id);
    }

    @RequestMapping("/delete")
    public AjaxResult delete(@RequestBody List<String> ids) {
        AjaxResult  result = super.delete(service, ids);
        return result;
    }

    @PostMapping("/save")
    public AjaxResult save(@RequestBody DatabaseFiledDTO dto) {
        AjaxResult  result = super.save(service,dto);
        return result;
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody DatabaseFiledDTO dto) {
        return super.save(service,dto);
    }

}
