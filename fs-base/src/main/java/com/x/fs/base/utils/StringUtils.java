package com.x.fs.base.utils;

import com.x.fs.common.exception.FsServiceException;

/**
 * 常用字符检查，或比较
 *
 * @author x
 */
public class StringUtils {

    /**
     * 检查字符，有无空格，回车等其他符号
     *
     * @param string
     */
    public static void stringFormCheck(String string, String remark) {
        if (string.contains(" ") || string.contains("&") || string.contains("\n") || string.contains("\t") || string.contains("|") || string.contains("<") || string.contains(">")) {
            throw new FsServiceException(remark + "[" + string + "]不能包含下列字符[空格、回车、制表符、|、&、>、<]");
        }
    }


    /**
     * 判断输入字符串是否与字符串列表其中之一相等
     *
     * @param inStr 输入字符串。为null直接返回false。
     * @param args  字符串列表
     * @return 等于 args 其中之一则返回true。
     */
    public static boolean equalAnyone(String inStr, String... args) {
        if (inStr == null) {
            return false;
        }

        for (String arg : args) {
            if (inStr.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    /**
     * TRIM后获取最后一行，并再次trim()
     *
     * @param inStr
     * @return
     */
    public static String getLastLine(String inStr) {
        if (inStr == null) {
            return "";
        }
        String trimStr = inStr.trim();

        int index = trimStr.lastIndexOf("\n");
        if (index == -1) {
            return trimStr;
        }
        return trimStr.substring(index).trim();
    }


}
