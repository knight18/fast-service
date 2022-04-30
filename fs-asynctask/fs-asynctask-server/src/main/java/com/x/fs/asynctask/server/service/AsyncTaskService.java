package com.x.fs.asynctask.server.service;

import org.springframework.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;

/**
 * 异步任务的service层
 * @author x
 */
public interface AsyncTaskService {

    /**
     * 提交异步任务，无返回
     * @param privateThreadPoolKey
     * @param task
     */
    void execute(String privateThreadPoolKey, Runnable task);

    /**
     * 提交异步任务，有返回
     * @param privateThreadPoolKey
     * @param task
     * @param <T>
     * @return
     */
    <T> ListenableFuture<T> commit(String privateThreadPoolKey, Callable<T> task);

}
