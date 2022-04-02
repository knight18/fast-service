package com.x.fs.cache.server.service.impl;

import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.cache.server.constant.CacheConstant;
import com.x.fs.cache.server.dto.CachedDataDataOperation;
import com.x.fs.cache.server.memory.MemoryCache;
import com.x.fs.cache.server.service.DataCache;
import com.x.fs.base.utils.MilliSecondUtils;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.BeansException;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/**
 * 缓存管理类
 * @author x
 */
@Slf4j
public final class CacheManager {

    private static final CacheManager INSTANCE = new CacheManager();

    private final ConcurrentMap<String, DataCache<?, ?>> cacheMap = new ConcurrentHashMap<>();

    private CacheManager() {
    }

    /**
     * 初始化所有自动业务缓存集合。统一交由CacheManager管理.
     * @param cacheMap 自定义缓存对象。
     */
    public void initCacheMap(ConcurrentMap<String, DataCache<K, ?> > cacheMap) {
        log.info("缓存加载开始......");
        for (String key : cacheMap.keySet()) {
            addCache(key, cacheMap.get(key), true);
        }
        log.info("缓存加载完成......");
    }

    public static CacheManager getInstance() {
        return INSTANCE;
    }

    public void addCache(String cacheName, DataCache cache) {
        addCache(cacheName, cache, false);
    }

    private <K, V> void addCache(String cacheName, DataCache<K, V> cache, boolean override) {
        if (override || !this.cacheMap.containsKey(cacheName)) {
            long time = MilliSecondUtils.currentTimeMillis();
            this.cacheMap.put(cacheName, cache);
            if (cache instanceof AbstractCacheData) {
                 ((AbstractCacheData<K, V>) cache).setCacheName(cacheName);
                ((AbstractCacheData<K, V>) cache).initialize();
                Map<K, V> data = ((AbstractCacheData<K, V>) cache).load();
                if (!CollectionUtils.isEmpty(data)) {
                    cache.putAll(data);
                }
            }
            long endTime = MilliSecondUtils.currentTimeMillis();
            log.info("[{}]缓存加载成功,耗时[{}]毫秒.", cacheName, endTime - time);
        }
    }

    /**
     * 通过缓存名获取缓存对象。只能获取当前进程已加载的缓存对象。
     * @param cacheName 缓存名
     * @return 缓存名对应缓存对象
     */
    public DataCache getCache(String cacheName) {
        return cacheMap.get(cacheName);
    }

    /**
     * 通过缓存类类型获取缓存对象
     * 只能获取当前进程已加载的缓存对象，该缓存类有且只有一个实例
     * @param classType 缓存类类型
     * @param <K> 缓存key类型
     * @param <V> 缓存value类型
     * @return Cache object
     */
    public <K, V> DataCache<K, V> getCache(Class<? extends AbstractCacheData<K, V>> classType) {
        try {
            return getCache(FsApplicationContext.getBean(classType).getCacheName());
        } catch (BeansException e) {
            log.info("缓存类型未找到[{}]", classType.toString());
        }
        return null;
    }

    /**
     * 移除通过缓存名移除缓存对象
     * @param cacheName 缓存名
     * @return CacheManager
     */
    public CacheManager removeCache(String cacheName) {
        this.cacheMap.remove(cacheName);
        return this;
    }

    /**
     * 构建缓存对象.
     * @param operation 缓存操作
     * @param cacheName 缓存名称
     * @return cache object
     */
    public DataCache buildCache(CachedDataDataOperation operation, String cacheName) {
        BaseDataCache cache = null;
        // 此处可以更具不同缓存类型，分别处理各情况，构建不同的初始化方法
        if (CacheConstant.DATA_CACHE_TYPE_ONE.equals(operation.getCacheType())) {
            cache = buildLocalCache(operation, cacheName);
        }
        if (cache != null) {
            cacheMap.putIfAbsent(cacheName, cache);
        }
        return cache;
    }

    private BaseDataCache buildLocalCache(CachedDataDataOperation operation, String cacheName) {
        BaseDataCache cache = new MemoryCache();
        initCache(cache, operation, cacheName);
        return cache;
    }

    private void initCache(BaseDataCache cache, CachedDataDataOperation operation, String cacheName) {
        cache.setCacheName(cacheName);
        if (operation.getExpire() > 0) {
            cache.setDefaultExpire(operation.getExpire());
        }
        if (operation.getTimeUnit() != null) {
            cache.setDefaultTimeUnit(operation.getTimeUnit());
        }
        cache.initialize();
    }

}
