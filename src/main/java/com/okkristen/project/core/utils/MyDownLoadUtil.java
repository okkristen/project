package com.okkristen.project.core.utils;

import com.okkristen.project.common.enums.TemplateTypeEnum;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 下载工具类
 * @author ysj
 * @create 2018-12-27
 **/
public class MyDownLoadUtil {

    /**
     * 下载 单个
     */
    public static  void  download(HttpServletResponse response,String fileName) {
        // 获取模板文件
        // 设置变量
        //设置文件路径
        response.setContentType("application/force-download");// 设置强制下载不打开
        try {
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));// 设置文件名
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            OutputStream outStream = response.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outStream,
                    "UTF-8");
            BufferedWriter sw = new BufferedWriter(writer);
//            template.merge(ctx, sw);
            sw.flush();
            sw.close();
            outStream.close();
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
    /**
     * zip下载
     */
    public static  void  downloadZip (HttpServletResponse response, String zipName) {
//        List<FileBean> fileList = fileService.getFileList();//查询数据库中记录
        response.setContentType("APPLICATION/OCTET-STREAM");
        try {
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(zipName,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + "cc.zip");// 设置文件名
            List<InputStream> list = new ArrayList<>();
            for (int i= 0; i< 5 ;i ++ ) {
                Template template = MyVelocityUtil.getTemplate(TemplateTypeEnum.Mybatis,"DtoTemplate.java.vm");
                VelocityContext ctx = new VelocityContext();
                ctx.put("domainName", "Test");
                ctx.put("domain","domain");
                ctx.put("packageName","passssss");
                StringWriter stringWriter = new StringWriter();
                template.merge(ctx,stringWriter);
                InputStream inputStream = new ByteArrayInputStream(stringWriter.toString().getBytes());
                list.add(inputStream);
            }
            byte[] buf = new byte[1024];
            int len;
            ZipOutputStream zout = new ZipOutputStream(response.getOutputStream());
            for (int i = 0; i < list.size(); i++) {
                InputStream in =list.get(i);
                zout.putNextEntry(new ZipEntry("DtoTemplate" + i + ".java"));
                while ((len = in.read(buf)) > 0) {
                    zout.write(buf, 0, len);
                }
                zout.closeEntry();
                in.close();
            }
            zout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
