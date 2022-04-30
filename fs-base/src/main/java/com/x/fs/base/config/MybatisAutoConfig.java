package com.x.fs.base.config;

import com.x.fs.base.constant.DataSourceConstan;
import com.x.fs.base.datasource.DataSourceUtils;
import com.x.fs.base.mybatis.ResourceResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import com.x.fs.base.config.MoreDataSourceProperties.DataSourceProperties;

import javax.sql.DataSource;
import java.beans.FeatureDescriptor;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author x
 */
@Slf4j
public class MybatisAutoConfig implements InitializingBean {

    private final MybatisProperties properties;
    private final MoreDataSourceProperties moreDataSourceProperties;
    private final Interceptor[] interceptors;
    private final TypeHandler[] typeHandlers;
    private final LanguageDriver[] languageDrivers;
    private final ResourceLoader resourceLoader;
    private final DatabaseIdProvider databaseIdProvider;
    private final List<ConfigurationCustomizer> configurationCustomizers;
    private final ApplicationContext applicationContext;
    private final Supplier<SqlSessionFactoryBean> sqlSessionFactoryBeanSupplier;
    private MybatisAutoConfig.ApplyConfigurationFunction function;

    public MybatisAutoConfig(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, ObjectProvider<TypeHandler[]> typeHandlersProvider, ObjectProvider<LanguageDriver[]> languageDriversProvider, ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider, ObjectProvider<MoreDataSourceProperties> multiDataSourceProperties, ApplicationContext applicationContext, Supplier<SqlSessionFactoryBean> sqlSessionFactoryBeanSupplier, MybatisAutoConfig.ApplyConfigurationFunction applyConfigurationFunction) {
        this.properties = properties;
        this.moreDataSourceProperties = multiDataSourceProperties.getIfAvailable();
        this.interceptors = interceptorsProvider.getIfAvailable();
        this.typeHandlers = typeHandlersProvider.getIfAvailable();
        this.languageDrivers = languageDriversProvider.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
        this.applicationContext = applicationContext;
        this.sqlSessionFactoryBeanSupplier = sqlSessionFactoryBeanSupplier;
        this.function = applyConfigurationFunction;
    }

    @Override
    public void afterPropertiesSet() {
        this.checkConfigFileExists();
    }

    private void checkConfigFileExists() {
        if (this.properties.isCheckConfigLocation() && StringUtils.hasText(this.properties.getConfigLocation())) {
            Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
            Assert.state(resource.exists(), "Cannot find config location: " + resource + " (please add config file or check your Mybatis configuration)");
        }
    }

    @Bean
    @DependsOn({"sqlSessionFactoryMap"})
    @ConditionalOnBean({MoreDataSourceProperties.class})
    public CustomSqlSessionTemplate sqlSessionTemplate(Map<Object, SqlSessionFactory> sqlSessionFactoryMap, MoreDataSourceProperties moreDataSourceProperties) {
        String defaultDataSouceId = moreDataSourceProperties.getDefaultDataSourceId();
        SqlSessionFactory defaultSqlSessionFactory = sqlSessionFactoryMap.get(DataSourceUtils.getSqlSessionFactoryName(defaultDataSouceId));
        CustomSqlSessionTemplate customSqlSessionTemplate;
        ExecutorType executorType = this.properties.getExecutorType();
        if (executorType != null) {
            customSqlSessionTemplate = new CustomSqlSessionTemplate(defaultSqlSessionFactory, executorType);
        } else {
            customSqlSessionTemplate = new CustomSqlSessionTemplate(defaultSqlSessionFactory);
        }
        log.info("sqlSessionTemplate sqlSessionFactory total size: {}", sqlSessionFactoryMap.size());
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        Map<String, PersistenceExceptionTranslator> exceptionTranslatorMap = new HashMap(4);
        sqlSessionFactoryMap.entrySet().stream().forEach((entry) -> {
            exceptionTranslatorMap.put(entry.getKey().toString(), new MyBatisExceptionTranslator((entry.getValue()).getConfiguration().getEnvironment().getDataSource(), true));
        });
        customSqlSessionTemplate.setDefaultDataSourceId(defaultDataSouceId);
        customSqlSessionTemplate.setExceptionTranslatorMap(exceptionTranslatorMap);
        customSqlSessionTemplate.setMultiDataSourceProperties(moreDataSourceProperties);
        return customSqlSessionTemplate;
    }

