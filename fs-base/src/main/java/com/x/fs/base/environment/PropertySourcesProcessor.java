package com.x.fs.base.environment;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class PropertySourcesProcessor implements EnvironmentPostProcessor, Ordered {
    private static final Logger log = LoggerFactory.getLogger(PropertySourcesProcessor.class);
    private static final String BACKSLASH = "/";
    private static final String YML_SUFFIX = ".yml";
    private static final String PROPERTIES_SUFFIX = ".properties";
    private static String CONFIG_PREFIX;
    private static String PROPERTIES_LOCATION;
    private static String YML_LOCATION;
    private String[] profiles;

    public PropertySourcesProcessor() {
    }

    @Override
    public int getOrder() {
        return -2147473637;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        this.profiles = environment.getActiveProfiles();
        if (!this.ignoreLocalConfig(environment)) {
            String rootPath = this.getRootPath(environment);
            log.info("config root path: {}", rootPath);
            if (!StringUtils.isEmpty(rootPath)) {
                YML_LOCATION = rootPath + "**/*.yml";
                PROPERTIES_LOCATION = rootPath + "**/*.properties";
                CONFIG_PREFIX = rootPath + "**/*-";
                MutablePropertySources mutablePropertySources = environment.getPropertySources();
                List<PropertySource> propertySources = this.loadSources();
                log.info("load propertySource:{}", propertySources);
                Iterator var6 = propertySources.iterator();

                while(var6.hasNext()) {
                    PropertySource propertySource = (PropertySource)var6.next();
                    mutablePropertySources.addLast(propertySource);
                }

            }
        }
    }

    private boolean pollConfigCenterConfig(ConfigurableEnvironment environment) {
        String configCenterEnabled = environment.getProperty("config.enabled");
        String configCenterUrl = environment.getProperty("config.uri");
        return (StringUtils.isEmpty(configCenterEnabled) || Objects.equals(configCenterEnabled, "true")) && !StringUtils.isEmpty(configCenterUrl);
    }

    private boolean ignoreLocalConfig(ConfigurableEnvironment environment) {
        String localEnabled = environment.getProperty("config.local-enabled");
        String kocaLocalEnabled = environment.getProperty("koca.config.local-enabled");
        return org.apache.commons.lang.StringUtils.equalsIgnoreCase(localEnabled, "false") || org.apache.commons.lang.StringUtils.equalsIgnoreCase(kocaLocalEnabled, "false");
    }

    private String getRootPath(ConfigurableEnvironment environment) {
        String springConfigLocation = environment.getProperty("spring.config.location");
        String kocaConfigLocation = environment.getProperty("fs.config.location");
        if (StringUtils.isEmpty(kocaConfigLocation)) {
            return "classpath*:config/";
        } else {
            String rootPath = kocaConfigLocation;
            if (!StringUtils.isEmpty(springConfigLocation) && !StringUtils.isEmpty(kocaConfigLocation)) {
                if (!springConfigLocation.endsWith("/") && !kocaConfigLocation.startsWith("/")) {
                    rootPath = springConfigLocation + "/" + kocaConfigLocation;
                } else if (springConfigLocation.endsWith("/") && kocaConfigLocation.startsWith("/")) {
                    springConfigLocation = springConfigLocation.substring(0, springConfigLocation.length() - 1);
                    rootPath = springConfigLocation + kocaConfigLocation;
                } else {
                    rootPath = springConfigLocation + kocaConfigLocation;
                }
            }

            if (!rootPath.endsWith("/")) {
                rootPath = rootPath + "/";
            }

            if (!ResourceUtils.isUrl(rootPath) && !rootPath.startsWith("classpath*:")) {
                rootPath = "file:" + rootPath;
            }

            return rootPath;
        }
    }

    private List<PropertySource> loadSources() {
        List<PropertySource> propertySources = new ArrayList();
        if (!ArrayUtils.isEmpty(this.profiles)) {
            String[] var2 = this.profiles;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String profile = var2[var4];
                propertySources.add(this.loadYmlSource(profile + "YmlConfig", CONFIG_PREFIX + profile + ".yml", false));
                propertySources.add(this.loadPropertiesSource(profile + "PropertiesConfig", CONFIG_PREFIX + profile + ".properties", false));
            }
        }

        propertySources.add(this.loadYmlSource("ymlConfig", YML_LOCATION, true));
        propertySources.add(this.loadPropertiesSource("propertiesConfig", PROPERTIES_LOCATION, true));
        return propertySources;
    }

    private PropertySource loadYmlSource(String sourceName, String location, boolean flag) {
        if (StringUtils.isEmpty(location)) {
            return null;
        } else {
            PropertiesPropertySource propertySource = null;

            try {
                YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
                yamlPropertiesFactoryBean.setResources(this.getResources(location, flag));
                yamlPropertiesFactoryBean.afterPropertiesSet();
                propertySource = new PropertiesPropertySource(sourceName, yamlPropertiesFactoryBean.getObject());
            } catch (Exception var6) {
                log.info(var6.getMessage(), var6);
            }

            return propertySource;
        }
    }

    private PropertySource loadPropertiesSource(String sourceName, String location, boolean flag) {
        if (StringUtils.isEmpty(location)) {
            return null;
        } else {
            PropertiesPropertySource propertySource = null;

            try {
                PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
                propertiesFactoryBean.setLocations(this.getResources(location, flag));
                propertiesFactoryBean.afterPropertiesSet();
                propertySource = new PropertiesPropertySource(sourceName, propertiesFactoryBean.getObject());
            } catch (Exception var6) {
                log.info(var6.getMessage(), var6);
            }

            return propertySource;
        }
    }

    private Resource[] getResources(String location, boolean flag) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(location);
        if (flag) {
            List<Resource> resourceList = new ArrayList();
            Resource[] var6 = resources;
            int var7 = resources.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Resource resource = var6[var8];
                if (!resource.getFilename().contains("-")) {
                    resourceList.add(resource);
                }
            }

            return (Resource[])resourceList.toArray(new Resource[resourceList.size()]);
        } else {
            return resources;
        }
    }
}
