package com.x.fs.base.config;

import com.x.fs.base.utils.FsApplicationContext;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author x
 */
@Configuration
@ConditionalOnClass({SqlSessionTemplate.class})
public class MybatisInterceptorAutoConfig {

    public MybatisInterceptorAutoConfig() {
    }

    @Bean
    @ConditionalOnMissingBean({FsApplicationContext.class})
    public FsApplicationContext appContext() {
        return new FsApplicationContext();
    }

    @Bean
    @ConditionalOnMissingBean(
            name = {"databaseIdProvider"}
    )
    VendorDatabaseIdProvider databaseIdProvider() {
        VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("Microsoft SQL Server", "mssql");
        properties.setProperty("Oracle", "oracle");
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }


}
