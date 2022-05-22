package com.x.fs.base.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 多数据源事务装配类
 * 控制事务加载的先后顺序,兼容jpa jdbc等spring原生事务加载
 * 事务的bean不可以和数据源在同一阶段加载
 *
 * @author x
 */
@Configuration
@ConditionalOnClass({JdbcTemplate.class, PlatformTransactionManager.class})
@ConditionalOnMissingBean(PlatformTransactionManager.class)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@AutoConfigureBefore(DataSourceTransactionManagerAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
@Import(DataTransactionManagerRegistrarInfo.class)
public class DataTransactionManagerAutoConfig {

}
