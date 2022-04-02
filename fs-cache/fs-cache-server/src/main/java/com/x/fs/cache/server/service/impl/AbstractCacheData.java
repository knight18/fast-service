package com.x.fs.cache.server.service.impl;

import com.x.fs.cache.server.constant.CacheConstant;
import com.x.fs.cache.server.dto.CachedDataDataOperation;
import com.x.fs.cache.server.service.DataCache;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 缓存数据实现类
 * @author x
 */
public abstract class AbstractCacheData<K, V> implements DataCache<K, V> {

    private BaseDataCache<K, V> cache;

    private String cacheName;

    private String cacheType = CacheConstant.DATA_CACHE_TYPE_ONE;

    public AbstractCacheData() {
        cacheName = getCacheName();
    }

    /**
     * 缓存对象初始化
     * 可以在此方法内，根据配置文件，加载异步加载缓存功能，此处还未实现
     */
    public void initialize() {
        CachedDataDataOperation operation = new CachedDataDataOperation();
        operation.setCacheType(getCacheType());
        cache = (BaseDataCache<K, V>) CacheManager.getInstance().buildCache(operation, cacheName);
    }

    @Override
    public V get(K key) {
        V value = cache.get(key);
        if (value == null) {
            value = load(key);
            if (value != null) {
                put(key, value);
            }
        }
        return value;
    }

    @Override
    public V get(K key, Callable<V> valueLoader) {
        return cache.get(key, valueLoader);
    }

    @Override
    public Map<K, V> getAll() {
        return cache.getAll();
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public void put(K key, V value, long expireAfterWrite, TimeUnit timeUnit) {
        cache.put(key, value, expireAfterWrite, timeUnit);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> entries) {
        cache.putAll(entries);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public void refresh() {
        cache.refresh();
        clear();
        Map<K, V> data = load();
        if (!CollectionUtils.isEmpty(data)) {
            putAll(data);
        }
    }

    @Override
    public void refresh(K key) {
        cache.refresh(key);
        remove(key);
        V value = load(key);
        if (value != null) {
            put(key, value);
        }
    }

    @Override
    public String getCacheName() {
        return this.cacheName;
    }

    public final void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheType() {
        return cacheType;
    }

    public final void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    /**
     * 加载特定key缓存数据
     * 默认实现为返回null
     * @param key 缓存key
     * @return 特定项数据
     */
    protected V load(K key) {
        return null;
    }

    /**
     * 加载缓存数据来源
     * @return map
     */
    protected abstract Map<K, V> load();

    public BaseDataCache getCache() {
        return this.cache;
    }

}
