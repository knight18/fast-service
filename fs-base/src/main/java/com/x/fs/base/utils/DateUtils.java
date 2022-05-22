package com.x.fs.base.utils;

import com.x.fs.base.dao.SystemDateMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间工具类
 * @author x
 */
@Component
public class DateUtils {

    public static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static SystemDateMapper systemDateMapper;

    public DateUtils(SystemDateMapper dataMapper) {
        systemDateMapper = dataMapper;
    }

    /**
     * 查询数据库系统时间
     * @return
     */
    public static Date getSystemDate(){
        return systemDateMapper.getSystemDate();
    }

    public static Timestamp getTimestamp(){
        return new Timestamp(systemDateMapper.getSystemDate().getTime());
    }

    /**
     * 查询数据库系统日期
     * @return
     */
    public static Integer getSysDate(){
        return systemDateMapper.getSelectSysDate();
    }

    /**
     * 查询数据库当前时间 时分秒 165340
     */
    public static Integer getTime(){
        return systemDateMapper.getSelectTime();
    }

    public static String timestampToStr(Timestamp timestamp, String dateFormatStr) {
        if (timestamp ==  null) {
            return "";
        }
        if (StringUtils.isEmpty(dateFormatStr)) {
            dateFormatStr = DEFAULT_DATETIME_FORMAT;
        }
        DateFormat df = new SimpleDateFormat(dateFormatStr);
        return df.format(timestamp);
    }

    public static String instantToStr(Instant instant, String dateFormatStr) {
        if (instant == null) {
            return "";
        }
        if (StringUtils.isEmpty(dateFormatStr)) {
            dateFormatStr = DEFAULT_DATETIME_FORMAT;
        }
        return DateTimeFormatter.ofPattern(dateFormatStr).withZone(ZoneId.systemDefault()).format(instant);
    }

    /**
     * 获取默认时间 1970-01-01 08:00:00
     * @return
     */
    public static Timestamp getDefualtTime() {
        return Timestamp.valueOf("1970-01-01 08:00:00");
    }


}
