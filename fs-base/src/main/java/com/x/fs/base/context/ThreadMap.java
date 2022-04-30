package com.x.fs.base.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadMap {

    private static final ThreadLocal<ThreadMap> THREAD_LOCAL = ThreadLocal.withInitial(() -> new ThreadMap());

    private Map<String, Object> map = new ConcurrentHashMap<>();

    public static ThreadMap getThreadMap() {
        return THREAD_LOCAL.get();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public Map<String, Object> getAll() {
        return this.map;
    }

    public void putAll(Map<String, Object> map) {
        this.map.putAll(map);
    }

    public <T> T get(String key) {
        return get(key, null);
    }

    public <T> T get(String key, T defaultVal) {
        return map.get(key) == null ? defaultVal : (T) map.get(key);
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object remove(String key) {
        return map.remove(key);
    }

    @Override
    public String toString() {
        return "ThreadMap{" + "map=" + map + '}';
    }

}
