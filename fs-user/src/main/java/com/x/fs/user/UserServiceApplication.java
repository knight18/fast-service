package com.x.fs.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy(proxyTargetClass = true)
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
//@EnableScheduling//// 开启定时任务 20220315
@EnableTransactionManagement
@EnableDiscoveryClient

@ComponentScan("com.x.fs.**")
//@Configuration
@MapperScan(value = {"com.x.fs.**.dao","com.x.fs.**.mapper"})
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


}
