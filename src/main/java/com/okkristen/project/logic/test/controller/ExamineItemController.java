package com.okkristen.project.logic.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.core.JavaScan.ClassScannerUtils;
import com.okkristen.project.core.global.GlobalUtils;
import com.okkristen.project.core.javassist.JavassistUtil;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.msg.MessageCode;
import com.okkristen.project.core.redis.service.RedisService;
import com.okkristen.project.core.utils.MyBeanUtil;
import com.okkristen.project.core.utils.MyReflectionUtil;
import com.okkristen.project.logic.test.dto.ExamineDistrictDTO;
import com.okkristen.project.logic.test.dto.ExamineGradeDTO;
import com.okkristen.project.logic.test.dto.ExamineItemDTO;
import com.okkristen.project.logic.test.dto.ExamineStreetDTO;
import com.okkristen.project.logic.test.entity.ExamineDistrict;
import com.okkristen.project.logic.test.entity.ExamineGrade;
import com.okkristen.project.logic.test.entity.ExamineItem;
import com.okkristen.project.logic.test.service.ExamineItemService;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * 考核项信息
 *
 * @author zpy
 * @create 2018/5/5
 */
@RestController
@RequestMapping("/api/examine/examineItem")
public class ExamineItemController {

    @Autowired
    private ExamineItemService examineItemService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/redistest")
    public AjaxResult findtest(@RequestBody JSONObject jsonObject) {
        System.out.println("测试");
      ExamineItem examineItem = examineItemService.findById("1");
        redisService.set("test", "ceeee");
        String className = "com.okkristen.project.entity.Dem";
        Map<String,Class<?>> classMap = new HashMap<>();
        classMap.put("name", String.class);
        classMap.put("tel",String.class);
        Object aClass = null;
        try {
            aClass = JavassistUtil.createClass(className,classMap);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);
        System.out.println(redisService.get("test"));
        return  AjaxResult.createSuccessResultWithCode(MessageCode.UPDATE_SUCCESS, GlobalUtils.typesMap);
    }


//
//    /**
//     * 查询列表
//     *
//     * @param examineItemDTO
//     * @return
//     */
    @PostMapping("/findAll")
    public AjaxResult findAll(@RequestBody ExamineItemDTO examineItemDTO) {
        if (examineItemDTO == null) {
            examineItemDTO = new ExamineItemDTO();
        }
        ExamineGradeDTO gradeDTO = new ExamineGradeDTO();
        gradeDTO.setCreateTime(new Date());
        gradeDTO.setEnable(true);
        ExamineDistrictDTO examineDistrict  = new ExamineDistrictDTO();
        ExamineStreetDTO streetDTO = new ExamineStreetDTO();
        streetDTO.setRemark("streetDTO");
        streetDTO.setScore(2222);
        ExamineGradeDTO gradeDTO1 = new ExamineGradeDTO();
        gradeDTO1.setExamineType(6666);
        gradeDTO1.setExamineGrade("");
        streetDTO.setExamineGradeList(Arrays.asList(gradeDTO));
        examineDistrict.setExamineStreet(streetDTO);
        examineDistrict.setRiverLengthUnit("ExamineDistrict");
        gradeDTO.setExamineDistrict(examineDistrict);
        examineItemDTO.setGrade("");
        gradeDTO.setExamineItem(examineItemDTO);
        ExamineGrade grade = new ExamineGrade();
//        System.out.println(JSONObject.toJSONString(gradeDTO));
        MyBeanUtil.copyObjectProperties(gradeDTO,grade);
//        BeanUtils.copyProperties(gradeDTO,grade);
//        MyBeanUtil.copyJsonObjectProperties(gradeDTO,grade);
        return AjaxResult.createSuccessResult(grade);
    }
//
//    /**
//     * 分页查询
//     *
//     * @param pageParam
//     * @return
//     */
//    @PostMapping("/findPage")
//    public AjaxResult findPage(@RequestBody PageParam<ExamineItemDTO> pageParam) {
//        ExamineItemDTO examineItemDTO = pageParam.getParam();
//        Page<ExamineItemDTO> page = examineItemService.findByDTO(examineItemDTO, pageParam.getPageable());
//        return AjaxResult.createSuccessResult(page);
//    }
//
//    /**
//     * 详情
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("/findOne")
//    public AjaxResult findOne(String id) {
//        if (StringUtils.isEmpty(id)) {
//            return AjaxResult.createErrorResult(MessageCode.OPERATE_FAILED, "获取数据失败");
//        } else {
//            ExamineItemDTO examineItemDTO = examineItemService.findDTOById(id);
//            return AjaxResult.createSuccessResult(examineItemDTO);
//        }
//    }
//
//    /**
//     * 保存
//     *
//     * @param examineItemDTO
//     * @return
//     */
//    @PostMapping("/save")
//    public AjaxResult save(@RequestBody ExamineItemDTO examineItemDTO) {
//        if (examineItemDTO != null) {
//            examineItemDTO = examineItemService.saveByDTO(examineItemDTO);
//            return AjaxResult.createSuccessResult(examineItemDTO);
//        } else {
//            return AjaxResult.createErrorResult(MessageCode.SAVE_FAILED, "信息未填写完整");
//        }
//    }
//
//    /**
//     * 更新
//     *
//     * @param dto
//     * @return
//     */
//    @PostMapping("/update")
//    public AjaxResult update(@RequestBody ExamineItemDTO dto) {
//        AjaxResult result = null;
//        if (dto != null && !StringUtils.isEmpty(dto.getId())) {
//            Integer examineItem = examineItemService.updateByDTO(dto);
//            if (examineItem > 0) {
//                result = AjaxResult.createSuccessResult(dto);
//            }
//        } else {
//            result = AjaxResult.createErrorResult(MessageCode.UPDATE_FAILED, "数据ID不能为空");
//        }
//        return result;
//    }
//
//    /**
//     * 删除
//     *
//     * @param ids
//     * @return
//     */
//    @RequestMapping("/delete")
//    public AjaxResult delete(@RequestBody List<String> ids) {
//        AjaxResult result = null;
//        if (ids != null && ids.size() > 0) {
//            int res = examineItemService.deleteByIdList(ids);
//            if (res > 0) {
//                result = AjaxResult.createSuccessResultWithCode(MessageCode.DELETE_SUCCESS);
//            } else {
//                result = AjaxResult.createErrorResult(MessageCode.DELETE_FAILED);
//            }
//        } else {
//            AjaxResult.createErrorResult(MessageCode.DELETE_FAILED, "未选择数据");
//        }
//        return result;
//    }
//
//    /**
//     * 详情
//     *
//     * @param type
//     * @return
//     */
//    @RequestMapping("/findGroupName")
//    public AjaxResult findGroupName(String type) {
//        if (StringUtils.isEmpty(type)) {
//            return AjaxResult.createErrorResult(MessageCode.OPERATE_FAILED, "获取数据失败");
//        } else {
//            return AjaxResult.createSuccessResult( examineItemService.findDistinctByGroupName(type));
//        }
//    }
//
//    /**
//     * Excel导出
//     * @param dto
//     * @param pageable
//     * @param request
//     * @param response
//     */
//    @RequestMapping("/exportExcel")
//    public void exportExcel(ExcelExamineItemDTO dto, Pageable pageable, HttpServletRequest request, HttpServletResponse response){
//        examineItemService.exportExcel(dto, pageable, "考核项信息", ExcelExamineItemDTO.class, request, response);
//    }
}
