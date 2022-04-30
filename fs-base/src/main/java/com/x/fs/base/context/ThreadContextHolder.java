package com.x.fs.base.context;

/**
 * 线程内容
 * @author x
 */
public class ThreadContextHolder {
    private static final ThreadContextHolder CONTEXT_HOLDER = new ThreadContextHolder();

    protected ThreadContextHolder() {
    }

    public static ThreadContextHolder getContext() {
        return CONTEXT_HOLDER;
    }

    public <T> T get(String key, T defaultVal) {
        return this.getThreadMap().get(key, defaultVal);
    }

    public void put(String key, Object value) {
        this.getThreadMap().put(key, value);
    }

    public <T> T get(String key) {
        return (T) this.getThreadMap().get(key, null);
    }

    public <T> T remove(String key) {
        return (T) this.getThreadMap().remove(key);
    }

    private ThreadMap getThreadMap() {
        return ThreadMap.getThreadMap();
    }
}

