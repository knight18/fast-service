package com.x.fs.base.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.x.fs.base.config.MoreDataSourceProperties;
import com.x.fs.base.constant.DataSourceConstan;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import com.x.fs.base.config.MoreDataSourceProperties.DataSourceProperties;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author x
 */
public class MoreDataSourceRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoreDataSourceRegistrar.class);
    private MoreDataSourceProperties moreDataSourceProperties;

    public MoreDataSourceRegistrar() {
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata classMetadata, BeanDefinitionRegistry beanRegistry) {
        if (this.moreDataSourceProperties != null) {
            DataSourceProperties[] dsProperties = this.moreDataSourceProperties.getDataSources();
            for (int i = 0; i < dsProperties.length; ++i) {
                DataSourceProperties dataSource = dsProperties[i];
                Map<String, Object> poolMap = dataSource.getPool();
                String dataSourceId = dataSource.getId();
                if (!poolMap.containsKey(DataSourceConstan.TYPE)) {
                    poolMap.put(DataSourceConstan.TYPE, DruidDataSource.class.getName());
                }
                String dataSourceName = DataSourceUtils.getDataSourceName(dataSourceId);
                BeanDefinition datasourceBeanDefinition;
                try {
                    datasourceBeanDefinition = this.createDatasourceBeanDefinition(poolMap);
                } catch (ClassNotFoundException e) {
                    LOGGER.error("register data source bean definition failed, data source type class not found: {}", poolMap.get(DataSourceConstan.TYPE));
                    throw new BeanDefinitionValidationException("data source register failed", e);
                }
                beanRegistry.registerBeanDefinition(dataSourceName, datasourceBeanDefinition);
                if (dataSourceId.equalsIgnoreCase(this.moreDataSourceProperties.getDefaultDataSourceId())) {
                    beanRegistry.registerAlias(dataSourceName, DataSourceConstan.DATA_SOURCE);
                }
            }
        }
    }

    private BeanDefinition createDatasourceBeanDefinition(Map<String, Object> properties) throws ClassNotFoundException {
        String typeStr;
        if (!properties.containsKey(DataSourceConstan.DRIVER_CLASS_NAME) && properties.containsKey(DataSourceConstan.URL)) {
            typeStr = (String) properties.get(DataSourceConstan.URL);
            String driverClass = DatabaseDriver.fromJdbcUrl(typeStr).getDriverClassName();
            properties.put(DataSourceConstan.DRIVER_CLASS_NAME, driverClass);
        }
        properties.computeIfAbsent("user", (key) -> properties.get("username"));
        properties.computeIfAbsent("jdbc-url", (key) -> properties.get(DataSourceConstan.URL));
        properties.computeIfAbsent("jdbcUrl", (key) -> properties.get(DataSourceConstan.URL));
        typeStr = (String) properties.get(DataSourceConstan.TYPE);
        Class<?> type = ClassUtils.forName(typeStr, this.getClass().getClassLoader());
        Assert.isAssignable(DataSource.class, type);
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(type);
        if (type == DruidDataSource.class) {
            definitionBuilder.setInitMethodName("init");
        }
        AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
        properties.forEach((property, value) -> {
            PropertyValue propertyValue = new PropertyValue(property, value);
            propertyValue.setOptional(true);
            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
        });
        return beanDefinition;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.moreDataSourceProperties = Binder.get(environment).bind("jdbc", Bindable.of(MoreDataSourceProperties.class)).orElse(null);
    }
}
