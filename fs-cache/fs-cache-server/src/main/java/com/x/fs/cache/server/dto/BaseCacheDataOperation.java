package com.x.fs.cache.server.dto;

import org.springframework.lang.Nullable;

/**
 * @author x
 */
public abstract class BaseCacheDataOperation {

    private String cacheName;

    public String getCacheName() {
        return this.cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * This implementation compares the {@code toString()} results.
     * @see #toString()
     */
    @Override
    public boolean equals(@Nullable Object other) {
        return (other instanceof BaseCacheDataOperation && toString().equals(other.toString()));
    }

    /**
     * This implementation returns {@code toString()}'s hash code.
     * @see #toString()
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "BaseCacheOperation{" +
                "cacheName='" + cacheName + '\'' +
                '}';
    }
}
