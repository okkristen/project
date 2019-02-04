package com.okkristen.project.logic.test.controller;

import com.okkristen.project.common.enums.TemplateTypeEnum;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.page.PageParam;
import com.okkristen.project.core.utils.MyVelocityUtil;
import com.okkristen.project.logic.test.dto.*;
import com.okkristen.project.logic.test.service.QueryEntityService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ysj
 * @create 2018-10-26
 **/
@RestController
@RequestMapping("/examine/student")
public class StudentController {

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
        studentDTO.setAge(1);
//        studentDTO.setName("杨2");
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
        queryEntityDTO.setLogin("denglu");
//        queryEntityDTO.setStartCreateTime(MyDateUtil.getToday0());
//        queryEntityDTO.setEndCreateTime(MyDateUtil.getToday24());
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
//        studentDTO.setId("8a8080a666bda7f10166bda830560004");
//        queryEntityDTO.setStudent(studentDTO);
//        queryEntityDTO.setLongId(Long.valueOf("958"));
//        queryEntityDTO.setName("测试");
        List<ParentDTO> parentDTOS = new ArrayList<>();
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setStudent(studentDTO);
//        parentDTO.setAge(2);
//        parentDTO.setSex(BigDecimal.valueOf(1));
//        parentDTO.setName("ccccccc");
        parentDTOS.add(parentDTO);
        queryEntityDTO.setParentList(parentDTOS);
//        queryEntityDTO =   queryEntityService.saveByDTO(queryEntityDTO);
        PageParam<QueryEntityDTO> pageParam = new PageParam<>();
        pageParam.setParam(queryEntityDTO);
        List<QueryEntityDTO> queryEntityDTOqq = queryEntityService.findListByDTO(pageParam.getParam());
       Page<QueryEntityDTO> queryEntityDTOs = queryEntityService.findPageByDTO(pageParam.getParam(),pageParam.getPageable());
        return AjaxResult.createSuccessResult(queryEntityDTOs);
    }
    /**
     * 下载
     */
    /**
     * 下载文件
     *
     * @param fileName
     * @param response
     * @return StreamingResponseBody
     */
    @GetMapping(value = "/downloadFile")
    public StreamingResponseBody downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
        String  filePath = System.getProperty("user.dir") + "\\views\\files\\file\\2\\数据结构.doc";
        fileName = "测试.java";
        Template template = MyVelocityUtil.getTemplate("DtoTemplate.java.vm");
        VelocityContext ctx = new VelocityContext();
        ctx.put("domainName", "Test");
        ctx.put("domain","domain");
        ctx.put("packageName","passssss");
        MyVelocityUtil.getCustom(response,ctx,template,fileName);
//        Template t =  MyVelocityUtil.getTemplate("/templates/hellovelocity.vm");
//        // 获取模板文件
//        // 设置变量
//        VelocityContext ctx = MyVelocityUtil.getVelocityContext();
//        ctx.put("name", "Velocity");
//        List list = new ArrayList();
//        list.add("1");
//        list.add("2");
//        ctx.put("list", list);
//        if (fileName != null) {
//            //设置文件路径
//            File file = new File(filePath);
//            if (file.exists()) {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName=" + "aaa.java");// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    OutputStream outStream = response.getOutputStream();
//                    OutputStreamWriter writer = new OutputStreamWriter(outStream,
//                            "UTF-8");
//                    BufferedWriter sw = new BufferedWriter(writer);
//                    t.merge(ctx, sw);
//                    sw.flush();
//                    sw.close();
//                    outStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
        return null;
    }

