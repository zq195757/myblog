package com.zqweb.blog.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：日期处理工具类
 * @Author: zq
 * @Date: 2022年05月01日 22:09
 */
public class DFUtil {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 日期转为指定格式的字符串
    public static String date2Str(Date date){
        return simpleDateFormat.format(date);
    }
    // 字符串转成日期类型
    public static Date str2Date(String str) throws ParseException {
        return simpleDateFormat.parse(str);
    }

}
