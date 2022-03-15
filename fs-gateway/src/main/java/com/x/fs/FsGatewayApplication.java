package com.x.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动类
 * @author x
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FsGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FsGatewayApplication.class, args);
    }
}
