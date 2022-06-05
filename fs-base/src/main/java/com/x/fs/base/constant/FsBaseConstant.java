package com.x.fs.base.constant;

import java.util.regex.Pattern;

/**
 * @author x
 */
public interface FsBaseConstant {

    Pattern CONNECT_INFO_FORMAT = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

    String CHARACTER_ENCODE = "UTF-8";


    /**
     * ORACLE厂商标识
     */
    String ORACLE = "Oracle";
    /**
     * SQL_SERVER厂商标识
     */
    String SQL_SERVER = "Microsoft SQL Server";
    /**
     * MYSQL厂商标识
     */
    String MYSQL = "MySQL";


}
