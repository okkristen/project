package com.okkristen.project.logic.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 区级考核项信息
 *
 * @author zpy
 * @create 2018/5/5
 */
@RestController
@RequestMapping("/api/examine/examineDistrict")
public class ExamineDistrictController {

//    @Autowired
//    private ExamineDistrictService examineDistrictService;
//
//    /**
//     * 查询列表
//     *
//     * @param examineDistrictDTO
//     * @return
//     */
//    @PostMapping("/findAll")
//    public AjaxResult findAll(@RequestBody ExamineDistrictDTO examineDistrictDTO) {
//        if (examineDistrictDTO == null) {
//            examineDistrictDTO = new ExamineDistrictDTO();
//        }
//        List<ExamineDistrictDTO> list = examineDistrictService.findByDTO(examineDistrictDTO);
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
//    public AjaxResult findPage(@RequestBody PageParam<ExamineDistrictDTO> pageParam) {
//        ExamineDistrictDTO examineDistrictDTO = pageParam.getParam();
//        Page<ExamineDistrictDTO> page = examineDistrictService.findByDTO(examineDistrictDTO, pageParam.getPageable());
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
//            ExamineDistrictDTO examineDistrictDTO = examineDistrictService.findDTOById(id);
//            return AjaxResult.createSuccessResult(examineDistrictDTO);
//        }
//    }
//
//    /**
//     * 保存
//     *
//     * @param examineDistrictDTO
//     * @return
//     */
//    @PostMapping("/save")
//    public AjaxResult save(@RequestBody ExamineDistrictDTO examineDistrictDTO) {
//        if (examineDistrictDTO != null) {
//            examineDistrictDTO = examineDistrictService.saveByDTO(examineDistrictDTO);
//            return AjaxResult.createSuccessResult(examineDistrictDTO);
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
//    public AjaxResult update(@RequestBody ExamineDistrictDTO dto) {
//        AjaxResult result = null;
//        if (dto != null && !StringUtils.isEmpty(dto.getId())) {
//            Integer examineDistrict = examineDistrictService.updateByDTO(dto);
//            if (examineDistrict > 0) {
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
//            int res = examineDistrictService.deleteByIdList(ids);
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
//    public void exportExcel(ExcelExamineDistrictDTO dto, Pageable pageable, HttpServletRequest request, HttpServletResponse response){
//        examineDistrictService.exportExcel(dto, pageable, "区级考核项信息", ExcelExamineDistrictDTO.class, request, response);
//    }
}
