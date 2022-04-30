package com.x.fs.cache.server.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author x
 */
@Component
public class ConfigUtils implements EnvironmentAware {

    private static Environment environment;

    public static String getString(String key) {
        return environment.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return environment.getProperty(key, Boolean.class, false);
    }

    public static int getInt(String key) {
        return environment.getProperty(key, Integer.class, 0);
    }

    @Override
    public void setEnvironment(Environment environment) {
        ConfigUtils.environment = environment;
    }


}
