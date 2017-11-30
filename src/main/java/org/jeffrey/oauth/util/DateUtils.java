package org.jeffrey.oauth.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 日期处理工具类
 *
 * @author Jeffrey.Liu
 * @create 2017-11-30 16:43
 **/
public abstract class DateUtils {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime now(){
        return LocalDateTime.now();
    }

    public static String toDateTime(LocalDateTime dateTime){
        return toDateTime(dateTime,DEFAULT_DATE_TIME_FORMAT);
    }

    public static String toDateTime(LocalDateTime dateTime,String pattern){
        return dateTime.format(DateTimeFormatter.ofPattern(pattern, Locale.SIMPLIFIED_CHINESE));
    }

    public static String toDateText(LocalDate date,String pattern){
        if (date == null || pattern == null){
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern,Locale.SIMPLIFIED_CHINESE));
    }
}
