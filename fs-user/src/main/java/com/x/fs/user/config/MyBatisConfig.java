package com.x.fs.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author x
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.x.fs.mapper"})
public class MyBatisConfig {
}
