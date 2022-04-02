package com.x.fs.cache.server.config;

import com.x.fs.cache.server.service.impl.UpdateDataCacheNotifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author x
 */
@Configuration
@ConditionalOnProperty(name = "cache.updateNotify", havingValue = "true", matchIfMissing = true)
public class UpdateCacheAutoConfiguration {

    @Bean("fs-update-cache-notifier")
    public UpdateDataCacheNotifier cacheUpdateNotifier() {
        return new UpdateDataCacheNotifier();
    }

}
