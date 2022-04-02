package com.x.fs.cache.server.service.impl;

import com.x.fs.cache.server.service.DataCache;
import com.x.fs.base.utils.FsApplicationContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 基础方法，可以不用都实现全部基础接口
 * @author x
 */
@Slf4j
public abstract class BaseDataCache<K, V> implements DataCache<K, V> {

    private long defaultExpire = Integer.MAX_VALUE;

    private TimeUnit defaultTimeUnit = TimeUnit.SECONDS;

    private String cacheName;

    private String cacheType;

    private UpdateDataCacheNotifier cacheUpdateNotifier;

    public BaseDataCache() {
        try {
            this.cacheUpdateNotifier = FsApplicationContext.getBean(UpdateDataCacheNotifier.class);
        } catch (Exception ex) {
            log.debug("CacheUpdateNotifier bean fail");
        }
    }

    /**
     * 初始化数据
     */
    protected abstract void initialize();

    /**
     * 获取缓存中key对应value值
     * @param key  缓存key
     * @param v 缓存value来源
     * @return 缓存值
     */
    @Override
    public V get(K key, Callable<V> v) {
        V result = get(key);
        if (result != null) {
            return result;
        }
        V value;
        try {
            value = v.call();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
        if (value != null) {
            put(key, value);
        }
        return value;
    }


    /**
     * 新增缓存数据
     * @param key   缓存key
     * @param value 缓存value
     */
    @Override
    public void put(K key, V value) {
        put(key, value, defaultExpire, defaultTimeUnit);
    }

    /**
     * 将value与缓存中的key关联,并设置过期时间
     * @param key              缓存key
     * @param value            缓存value
     * @param expireAfterWrite 过期时间
     * @param timeUnit         时间单位
     */
    @Override
    public void put(K key, V value, long expireAfterWrite, TimeUnit timeUnit) {

    }

    /**
     * 批量在缓存中插入键值对
     * 如果key不存在，则新增; 如果键值对已存在，则更新键值对
     * @param entries 键值对
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> entries) {

    }

    /**
     * 判断缓存key是否存在
     * @param key 缓存key,不能为空
     * @return true or false
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }


    @Override
    public void clear() {

    }

    @Override
    public void refresh() {

    }

    protected void afterPut(K key, V value, long expireAfterWrite, TimeUnit timeUnit) {
        if (this.cacheUpdateNotifier != null) {
            this.cacheUpdateNotifier.put(cacheName, key, value, expireAfterWrite, timeUnit);
        }
    }

    protected void afterPutAll() {
        if (this.cacheUpdateNotifier != null) {
            this.cacheUpdateNotifier.putAll(cacheName);
        }
    }

    protected void afterClear() {
        if (this.cacheUpdateNotifier != null) {
            this.cacheUpdateNotifier.clear(cacheName);
        }
    }

    protected void afterRemove(K key) {
        if (this.cacheUpdateNotifier != null) {
            this.cacheUpdateNotifier.remove(cacheName, key);
        }
    }

    @Override
    public String getCacheName() {
        return cacheName;
    }

    @Override
    public void refresh(K key) {

    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public void setDefaultTimeUnit(TimeUnit defaultTimeUnit) {
        this.defaultTimeUnit = defaultTimeUnit;
    }

    public long getDefaultExpire() {
        return defaultExpire;
    }

    public void setDefaultExpire(long defaultExpire) {
        this.defaultExpire = defaultExpire;
    }

    public TimeUnit getDefaultTimeUnit() {
        return defaultTimeUnit;
    }

    public String getCacheType() {
        return cacheType;
    }

}
