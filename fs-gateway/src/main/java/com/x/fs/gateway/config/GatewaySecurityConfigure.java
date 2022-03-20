package com.x.fs.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * postman请求 报 CSRF Token has been associated to this client
 *  在网上搜索的结果：
 *  https://blog.csdn.net/ckclh1314/article/details/122615773
 *
 * @author x
 */
@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfigure {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        //配置白名单和访问规则，CommonEnum枚举类
        http.csrf().disable();
        return http.build();
    }

}