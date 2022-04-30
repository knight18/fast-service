package com.x.fs.base.cache.impl;

import com.x.fs.base.cache.SysCache;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.apache.ibatis.cache.decorators.SynchronizedCache;
import org.apache.ibatis.cache.impl.PerpetualCache;

/**
 * 请求数据缓存，当然也可以缓存其他数据
 * @author x
 */
public class RequestCache<T> implements SysCache {

    private Cache data;

    /**
     * 初始化数据
     * @param dataId
     */
    public RequestCache(String dataId){
        // 一种基于hashMap的缓存实现
        data = new PerpetualCache(dataId);

        // 线程同步
        data = new SynchronizedCache(data);

        // 溢出淘汰(LRU原则,最大默认1024个引用)
        data = new LruCache(data);
        ((LruCache) data).setSize(1024);

        // 过期清理(默认30s)
        data = new ScheduledCache(data);
        ((ScheduledCache) data).setClearInterval(30 * 1000);

        // 命中率统计
        data = new LoggingCache(data);
    }


    /**
     * 获取缓存尺寸
     *
     * @return
     */
    @Override
    public int getSize() {
        return data.getSize();
    }

    /**
     * 从缓存取出数据
     *
     * @param key
     * @return
     */
    @Override
    public Object getValue(String key) {
        return data.getObject(key);
    }

    /**
     * 新增缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void putValue(String key, Object value) {
         data.putObject(key,value);
    }

    /**
     * 移除缓存中的数据
     *
     * @param key
     * @return
     */
    @Override
    public Object removeValue(String key) {
        return data.removeObject(key);
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
        data.clear();
    }
}
