package com.okkristen.project.core.utils;

import com.okkristen.project.common.enums.TemplateTypeEnum;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * velocity工具类
 */
public class MyVelocityUtil {

    private static String template = "/templates/";

    public static Template getTemplate(String templateName) {
        // 初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        //可选值："class"--从classpath中读取，"file"--从文件系统中读取
        ve.setProperty("resource.loader", "class");
        //如果从文件系统中读取模板，那么属性值为org.apache.velocity.runtime.resource.loader.FileResourceLoader
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Template template=ve.getTemplate(MyVelocityUtil.template + templateName);
        ve.init();
        return  template;
    }

    /**
     * 根据 类型来获取 模板
     */
    public static Template getTemplate(TemplateTypeEnum typeEnum, String templateName) {
       return getTemplate(typeEnum.getPath() + templateName);
    }

    /**
     * 获取模板名字
     * @return
     */
    public static List<String> getTemplate() {
        List<String> list = Arrays.asList("DaoTemplate.java.vm","DtoTemplate.java.vm","ServiceImplTemplate.java.vm","ServiceTemplate.java.vm","ControllerTemplate.java.vm");
        return  new ArrayList<>(list);
    }


/*****************************************************************************/
    /**
     * 获取
     * @return
     */
    public static VelocityContext getVelocityContext() {
        return new VelocityContext();
    }

    /**
     * @param template
     * @param ctx
     * @return
     */
    public static Writer getWriter(Template template, VelocityContext ctx) {
        Writer writer = new StringWriter();
        template.merge(ctx,writer);
        return  writer;
    }
    /**
     * 获取
     */
    public static Writer create() {
        Template t = getTemplate("/templates/hellovelocity.vm");
        // 获取模板文件
        // 设置变量
        VelocityContext ctx = getVelocityContext();
        ctx.put("name", "Velocity");
        List list = new ArrayList();
        list.add("2");
        list.add("1");
        ctx.put("list", list);
        // 输出
        Writer sw = getWriter(t,ctx);
        BufferedWriter writer = new BufferedWriter(sw);
        return sw;
    }

    /**
     * 下载自定义内容文件
     */
    public static  void getCustom(HttpServletResponse response,VelocityContext ctx, Template template ,String fileName) {
//        Template t =  MyVelocityUtil.getTemplate("/templates/hellovelocity.vm");
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
            template.merge(ctx, sw);
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
}
//    String[] names={"one.jpg","two.jpg","three.jpg","four.jpg"};
//        //四个文件流
//        FileInputStream input1 = new FileInputStream(new File("文件路径"));
//        FileInputStream input2 = new FileInputStream(new File("文件路径"));
//        FileInputStream input3 = new FileInputStream(new File("文件路径"));
//        FileInputStream input4 = new FileInputStream(new File("文件路径"));
//        FileInputStream[] inputs={input1,input2,input3,input4};
//        //ZIP打包图片
//        File zipFile = new File("压缩文件存放路径");
//        byte[] buf = new byte[1024];
//        int len;
//        ZipOutputStream zout=new ZipOutputStream(new FileOutputStream(zipFile));
//        for (int i = 0; i < inputs.length; i++) {
//            FileInputStream in =inputs[i];
//            zout.putNextEntry(new ZipEntry(names[i]));
//            while ((len = in.read(buf)) > 0) {
//                zout.write(buf, 0, len);
//            }
//            zout.closeEntry();
//            in.close();
//        }
//        zout.close();
//
//
//        //下载图片
//        FileInputStream zipInput =new FileInputStream(zipFile);
//        OutputStream out = response.getOutputStream();
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment; filename=images.zip");
//        while ((len=zipInput.read(buf))!= -1){
//            out.write(buf,0,len);
//        }
//        zipInput.close();
//        out.flush();
//        out.close();
//        //删除压缩包
//        zipFile.delete();