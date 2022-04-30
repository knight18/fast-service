package com.x.fs.base.mybatis;

import com.x.fs.base.annotation.ConditionalOnMoreDataSourceEnabled;
import com.x.fs.base.config.MoreDataSourceAutoConfig;
import com.x.fs.base.config.MybatisAutoConfig;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.function.Supplier;

/**
 * 数据库配置文件
 * @author x
 */
@ConditionalOnMoreDataSourceEnabled
@ConditionalOnProperty(
        name = {"jdbc.mapper.enabled"},
        havingValue = "true",
        matchIfMissing = true
)
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class, MybatisProperties.class})
@EnableConfigurationProperties({MybatisProperties.class})
@AutoConfigureAfter({MoreDataSourceAutoConfig.class, MybatisLanguageDriverAutoConfiguration.class})
@AutoConfigureBefore({MybatisAutoConfiguration.class})
@Import({MybatisAutoConfig.class})
public class MoreDataSourceMybatisAutoConfig {
    public MoreDataSourceMybatisAutoConfig() {
    }

    @Bean
    public Supplier<SqlSessionFactoryBean> sqlSessionFactoryBeanSupplier() {
        return () -> new SqlSessionFactoryBean();
    }

    @Bean
    public MybatisAutoConfig.ApplyConfigurationFunction applyConfigurationFunction() {
        return (factory, properties, configLocation, configurationCustomizers) -> {
            Configuration configuration = properties.getConfiguration();
            if (configuration == null && !StringUtils.hasText(configLocation) && !StringUtils.hasText(properties.getConfigLocation())) {
                configuration = new Configuration();
            }
            if (configuration != null && !CollectionUtils.isEmpty(configurationCustomizers)) {
                Iterator iterator = configurationCustomizers.iterator();
                while(iterator.hasNext()) {
                    ConfigurationCustomizer customizer = (ConfigurationCustomizer)iterator.next();
                    customizer.customize(configuration);
                }
            }
            factory.setConfiguration(configuration);
        };
    }
}
