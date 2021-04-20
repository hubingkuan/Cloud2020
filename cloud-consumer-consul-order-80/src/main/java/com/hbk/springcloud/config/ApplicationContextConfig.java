package com.hbk.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 胡冰宽
 * @date 2021/3/5
 **/
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced// 赋予了restTemple负载均衡的能力
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
