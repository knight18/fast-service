package com.x.fs.common.dao;

import com.x.fs.common.utils.SimpleSelectLangDriver;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * 查询时间相关
 * @author x
 */
@Mapper
public interface SystemDateMapper {
    // mssql: select convert(varchar(100),GETDATE(),20)
    // oracle: select systimestamp from dual
    //@Select("select now()")   mysql
    @Select("SELECT CURRENT_TIMESTAMP(3) FROM DUAL")
    @Lang(SimpleSelectLangDriver.class)
    Date getSystemDate();

    //mssql : select CONVERT(varchar(30),GETDATE(),112)
    //ORACLE: select to_number(to_char(sysdate,'yyyymmdd')) from dual
    // mysql
    @Select("SELECT DATE_FORMAT(CURRENT_TIMESTAMP,'%Y%m%d') FROM DUAL")
    Integer getSelectSysDate();

    //oracle SELECT TO_CHAR(sysdate,'HH24')*1000000 + TO_CHAR(sysdate,'mi')*10000 + TO_CHAR(sysdate,'ss')*100 FROM DUAL;
    @Select("SELECT DATE_FORMAT(CURRENT_TIMESTAMP,'%H')*1000000 + DATE_FORMAT(CURRENT_TIMESTAMP,'%i')*10000 + DATE_FORMAT(CURRENT_TIMESTAMP,'%S')*100 FROM DUAL")
    Integer getSelectTime();
}