//    /**
//     * 打包压缩下载文件
//     */
//    @RequestMapping(value = "/downLoadZipFile")
//    public void downLoadZipFile(HttpServletResponse response) throws IOException{
//        String zipName = "myfile.zip";
////        List<FileBean> fileList = fileService.getFileList();//查询数据库中记录
//        response.setContentType("APPLICATION/OCTET-STREAM");
//        response.setHeader("Content-Disposition","attachment; filename="+zipName);
//        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
//            try {
//                response.setContentType("application/force-download");// 设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName=" + "cc.zip");// 设置文件名
//                List<InputStream> list = new ArrayList<>();
//                for (int i= 0; i< 5 ;i ++ ) {
//                    Template template = MyVelocityUtil.getTemplate(TemplateTypeEnum.Mybatis,"DtoTemplate.java.vm");
//                    VelocityContext ctx = new VelocityContext();
//                    ctx.put("domainName", "Test");
//                    ctx.put("domain","domain");
//                    ctx.put("packageName","passssss");
//                    StringWriter stringWriter = new StringWriter();
//                    template.merge(ctx,stringWriter);
//                    InputStream inputStream = new ByteArrayInputStream(stringWriter.toString().getBytes());
//                    list.add(inputStream);
//                }
//                byte[] buf = new byte[1024];
//                int len;
//                ZipOutputStream zout = new ZipOutputStream(response.getOutputStream());
//                for (int i = 0; i < list.size(); i++) {
//                    InputStream in =list.get(i);
//                    zout.putNextEntry(new ZipEntry("DtoTemplate" + i + ".java"));
//                    while ((len = in.read(buf)) > 0) {
//                        zout.write(buf, 0, len);
//                    }
//                    zout.closeEntry();
//                    in.close();
//                }
//                zout.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            out.close();
//        }
//    }


    /**
     * 打包压缩下载文件
     */
    @RequestMapping(value = "/downLoadZipFile")
    public void downLoadZipFile(HttpServletResponse response) throws IOException{
        String zipName = "myfile.zip";
//        List<FileBean> fileList = fileService.getFileList();//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");
        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
        try {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(zipName,"UTF-8"));
            List<InputStream> list = new ArrayList<>();
            byte[] buf = new byte[1024];
            int len;
            ZipOutputStream zout = new ZipOutputStream(response.getOutputStream());
            Map<String,InputStream> map = getTemplate();
            for (Map.Entry<String,InputStream> entry : map.entrySet()) {
               String key = entry.getKey();
               InputStream is = entry.getValue();
                zout.putNextEntry(new ZipEntry(getPath(key)));
                while ((len = is.read(buf)) > 0) {
                    zout.write(buf, 0, len);
                }
                zout.closeEntry();
                is.close();
            }
            zout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            out.close();
        }
    }

    // 拿到  几个模板 模板流
    private  Map<String,InputStream> getTemplate() {
        Map<String,InputStream> map = new HashMap<>();
        List<InputStream> list = new ArrayList<>();
        List<String> templateNames = MyVelocityUtil.getTemplate();
        for (String templateName : templateNames) {
//          模板
            Template template = MyVelocityUtil.getTemplate(TemplateTypeEnum.Mybatis,templateName);
            VelocityContext ctx = MyVelocityUtil.getVelocityContext();
            ctx.put("domainName", "Test");
            ctx.put("domain","domain");
            ctx.put("packageName","passssss");
            Writer stringWriter = new StringWriter();
            template.merge(ctx,stringWriter);
            InputStream inputStream = null;
            try {
                inputStream = new ByteArrayInputStream(new String( stringWriter.toString().getBytes("ISO-8859-1") , "utf-8").getBytes());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            list.add(inputStream);
            map.put(templateName,inputStream);
        }
        return map;
    }

    // 根据 模板 创建路径

    public  String getPath (String key) {
        String source = "src.main.java.";
//        程序名
        String projectNAME = source + "com.okkristen.project.";
//        com.okkristen.project.logic.test
//        logicName
        String logicName = "logic.";
        String customName = "user.fisrt.";
        String domainName = "article.";
//        String packageName = "article";
        String domain = domainName.substring(0,1).toUpperCase() + domainName.substring(1);
        String path = "";
        if (key.contains("Controller")) {
//          com.okkristen.project.logic.user.fisrt.article.controller
            path = (projectNAME + logicName + customName + domainName + "controller").replaceAll("\\.","/");
        }
        if (key.contains("Dao")) {
            path = (projectNAME + logicName + customName + domainName + "dao").replaceAll("\\.","/");
        }
        if (key.contains("Dto")) {
            path = (projectNAME + logicName + customName + domainName + "dto").replaceAll("\\.","/");
        }
        if (key.contains("Impl")) {
            path = (projectNAME + logicName + customName + domainName + "service.impl").replaceAll("\\.","/");
        }
        if (key.contains("ServiceTemplate.java.vm")) {
            path = (projectNAME + logicName + customName + domainName +  "service").replaceAll("\\.","/");
        }
        return   path + "/" + (domain.replaceAll("\\.", "")) +  key.replace("Template", "").replace(".vm", "");
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
    /**
     * 保存
     *
     * @param examineGradeDTO
     * @return
     */
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ExamineGradeDTO examineGradeDTO) {
        StudentDTO studentDTO = new StudentDTO();
        QueryEntityDTO queryEntityDTO = new QueryEntityDTO();
        queryEntityDTO.setaByte(Byte.parseByte("111"));
        // 小数
        queryEntityDTO.setaDouble(Double.parseDouble("123"));
        queryEntityDTO.setAge(Integer.valueOf(5));
        queryEntityDTO.setHappyTime(new Date());
//         有问题
        queryEntityDTO.setBool(Boolean.FALSE);
        queryEntityDTO.setMoney(BigDecimal.TEN);
//         查询 有小数
        queryEntityDTO.setNum1(Float.valueOf("999"));
        studentDTO.setId("8a8080a666bda7f10166bda830560004");
        queryEntityDTO.setStudent(studentDTO);
        queryEntityDTO.setLongId(Long.valueOf("958"));
        queryEntityDTO.setName("测试");
        List<ParentDTO> parentDTOS = new ArrayList<>();
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setAge(11222);
        parentDTO.setSex(BigDecimal.valueOf(1));
        parentDTO.setName("ccccccc");
        parentDTOS.add(parentDTO);
        queryEntityDTO.setParentList(parentDTOS);
        queryEntityDTO =   queryEntityService.saveByDTO(queryEntityDTO);
//       List<QueryEntityDTO> queryEntityDTOs = queryEntityService.findListByDTO(queryEntityDTO);
        return AjaxResult.createSuccessResult(queryEntityDTO);
    }
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
