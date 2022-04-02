package com.x.fs.cache.server.config;

import com.x.fs.cache.server.service.impl.CacheApplyLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 加载自动配置类
 * @author x
 */
@Configuration
public class CacheAutoConfiguration {

    @Bean("fs-cache-loader")
    @ConditionalOnMissingBean
    public CacheApplyLoader cacheLoader() {
        return new CacheApplyLoader();
    }

    @Bean
    @ConfigurationProperties("cache")
    public CacheProperties cacheProperties() {
        return new CacheProperties();
    }

}
