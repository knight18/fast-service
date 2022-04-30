package com.x.fs.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 多数据源配置值
 * @author x
 */
@ConfigurationProperties(prefix = "jdbc")
public class MoreDataSourceProperties {

    private String defaultDataSourceId;
    private DataSourceProperties[] dataSources;

    public String getDefaultDataSourceId() {
        return defaultDataSourceId;
    }

    public void setDefaultDataSourceId(String defaultDataSourceId) {
        this.defaultDataSourceId = defaultDataSourceId;
    }

    public DataSourceProperties[] getDataSources() {
        return dataSources == null ? null : dataSources.clone();
    }

    public void setDataSources(DataSourceProperties[] dataSources) {
        this.dataSources = (dataSources == null ? null : dataSources.clone());
    }

    public static class DataSourceProperties {
        private String id;
        private Map<String, Object> pool;
        private String[] mybatisPath;
        private String configLocation;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, Object> getPool() {
            return pool;
        }

        public void setPool(Map<String, Object> pool) {
            this.pool = pool;
        }

        public String[] getMybatisPath() {
            return mybatisPath;
        }

        public void setMybatisPath(String[] mybatisPath) {
            this.mybatisPath = mybatisPath;
        }

        public String getConfigLocation() {
            return configLocation;
        }

        public void setConfigLocation(String configLocation) {
            this.configLocation = configLocation;
        }
    }

    static class Pool {
        private Map<String, Object> properties;

        public Map<String, Object> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, Object> properties) {
            this.properties = properties;
        }
    }

}
