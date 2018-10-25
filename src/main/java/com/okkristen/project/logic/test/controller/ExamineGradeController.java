package com.okkristen.project.logic.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考核表信息
 *
 * @author zpy
 * @create 2018/5/5
 */
@RestController
@RequestMapping("/api/examine/examineGrade")
public class ExamineGradeController {

//    @Autowired
//    private ExamineGradeService examineGradeService;
//
//    /**
//     * 查询列表
//     *
//     * @param examineGradeDTO
//     * @return
//     */
//    @PostMapping("/findAll")
//    public AjaxResult findAll(@RequestBody ExamineGradeDTO examineGradeDTO) {
//        if (examineGradeDTO == null) {
//            examineGradeDTO = new ExamineGradeDTO();
//        }
//        List<ExamineGradeDTO> list = examineGradeService.findByDTO(examineGradeDTO);
//        return AjaxResult.createSuccessResult(list);
//    }
//
//    /**
//     * 分页查询
//     *
//     * @param pageParam
//     * @return
//     */
//    @PostMapping("/findPage")
//    public AjaxResult findPage(@RequestBody PageParam<ExamineGradeDTO> pageParam) {
//        ExamineGradeDTO examineGradeDTO = pageParam.getParam();
//        Page<ExamineGradeDTO> page = examineGradeService.findByDTO(examineGradeDTO, pageParam.getPageable());
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
//            ExamineGradeDTO examineGradeDTO = examineGradeService.findDTOById(id);
//            return AjaxResult.createSuccessResult(examineGradeDTO);
//        }
//    }
//
//    /**
//     * 保存
//     *
//     * @param examineGradeDTO
//     * @return
//     */
//    @PostMapping("/save")
//    public AjaxResult save(@RequestBody ExamineGradeDTO examineGradeDTO) {
//        if (examineGradeDTO != null) {
//            examineGradeDTO = examineGradeService.saveByDTO(examineGradeDTO);
//            return AjaxResult.createSuccessResult(examineGradeDTO);
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
//    public AjaxResult update(@RequestBody ExamineGradeDTO dto) {
//        AjaxResult result = null;
//        if (dto != null && !StringUtils.isEmpty(dto.getId())) {
//            Integer examineGrade = examineGradeService.updateByDTO(dto);
//            if (examineGrade > 0) {
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
//            int res = examineGradeService.deleteByIdList(ids);
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
//     * Excel导出
//     * @param dto
//     * @param pageable
//     * @param request
//     * @param response
//     */
//    @RequestMapping("/exportExcel")
//    public void exportExcel(ExcelExamineGradeDTO dto, Pageable pageable, HttpServletRequest request, HttpServletResponse response){
//        examineGradeService.exportExcel(dto, pageable, "考核表信息", ExcelExamineGradeDTO.class, request, response);
//    }
}
