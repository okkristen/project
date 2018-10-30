package com.okkristen.project.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ysj
 * 时间工具类
 * @create 2018-10-30
 **/
public class MyDateUtil {

    public static Date strToDate(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return  simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return  new Date();
        }
    }
    public static String dateToStr(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return  simpleDateFormat.format(date);
    }
    /**
     * 获取 今天的 00：00：00
     * @return
     */
    public static Date getToday0() {
       StringBuilder stringBuilder = new StringBuilder(dateToStr(new Date(),"yyyy-MM-dd"));
       stringBuilder.append(" 00:00:00");
       return  strToDate(stringBuilder.toString(), "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 获取 今天的 23：59：59
     * @return
     */
    public static Date getToday24() {
        StringBuilder stringBuilder = new StringBuilder(dateToStr(new Date(),"yyyy-MM-dd"));
        stringBuilder.append(" 23:59:59");
        return  strToDate(stringBuilder.toString(), "yyyy-MM-dd hh:mm:ss");
    }

    
}
