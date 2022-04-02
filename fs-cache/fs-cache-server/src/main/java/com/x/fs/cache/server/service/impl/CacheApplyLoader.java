package com.x.fs.cache.server.service.impl;

import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.cache.server.config.CacheProperties;
import com.x.fs.cache.server.service.ApplyactionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author x
 */
public class CacheApplyLoader implements ApplyactionLoader {

    @Autowired
    private CacheProperties cacheProperties;

    @Override
    public void load() {
        boolean limit = !cacheProperties.getAllEnabled();
        // true 开启缓存， false 关闭缓存功能
        if(limit){
            return;
        }
        Map<String, CacheProperties.EnabledCacheProperties> enabledCacheMap =
                Optional.ofNullable(cacheProperties.getEnabledCaches()).orElse(new ArrayList<>()).stream()
                        .collect(Collectors.toMap(x -> x.getCacheName(), x -> x));
        CacheManager manager = CacheManager.getInstance();
        abstractCachesLoadAll(manager, enabledCacheMap);
    }

    /**
     * 加载AbstractCache代码实现缓存
     * @param cacheManager
     * @param enabledCacheMap
     */
    private void abstractCachesLoadAll(CacheManager cacheManager,
                                         Map<String, CacheProperties.EnabledCacheProperties> enabledCacheMap){
        Map<String, AbstractCacheData> cacheMap = FsApplicationContext.getBeansOfType(AbstractCacheData.class);
        for (String key : cacheMap.keySet()) {
            AbstractCacheData cache = cacheMap.get(key);
            String cacheName = StringUtils.hasText(cache.getCacheName()) ? cache.getCacheName() : key;

            if (!enabledCacheMap.containsKey(cacheName)) {
                continue;
            }
            //在此处可通过配置文件中，缓存类型，来给cache中 缓存类型赋值，变更缓存类型

            cacheManager.addCache(cacheName, cache);
        }
    }

    protected CacheProperties getCacheProperties() {
        return cacheProperties;
    }

    @Override
    public int getOrder() {
        return 5;
    }
}
