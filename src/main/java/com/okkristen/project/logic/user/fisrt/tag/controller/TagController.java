package com.okkristen.project.logic.user.fisrt.tag.controller;

import com.okkristen.project.common.Base.BaseController;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.page.PageParam;
import com.okkristen.project.logic.other.database.service.DatabaseFiledService;
import com.okkristen.project.logic.user.fisrt.tag.dto.TagDTO;
import com.okkristen.project.logic.user.fisrt.tag.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

/**
 * @author ysj
 * @create 2018-12-27
 **/
public class TagController extends BaseController<Tag> {
    @Autowired
    private DatabaseFiledService service;

    /**
     * 查询所有数据
     *
     * @param param
     * @return
     */
    @PostMapping("/findAll")
    public AjaxResult findAll(@RequestBody PageParam<Tag> param) {
        return super.findAll(service, param);
    }

    /**
     * 分页查询数据
     *
     * @param param
     * @return
     */
    @PostMapping("/findPage")
    public AjaxResult findPage(@RequestBody PageParam<Tag> param) {
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
    public AjaxResult save(@RequestBody TagDTO dto) {
        AjaxResult  result = super.save(service,dto);
        return result;
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody TagDTO dto) {
        return super.save(service,dto);
    }

}