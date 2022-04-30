package com.x.fs.base.mybatis;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author x
 */
public final class ResourceResolver {
    private static final ResourcePatternResolver RESOURCE_RESOLVE = new PathMatchingResourcePatternResolver();

    private ResourceResolver() {
    }

    public static Resource[] resolveMapperLocations(String[] mapperLocations) {
        return  Stream.of((Object[]) Optional.ofNullable(mapperLocations).orElse(new String[0])).flatMap((location) -> Stream.of(getResources((String) location))).toArray((x$0) -> new Resource[x$0]);
    }

    private static Resource[] getResources(String location) {
        try {
            return RESOURCE_RESOLVE.getResources(location);
        } catch (IOException var2) {
            return new Resource[0];
        }
    }
}

