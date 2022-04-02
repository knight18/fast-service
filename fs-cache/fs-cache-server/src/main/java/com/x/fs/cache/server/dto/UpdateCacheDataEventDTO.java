package com.x.fs.cache.server.dto;

/**
 * @author x
 */
public class UpdateCacheDataEventDTO {

    private String cacheName;

    private String data;

    private String type;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UpdateCacheEventDTO{" +
                "cacheName='" + cacheName + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
