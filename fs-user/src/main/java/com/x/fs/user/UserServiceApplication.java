package com.x.fs.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableFeignClients
//@EnableDiscoveryClient

@EnableAspectJAutoProxy(proxyTargetClass = true)
////@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
////@EnableScheduling//// 开启定时任务 20210615
@EnableTransactionManagement
@EnableDiscoveryClient
@Configuration

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


}
