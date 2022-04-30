package com.x.fs.asynctask.server.dto;

/**
 * @author x
 */
public class PrivateProperties extends Properties{
    private String threadPoolKey;

    public String getThreadPoolKey() {
        return threadPoolKey;
    }

    public void setThreadPoolKey(String threadPoolKey) {
        this.threadPoolKey = threadPoolKey;
    }
}
