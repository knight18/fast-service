package com.x.fs.asynctask.server.service.impl;

import com.x.fs.asynctask.server.service.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/**
 * @author x
 */
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService {


    @Autowired
    @Qualifier(value = "publicAsynTaskExecutor")
    private AsyncListenableTaskExecutor publicAsynTaskExecutor;

    @Autowired
    @Qualifier(value = "privateAsyncExecutor")
    private Map<String, AsyncListenableTaskExecutor> privateAsyncExecutorMap;


    /**
     * 未指定私有线程池则直接提交任务给公共线程池
     * 如果没有指定的私有线程池也直接提交任务给公共线程池
     * 如果被私有线程池拒绝，则把任务提交给公共线程池
     *
     * @param privateThreadPoolKey
     * @param task
     */
    @Override
    public void execute(String privateThreadPoolKey, Runnable task) {
        do {
            if (privateThreadPoolKey == null) {
                publicAsynTaskExecutor.execute(task);
                break;
            }
            Executor privateAsyncExecutor = privateAsyncExecutorMap.get(privateThreadPoolKey);
            if (privateAsyncExecutor == null) {
                publicAsynTaskExecutor.execute(task);
                break;
            }
            try {
                privateAsyncExecutor.execute(task);
            } catch (RejectedExecutionException e) {
                publicAsynTaskExecutor.execute(task);
            }
        } while (false);
    }

    /**
     * 未指定私有线程池则直接提交任务给公共线程池
     * 如果没有指定的私有线程池也直接提交任务给公共线程池
     * 如果被私有线程池拒绝，则把任务提交给公共线程池
     *
     * @param privateThreadPoolKey
     * @param task
     * @param <T>
     * @return
     */
    @Override
    public <T> ListenableFuture<T> commit(String privateThreadPoolKey, Callable<T> task) {
        ListenableFuture<T> future;
        if (privateThreadPoolKey == null) {
            return publicAsynTaskExecutor.submitListenable(task);
        }
        AsyncListenableTaskExecutor privateAsyncExecutor = privateAsyncExecutorMap.get(privateThreadPoolKey);
        if (privateAsyncExecutor == null) {
            return publicAsynTaskExecutor.submitListenable(task);
        }
        try {
            future = privateAsyncExecutor.submitListenable(task);
        } catch (RejectedExecutionException e) {
            future = publicAsynTaskExecutor.submitListenable(task);
        }
        return future;
    }

}
