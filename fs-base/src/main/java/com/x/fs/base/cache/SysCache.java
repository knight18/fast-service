package com.x.fs.base.cache;

import java.util.StringJoiner;

/**
 * 程序执行过程中，用来缓存数据，顶层接口
 * @author x
 */
public interface SysCache<T> {

    /**
     * 获取缓存尺寸
     *
     * @return
     */
    int getSize();

    /**
     * 从缓存取出数据
     *
     * @param key
     * @return
     */
    T getValue(String key);

    /**
     * 新增缓存
     * @param key
     * @param value
     */
    void putValue(String key, T value);


    /**
     * 移除缓存中的数据
     *
     * @param key
     * @return
     */
    T removeValue(String key);

    /**
     * 清空缓存
     */
    void clear();


    /**
     * 创建缓存Key
     *
     * @param args
     * @return
     */
    default String createCacheKey(Object... args) {
        StringJoiner stringJoiner = new StringJoiner(":", "cacheKey[", "]");

        for (Object arg : args) {
            stringJoiner.add(String.valueOf(arg));
        }

        return stringJoiner.toString();
    }


}
