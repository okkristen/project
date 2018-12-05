package com.okkristen.project.common.commonController;

import com.okkristen.project.core.config.Component.FilePathConfig;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.msg.MessageCode;
import com.okkristen.project.core.utils.MyFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileController{

    @Autowired
    private FilePathConfig filePathConfig;

    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file, HttpServletRequest request) {
        String userId = (String) request.getParameter("userId");
        String attachModule = (String) request.getParameter("attachModule");
        String repPath = (String) request.getParameter("repPath");
        AjaxResult result = null;
//        文件原来的名称
        String fileName = file.getOriginalFilename();
//        时间戳的名字
        String newFileName = fileName; // generateFileName(fileName);
        // 获取配置文件中上传路径，如果为空，则从request中获取路径
        String uploadPath = filePathConfig.getUploadPath() != null ? filePathConfig.getUploadPath() : request.getServletContext().getRealPath("");
        String filePath = "/files/file/" + attachModule + "/";
        String newFilePath = uploadPath + filePath + newFileName;
        isExists(uploadPath , filePath + newFileName);
//        System.out.println(newFilePath);
        File newFile = new File(newFilePath);
        try {
            MyFileUtil.inputstreamtofile(file.getInputStream(),newFile);
            result = AjaxResult.createSuccessResult(null);
        } catch (IOException e) {
            e.printStackTrace();
            result = AjaxResult.createErrorResult(MessageCode.UPDATE_FAILED);
        }
        return result;
    }

    /**
     * 判断文件是否存在
     * @param path
     */
    private void isExists (String uploadPath, String path) {
        File file = new File(uploadPath + path);
        if (path.indexOf(".") == -1) {
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            path = path.substring(0, path.lastIndexOf("/"));
            isExists(uploadPath, path);
        }
    }
    /**
     * 下载文件
     *
     * @param fileName
     * @param response
     * @return StreamingResponseBody
     */
    @GetMapping(value = "/downloadFile")
    public StreamingResponseBody downloadFile(String fileName,HttpServletRequest request, HttpServletResponse response) {
        String  filePath = System.getProperty("user.dir") + "\\views\\files\\file\\2\\数据结构.doc";
        fileName = "数据结构.doc";
        if (fileName != null) {
            //设置文件路径
            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
