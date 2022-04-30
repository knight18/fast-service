package com.x.fs.asynctask.server.dto;

/**
 * @author x
 */
public class PublicProperties extends Properties{
    private String threadNamePrefix;

    public String getThreadNamePrefix() {
        return threadNamePrefix;
    }

    public void setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }
}
