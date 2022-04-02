package com.x.fs.cache.server.support.service.impl;

import com.x.fs.cache.server.service.impl.AbstractCacheData;
import com.x.fs.cache.server.service.impl.BaseDataCache;
import com.x.fs.cache.server.support.service.FsCacheSysCache;
import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.mbg.model.FsSysDict;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * fs_sys_dict表数据缓存，相当于service，给api层调用
 * @author x
 */
public class FsSysDictService {

    private static FsSysDictCache dictCache;

    public static FsSysDict getDictInfo(String dictNo,String key) {
        if (StringUtils.isEmpty(dictNo)|| StringUtils.isEmpty(key)) {
            return null;
        }
        if (dictCache == null) {
            dictCache = FsApplicationContext.getBean(FsSysDictCache.class);
        }
        Object value = getCacheItem(dictCache, dictNo);
        FsSysDict sysDict = null;
        if(value instanceof List){
            if(!CollectionUtils.isEmpty((Collection<?>) value)){
                for(Object s : (List)value){
                    FsSysDict dict = (FsSysDict) s;
                    if(key.equals(dict.getDictKey())){
                        sysDict = dict;
                        break;
                    }
                }
            }
        }else{
            if(key.equals(((FsSysDict)value).getDictKey())){
                sysDict = (FsSysDict) value;
            }
        }
        return sysDict;
    }

    private static Object getCacheItem(FsCacheSysCache<String, ?> cache, String dictNo) {
        Map<String, ?> cacheMap = getCacheMap(cache);
        if (cacheMap == null) {
            return cache.loadItem(dictNo);
        }
        Object item = cacheMap.get(dictNo);
        if (item == null) {
            item =  cache.refreshItem(dictNo);
        }
        return item;
    }

    public static Map<String, ?> getCacheMap(AbstractCacheData<String, ?> cache) {
        BaseDataCache<String, ?> cacheData = cache.getCache();
        if (cacheData == null) {
            return null;
        }
        return cacheData.getAll();
    }

}
