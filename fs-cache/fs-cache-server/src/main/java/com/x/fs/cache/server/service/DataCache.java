package com.x.fs.cache.server.service;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 缓存功能最顶层接口
 * @author x
 */
public interface DataCache<K,V> {

    /**
     * 获取缓存中key对应的value值,如果缓存不存在，则重新加载对应数据
     * @param key 缓存键，不能为空
     * @return key对应的value
     */
    V get(K key);

    /**
     * 获取缓存名称
     * @return 缓存名
     */
    String getCacheName();

    /**
     * 获取缓存中key对应value值
     * @param key key
     * @param v v
     * @return 缓存值
     */
    V get(K key, Callable<V> v);

    /**
     * 获取缓存中所有的数据
     * @return 缓存的所有数据
     */
    Map<K, V> getAll();

    /**
     * 删除缓存指定key的数据
     * @param key 缓存key,不能为空
     */
    void remove(K key);

    /**
     * 缓存新增数据
     * @param key 缓存key
     * @param value 缓存value
     */
    void put(K key, V value);

    /**
     * 将value与缓存中的key关联,并设置过期时间
     * @param key key
     * @param value value
     * @param expireAfterWrite 过期时间
     * @param timeUnit 时间单位
     */
    void put(K key, V value, long expireAfterWrite, TimeUnit timeUnit);

    /**
     * 批量在缓存中插入键值对
     * 如果key不存在，则新增; 如果键值对已存在，则更新键值对
     * @param entries 键值对
     */
    void putAll(Map<? extends K, ? extends V> entries);

    /**
     * 清除缓存中的数据
     */
    void clear();

    /**
     * 刷新缓存中所有数据
     */
    void refresh();

    /**
     * 刷新缓存中指定项的数据
     * @param key key
     */
    void refresh(K key);

    /**
     * 判断缓存中key是否存在
     * @param key 缓存key,不能为空
     * @return boolean
     */
    boolean containsKey(K key);

}
