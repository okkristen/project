package com.okkristen.project.core.logic.base.controller;

import com.okkristen.project.common.Base.BaseController;
import com.okkristen.project.core.logic.base.dto.BaseAttachDTO;
import com.okkristen.project.core.logic.base.entity.BaseAttach;
import com.okkristen.project.core.logic.base.service.BaseAttachService;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.page.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ysj
 * @create 2018-12-27
 **/
public class BaseAttachController extends BaseController<BaseAttach> {

    @Autowired
    private BaseAttachService service;

    /**
     * 查询所有数据
     *
     * @param param
     * @return
     */
    @PostMapping("/findAll")
    public AjaxResult findAll(@RequestBody PageParam<BaseAttach> param) {
        return super.findAll(service, param);
    }

    /**
     * 分页查询数据
     *
     * @param param
     * @return
     */
    @PostMapping("/findPage")
    public AjaxResult findPage(@RequestBody PageParam<BaseAttach> param) {
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
    public AjaxResult save(@RequestBody BaseAttachDTO dto) {
        AjaxResult  result = super.save(service,dto);
        return result;
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody BaseAttachDTO dto) {
        return super.save(service,dto);
    }

}
