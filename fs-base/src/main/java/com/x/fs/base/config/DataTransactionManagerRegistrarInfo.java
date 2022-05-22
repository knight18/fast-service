package com.x.fs.base.config;

import com.x.fs.base.constant.DataSourceConstan;
import com.x.fs.base.datasource.utils.DataSourceDescUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author x
 */
public class DataTransactionManagerRegistrarInfo implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private MoreDataSourceProperties moreDataSourceProperties;

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        moreDataSourceProperties = Binder.get(environment).bind(DataSourceConstan.DATA_PROPERTIES_PREFIX, MoreDataSourceProperties.class)
                .orElse(null);
    }

    /**
     * Register bean definitions as necessary based on the given annotation metadata of
     * the importing {@code @Configuration} class.
     * <p>Note that {@link BeanDefinitionRegistryPostProcessor} types may <em>not</em> be
     * registered here, due to lifecycle constraints related to {@code @Configuration}
     * class processing.
     * <p>The default implementation delegates to
     * {@link #registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)}.
     *
     * @param importingClassMetadata  annotation metadata of the importing class
     * @param registry                current bean definition registry
     * @param importBeanNameGenerator the bean name generator strategy for imported beans:
     *                                {@link ConfigurationClassPostProcessor#IMPORT_BEAN_NAME_GENERATOR} by default, or a
     *                                user-provided one if {@link ConfigurationClassPostProcessor#setBeanNameGenerator}
     *                                has been set. In the latter case, the passed-in strategy will be the same used for
     *                                component scanning in the containing application context (otherwise, the default
     *                                component-scan naming strategy is {@link AnnotationBeanNameGenerator#INSTANCE}).
     * @see ConfigurationClassPostProcessor#IMPORT_BEAN_NAME_GENERATOR
     * @see ConfigurationClassPostProcessor#setBeanNameGenerator
     * @since 5.2
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        if (null == moreDataSourceProperties) {
            return;
        }
        for (MoreDataSourceProperties.DataSourceProperties dataSource : moreDataSourceProperties.getDataSources()) {
            String dataSourceId = dataSource.getId();
            String dataSourceName = DataSourceDescUtils.getDataSourceName(dataSourceId);
            String transManagerName = DataSourceDescUtils.getTransManagerName(dataSourceId);
            //事务bean definition
            BeanDefinitionBuilder transBeanDefinitionBuilder =
                    BeanDefinitionBuilder.rootBeanDefinition(DataSourceTransactionManager.class);
            transBeanDefinitionBuilder.addConstructorArgReference(dataSourceName);
            if (dataSourceId.equalsIgnoreCase(moreDataSourceProperties.getDefaultDataSourceId())) {
                registry.registerAlias(transManagerName, "dataTransactionManager");
                transBeanDefinitionBuilder.setPrimary(true);
            }
            BeanDefinition transBeanDefinition = transBeanDefinitionBuilder.getBeanDefinition();
            registry.registerBeanDefinition(transManagerName, transBeanDefinition);
        }
    }

    /**
     * Register bean definitions as necessary based on the given annotation metadata of
     * the importing {@code @Configuration} class.
     * <p>Note that {@link BeanDefinitionRegistryPostProcessor} types may <em>not</em> be
     * registered here, due to lifecycle constraints related to {@code @Configuration}
     * class processing.
     * <p>The default implementation is empty.
     *
     * @param importingClassMetadata annotation metadata of the importing class
     * @param registry               current bean definition registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry);
    }
}
