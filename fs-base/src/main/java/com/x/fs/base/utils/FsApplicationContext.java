package com.x.fs.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 获取Spring管理的类
 * @author x
 */
@Component
public class FsApplicationContext implements ApplicationContextAware, BeanPostProcessor {

    private static ApplicationContext springApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springApplicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    /**
     * 获取spring容器上下文。
     * @return ApplicationContext
     */
    public static ApplicationContext getSpringContext() {
        return springApplicationContext;
    }


    /**
     * 获取资源文件配置属性.
     * @param configKey 配置项key
     * @return
     */
    public static String getDefaultConfigProperty(String configKey) {
        Environment environment = getEnvironment();
        if (environment == null) {
            return null;
        }
        return environment.getProperty(configKey);
    }

    /**
     * 获取环境变量中key对应的String值, 默认返回null.
     * 等价于{@link #getDefaultConfigProperty(String)}
     * @param key 配置项key
     * @return
     */
    public static String getString(String key) {
        return getDefaultConfigProperty(key);
    }

    /**
     * 获取环境变量中key对应的布尔值, 默认返回false.
     * @param key 配置项key
     * @return boolean value
     */
    public static boolean getBoolean(String key) {
        Environment environment = getEnvironment();
        if (environment == null) {
            return false;
        }
        return environment.getProperty(key, Boolean.class, false);
    }

    /**
     * 获取环境变量中key对应的整型值, 默认返回0.
     * @param key 配置项key
     * @return int value
     */
    public static int getInt(String key) {
        Environment environment = getEnvironment();
        if (environment == null) {
            return 0;
        }
        return environment.getProperty(key, Integer.class, 0);
    }

    private static Environment getEnvironment() {
        if (springApplicationContext == null) {
            return null;
        }
        return springApplicationContext.getEnvironment();
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> type) {
        return springApplicationContext.getBeansWithAnnotation(type);
    }

    public static <T> T getBean(Class<T> clazz) {
        return springApplicationContext.getBean(clazz);
    }

    public static <T> T getBean(String instanceName, Class<T> clazz) {
        return springApplicationContext.getBean(instanceName, clazz);
    }

    public static <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) {
        return springApplicationContext.getBeansOfType(type);
    }

    public static <T> T getBean(String instanceName) {
        return (T) springApplicationContext.getBean(instanceName);
    }










}
