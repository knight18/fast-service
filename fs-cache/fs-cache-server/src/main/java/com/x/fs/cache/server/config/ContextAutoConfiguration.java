package com.x.fs.cache.server.config;

import com.x.fs.base.utils.FsApplicationContext;
import com.x.fs.cache.server.listener.ApplyLoadListener;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author x
 */
@Configuration
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10000)
public class ContextAutoConfiguration {

    @Bean("fs-cache-application-context")
    @ConditionalOnMissingBean
    public FsApplicationContext appContext() {
        return new FsApplicationContext();
    }

    @Bean("fs-cache-application-init-listener")
    public ApplyLoadListener appLoadListener(ApplicationContext context) {
        return new ApplyLoadListener(context);
    }

    @Bean
    @ConditionalOnMissingBean
    public ConfigUtils configUtils() {
        return new ConfigUtils();
    }

}