    @Bean
    @ConditionalOnBean({MoreDataSourceProperties.class})
    public Map<Object, SqlSessionFactory> sqlSessionFactoryMap(Map<String, DataSource> dSourceMap) throws Exception {
        Map<Object, SqlSessionFactory> sessionFactoryMap = new HashMap(4);
        Iterator iterator = dSourceMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, DataSource> entry = (Map.Entry) iterator.next();
            String key = entry.getKey();

            if (!DataSourceConstan.DATA_SOURCE.equals(key)) {
                DataSource value = entry.getValue();
                SqlSessionFactory sqlSessionFactory = this.createSqlSessionFactory(key, value);
                sessionFactoryMap.put(DataSourceUtils.getSqlSessionFactoryName(key), sqlSessionFactory);
            }
        }
        return sessionFactoryMap;
    }

    private SqlSessionFactory createSqlSessionFactory(String dataSourceId, DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = this.sqlSessionFactoryBeanSupplier.get();
        MybatisProperties properties = Binder.get(this.applicationContext.getEnvironment()).bind("mybatis", MybatisProperties.class).orElse(new MybatisProperties());
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        String configLocation = (Arrays.stream(this.moreDataSourceProperties.getDataSources()).filter((x) -> dataSourceId.equals(DataSourceUtils.getDataSourceName(x.getId()))).findFirst().orElse(new DataSourceProperties())).getConfigLocation();
        if (StringUtils.hasText(configLocation)) {
            factory.setConfigLocation(this.resourceLoader.getResource(configLocation));
        } else if (StringUtils.hasText(properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(properties.getConfigLocation()));
        }
        this.function.apply(factory, properties, configLocation, this.configurationCustomizers);

        checkSqlData(factory);


        String[] mybatisPath = (Arrays.stream(this.moreDataSourceProperties.getDataSources()).filter((x) -> dataSourceId.equals(DataSourceUtils.getDataSourceName(x.getId()))).findFirst().orElse(new DataSourceProperties())).getMybatisPath();
        String[] mapperLocations;
        if (mybatisPath != null && mybatisPath.length > 0) {
            mapperLocations = mybatisPath;
        } else if (!ObjectUtils.isEmpty(properties.getMapperLocations())) {
            mapperLocations = properties.getMapperLocations();
        } else {
            mapperLocations = new String[]{"classpath*:/mybatis/**/*.xml", "classpath*:/mapper/**/*.xml"};
        }
        Resource[] resources = ResourceResolver.resolveMapperLocations(mapperLocations);
        factory.setMapperLocations(resources);
        Set<String> factoryPropertyNames = Stream.of((new BeanWrapperImpl(SqlSessionFactoryBean.class)).getPropertyDescriptors()).map(FeatureDescriptor::getName).collect(Collectors.toSet());
        Class<? extends LanguageDriver> defaultLanguageDriver = properties.getDefaultScriptingLanguageDriver();
        if (factoryPropertyNames.contains("scriptingLanguageDrivers") && !ObjectUtils.isEmpty(this.languageDrivers)) {
            factory.setScriptingLanguageDrivers(this.languageDrivers);
            if (defaultLanguageDriver == null && this.languageDrivers.length == 1) {
                defaultLanguageDriver = this.languageDrivers[0].getClass();
            }
        }
        if (factoryPropertyNames.contains("defaultScriptingLanguageDriver")) {
            factory.setDefaultScriptingLanguageDriver(defaultLanguageDriver);
        }
        return factory.getObject();
    }

    @Bean
    @ConditionalOnProperty(
            name = {"jdbc.mapper.enabled"},
            havingValue = "true"
    )
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = this.sqlSessionFactoryBeanSupplier.get();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        this.function.apply(factory, this.properties, "", this.configurationCustomizers);

        //检查数据
        checkData(factory);

        Set<String> factoryPropertyNames = Stream.of((new BeanWrapperImpl(SqlSessionFactoryBean.class)).getPropertyDescriptors()).map(FeatureDescriptor::getName).collect(Collectors.toSet());
        Class<? extends LanguageDriver> defaultLanguageDriver = this.properties.getDefaultScriptingLanguageDriver();
        if (factoryPropertyNames.contains("scriptingLanguageDrivers") && !ObjectUtils.isEmpty(this.languageDrivers)) {
            factory.setScriptingLanguageDrivers(this.languageDrivers);
            if (defaultLanguageDriver == null && this.languageDrivers.length == 1) {
                defaultLanguageDriver = this.languageDrivers[0].getClass();
            }
        }
        if (factoryPropertyNames.contains("defaultScriptingLanguageDriver")) {
            factory.setDefaultScriptingLanguageDriver(defaultLanguageDriver);
        }
        return factory.getObject();
    }

    @FunctionalInterface
    public interface ApplyConfigurationFunction {
        void apply(SqlSessionFactoryBean var1, MybatisProperties var2, String var3, List<ConfigurationCustomizer> var4);
    }

    /**
     * 检查数据
     *
     * @param factory
     */
    private void checkSqlData(SqlSessionFactoryBean factory) {
        if (properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(properties.getTypeHandlersPackage());
        }
        if (StringUtils.hasLength(properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(properties.getTypeAliasesPackage());
        }
        if (!ObjectUtils.isEmpty(this.typeHandlers)) {
            factory.setTypeHandlers(this.typeHandlers);
        }
    }

    /**
     * 检查数据
     *
     * @param factory
     */
    private void checkData(SqlSessionFactoryBean factory) {
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (this.properties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.typeHandlers)) {
            factory.setTypeHandlers(this.typeHandlers);
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }
    }


}
