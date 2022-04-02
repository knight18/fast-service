package com.x.fs.cache.server.config;

import java.util.List;

/**
 * 获取配置文件数据实体类
 * @author x
 */
public class CacheProperties {

    private boolean updateNotify = true;
    /**
     * 是否开启缓存功能，默认开始
     */
    private boolean allEnabled;

    private List<EnabledCacheProperties> enabledCaches;

    public List<EnabledCacheProperties> getEnabledCaches() {
        return enabledCaches;
    }

    public void setEnabledCaches(List<EnabledCacheProperties> enabledCaches) {
        this.enabledCaches = enabledCaches;
    }

    public boolean getAllEnabled() {
        return allEnabled;
    }

    public void setAllEnabled(boolean allEnabled) {
        this.allEnabled = allEnabled;
    }

    public boolean isUpdateNotify() {
        return updateNotify;
    }

    public void setUpdateNotify(boolean updateNotify) {
        this.updateNotify = updateNotify;
    }

    public static class EnabledCacheProperties {
        /**
         * 缓存类型名称，还可以增加缓存类型，暂时还未实现
         */
        private String cacheName;

        public String getCacheName() {
            return cacheName;
        }

        public void setCacheName(String cacheName) {
            this.cacheName = cacheName;
        }
    }
}
