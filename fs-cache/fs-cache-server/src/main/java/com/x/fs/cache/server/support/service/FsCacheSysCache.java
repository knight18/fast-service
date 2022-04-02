package com.x.fs.cache.server.support.service;

import com.x.fs.cache.server.service.impl.AbstractCacheData;

/**
 * fscache模块，缓存，其他各表数据缓存，需继承该类，重写部分方法
 * @author x
 */
public abstract class FsCacheSysCache<K, V> extends AbstractCacheData<K, V> {

    public V refreshItem(K key) {
        if (this.getCache() == null) {
            return null;
        }
        this.remove(key);
        V value = loadItem(key);
        if (value != null) {
            this.put(key, value);
        }
        return value;
    }

    public abstract V loadItem(K key);

}
