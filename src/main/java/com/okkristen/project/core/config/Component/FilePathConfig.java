package com.okkristen.project.core.config.Component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取文件路径配置
 * @author fengw
 * @create 2017年3月28日
 */
@Component
@ConfigurationProperties(prefix = "web")
public class FilePathConfig {
    // 上传文件路径
    private String uploadPath = null;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}