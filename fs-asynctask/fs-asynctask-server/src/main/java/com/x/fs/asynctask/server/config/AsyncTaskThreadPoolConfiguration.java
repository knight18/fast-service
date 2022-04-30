package com.x.fs.asynctask.server.config;

import com.x.fs.asynctask.server.dto.PrivateProperties;
import com.x.fs.asynctask.server.dto.PublicProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异步任务读取自定义的线程池信息
 * @author x
 */
@Configuration
public class AsyncTaskThreadPoolConfiguration {
    @Autowired
    private AsyncTaskThreadPoolsProperties asyncTaskThreadPoolsProperties;

    @Bean
    public AsyncListenableTaskExecutor publicAsynTaskExecutor() {
        PublicProperties properties = asyncTaskThreadPoolsProperties.getPublicProperties();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setKeepAliveSeconds(properties.getKeepAliveSeconds());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setQueueCapacity(properties.getQueueCapacity());
        return executor;
    }

    @Bean
    public Map<String, AsyncListenableTaskExecutor> privateAsyncExecutor() {
        Map<String, AsyncListenableTaskExecutor> map = new HashMap<>();
        List<PrivateProperties> list = asyncTaskThreadPoolsProperties.getPrivateProperties();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        list.forEach(s -> {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(s.getCorePoolSize());
            executor.setMaxPoolSize(s.getMaxPoolSize());
            executor.setKeepAliveSeconds(s.getKeepAliveSeconds());
            executor.setThreadNamePrefix(s.getThreadPoolKey());
            executor.setQueueCapacity(s.getQueueCapacity());
            executor.initialize();
            map.put(s.getThreadPoolKey(), executor);
        });
        return map;
    }

}
