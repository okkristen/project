package com.okkristen.project.logic.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.logic.test.dto.ParentDTO;
import com.okkristen.project.logic.test.dto.QueryEntityDTO;
import com.okkristen.project.logic.test.dto.StudentDTO;
import com.okkristen.project.logic.test.dto.TeatherDTO;
import com.okkristen.project.logic.test.entity.Teather;
import com.okkristen.project.logic.test.service.QueryEntityService;
import com.okkristen.project.logic.test.service.StudentService;
import com.okkristen.project.logic.test.service.TeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ysj
 * @create 2018-10-26
 **/
@RestController
@RequestMapping("/api/examine/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeatherService teatherService;

    @Autowired
    private QueryEntityService queryEntityService;
    /**
     * 查询列表
     *
     * @param
     * @return
     */
    @PostMapping("/findAll")
    public AjaxResult findAll(@RequestBody StudentDTO studentDTO) {
        if (studentDTO == null) {
            studentDTO = new StudentDTO();
        }
//        studentDTO.setAge(1);
        studentDTO.setName("杨2");
//        List<StudentDTO> list = studentService.findListByDTO(studentDTO);
        Long start = System.currentTimeMillis();
        List<TeatherDTO> list1 = new ArrayList<>();
//        for (int i = 0; i < 3 ; i++) {
//        studentDTO = new StudentDTO();
//           ParentDTO parentDTO = new ParentDTO();
//           parentDTO.setStudent(studentDTO);
//           parentDTO.setName("parentDTOS.add(parentDTO)");
//           parentDTO.setAge(11);
//           parentDTO.setSex(BigDecimal.TEN);
//           List<ParentDTO> list = new ArrayList<>();
//           list.add(parentDTO);
//           studentDTO.setParents(list);
//           studentDTO.setSex(BigDecimal.ONE);
//           studentDTO.setName("33");
//           List<StudentDTO> dtoList  = new ArrayList<>();
//           dtoList.add(studentDTO);
//           TeatherDTO teatherDTO = new TeatherDTO();
//           studentDTO.setTeather(teatherDTO);
//           teatherDTO.setAge(222);
//           teatherDTO.setName("教师");
//           teatherDTO.setStudent(dtoList);
//           list1.add(teatherDTO);
//        }
//        teatherService.saveByDTOList(list1);
//        TeatherDTO teatherDTO = new TeatherDTO();
//        teatherDTO.setName("测试1111");
//        teatherDTO.setId("1");
//        teatherService.updateByDTO(teatherDTO);
//        teatherService.deleteById("1");
//        teatherService.deleteById("1");
//        查询
//       studentDTO = new StudentDTO();
//       ParentDTO parentDTO = new ParentDTO();
//       parentDTO.setStudent(studentDTO);
////       父母
//       parentDTO.setName("parentDTOS.add(parentDTO)");
//       parentDTO.setAge(11);
//       parentDTO.setSex(BigDecimal.TEN);
//       List<ParentDTO> list = new ArrayList<>();
//       list.add(parentDTO);
//       studentDTO.setParents(list);
//       studentDTO.setSex(BigDecimal.ONE);
////    学生
//       studentDTO.setName("33");
//       List<StudentDTO> dtoList  = new ArrayList<>();
//       dtoList.add(studentDTO);
//       TeatherDTO teatherDTO = new TeatherDTO();
//       studentDTO.setTeather(teatherDTO);
//       teatherDTO.setAge(222);
//       teatherDTO.setName("教师");
//       teatherDTO.setStudent(dtoList);
//        JSONObject sourceJson = JSONObject.parseObject(JSONObject.toJSONString(teatherDTO));
////        教师
//        teatherDTO.setName("1");
//        List<TeatherDTO> teatherDTOList = teatherService.findListByDTO(teatherDTO);
//        System.out.println("时间" + (System.currentTimeMillis() - start));
//        return AjaxResult.createSuccessResult(teatherDTOList);
        QueryEntityDTO queryEntityDTO = new QueryEntityDTO();
//        queryEntityDTO.setaByte(Byte.parseByte("111"));
        // 小数
//        queryEntityDTO.setaDouble(Double.parseDouble("123"));
//        queryEntityDTO.setAge(Integer.valueOf(5));
//        queryEntityDTO.setHappyTime(new Date());
//         有问题
//        queryEntityDTO.setBool(Boolean.FALSE);
//        queryEntityDTO.setMoney(BigDecimal.TEN);
//         查询 有小数
//        queryEntityDTO.setNum1(Float.valueOf("999"));
//        studentDTO.setId("402881e766b01a1a0166b01bd1530000");
//        queryEntityDTO.setStudent(studentDTO);
//        queryEntityDTO.setLongId(Long.valueOf("958"));
//        queryEntityDTO.setName("测试");
        List<ParentDTO> parentDTOS = new ArrayList<>();
        ParentDTO parentDTO = new ParentDTO();
//        parentDTO.setAge(11222);
//        parentDTO.setSex(BigDecimal.valueOf(1));
        parentDTO.setName("ccccccc");
        parentDTOS.add(parentDTO);
        queryEntityDTO.setParentList(parentDTOS);
//        queryEntityDTO =   queryEntityService.saveByDTO(queryEntityDTO);
       List<QueryEntityDTO> queryEntityDTOs = queryEntityService.findListByDTO(queryEntityDTO);
        return AjaxResult.createSuccessResult(queryEntityDTOs);
    }
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
