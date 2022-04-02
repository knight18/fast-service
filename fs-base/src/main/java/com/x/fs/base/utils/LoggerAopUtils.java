package com.x.fs.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author x
 */
public class LoggerAopUtils {

    /**
     * 	可扩展是否需要过滤一些参数不打印，预留待扩展,文件流类型不应该进行记录日志
     */
    private static Set<String> excludeProperties = Sets.newConcurrentHashSet();

    private LoggerAopUtils() {}

    public static String formatArgs(Object object) {
        if (object == null) {
            return "";
        }
        if (object instanceof String) {
            return (String) object;
        }
        return JSON.toJSONString(object, new SimplePropertyPreFilter() {
            @Override
            public boolean apply(JSONSerializer serializer, Object source, String name) {
                if (excludeProperties.contains(name) || name.toLowerCase().contains("list") || name.toLowerCase().contains("rows")) {
                    return false;
                }
                return true;
            }
        }, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
    }

    public static void setExcludeProperties (String name) {
        excludeProperties.add(name);
    }

}
