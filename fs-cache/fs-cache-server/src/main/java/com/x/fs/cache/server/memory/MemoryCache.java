package com.x.fs.cache.server.memory;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.x.fs.cache.server.constant.CacheConstant;
import com.x.fs.cache.server.service.impl.BaseDataCache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * caffeineCache 缓存数据
 * Caffeine 缓存不支持value为null
 * @author x
 */
public class MemoryCache<K, V> extends BaseDataCache<K, V> {

    private Cache<K, V> caffeineCache;

    public MemoryCache() {
        super();
        // caffeine 默认值是不设置 不设置就是-1
        this.setDefaultExpire(-1);
        super.setCacheType(CacheConstant.DATA_CACHE_TYPE_ONE);
    }

    /**
     * 获取当前缓存所有的数据.
     * @return 缓存的所有数据
     */
    @Override
    public Map<K, V> getAll() {
        return caffeineCache.asMap();
    }

    @Override
    public void put(K key, V value, long expireAfterWrite, TimeUnit timeUnit) {
        caffeineCache.put(key, value);
        super.afterPut(key, value, expireAfterWrite, timeUnit);
    }

    /**
     * 删除缓存指定项数据.
     * @param key 缓存key,不能为空
     */
    @Override
    public void remove(K key) {
        caffeineCache.invalidate(key);
        super.afterRemove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return caffeineCache.asMap().containsKey(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> entries) {
        caffeineCache.putAll(entries);
        super.afterPutAll();
    }

    @Override
    protected void initialize() {
        if (this.getDefaultExpire() == -1) {
            caffeineCache = Caffeine.newBuilder().build();
        } else {
            caffeineCache = Caffeine.newBuilder().expireAfterWrite(getDefaultExpire(), getDefaultTimeUnit()).build();
        }
    }

    @Override
    public void clear() {
        caffeineCache.invalidateAll();
        super.afterClear();
    }
    /**
     * 获取缓存中key对应的value值,如果缓存不存在，则重新加载对应数据
     * @param key 缓存键，不能为空
     * @return key对应的value
     */
    @Override
    public V get(K key) {
        return caffeineCache.getIfPresent(key);
    }

}
