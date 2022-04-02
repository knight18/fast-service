package com.x.fs.cache.server.dto;

import com.x.fs.cache.server.constant.CacheConstant;

import java.util.concurrent.TimeUnit;

/**
 * @author x
 */
public class CachedDataDataOperation extends BaseCacheDataOperation {

    private TimeUnit timeUnit;

    private long expire;

    private String cacheType = CacheConstant.DATA_CACHE_TYPE_ONE;

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

}
