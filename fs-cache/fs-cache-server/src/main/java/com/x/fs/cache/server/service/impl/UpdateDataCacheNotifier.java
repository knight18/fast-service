package com.x.fs.cache.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.x.fs.cache.server.constant.CacheConstant;
import com.x.fs.cache.server.dto.UpdateCacheDataEventDTO;
import com.x.fs.cache.server.listener.UpdateCacheListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author x
 */
public class UpdateDataCacheNotifier {

    @Autowired(required = false)
    private List<UpdateCacheListener> updateCacheListeners;

    /**
     * 缓存put操作通知
     * @param cacheName 缓存名称
     * @param key key
     * @param value value
     * @param expireAfterWrite 失效时间
     * @param timeUnit 时间单位
     */
    public void put(String cacheName, Object key, Object value, long expireAfterWrite, TimeUnit timeUnit) {
        UpdateCacheDataEventDTO event = new UpdateCacheDataEventDTO();
        event.setType(CacheConstant.DATA_PUT);
        event.setCacheName(cacheName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", key);
        jsonObject.put("value", value);
        jsonObject.put("expireAfterWrite", expireAfterWrite);
        jsonObject.put("timeUnit", timeUnit);
        event.setData(jsonObject.toJSONString());
        notify(event);
    }

    /**
     * 缓存移除操作
     * @param cacheName 缓存名
     * @param key key
     */
    public void remove(String cacheName, Object key) {
        UpdateCacheDataEventDTO event = new UpdateCacheDataEventDTO();
        event.setType(CacheConstant.DATA_REMOVE);
        event.setCacheName(cacheName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", key);
        event.setData(jsonObject.toJSONString());
        notify(event);
    }

    /**
     * 缓存putAll操作通知
     * @param cacheName 缓存名
     */
    public void putAll(String cacheName) {
        UpdateCacheDataEventDTO event = new UpdateCacheDataEventDTO();
        event.setType(CacheConstant.DATA_PUT_ALL);
        event.setCacheName(cacheName);
        notify(event);
    }

    private void notify(UpdateCacheDataEventDTO input) {
        Optional.ofNullable(updateCacheListeners).orElse(new ArrayList<>()).forEach(x -> x.updateDataEvent(input));
    }

    /**
     * 缓存清除操作通知
     * @param cacheName 缓存名
     */
    public void clear(String cacheName) {
        UpdateCacheDataEventDTO event = new UpdateCacheDataEventDTO();
        event.setType(CacheConstant.DATA_CLEAR);
        event.setCacheName(cacheName);
        notify(event);
    }

}
