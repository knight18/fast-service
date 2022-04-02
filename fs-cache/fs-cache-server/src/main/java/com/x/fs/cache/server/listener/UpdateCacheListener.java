package com.x.fs.cache.server.listener;

import com.x.fs.cache.server.dto.UpdateCacheDataEventDTO;

/**
 * 更新缓存监听器
 * @author x
 */
public interface UpdateCacheListener {

    /**
     * 更新数据通知
     * @param input
     */
    void updateDataEvent(UpdateCacheDataEventDTO input);
}
