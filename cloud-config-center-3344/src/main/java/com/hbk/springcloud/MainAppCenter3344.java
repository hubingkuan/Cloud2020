package com.hbk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Description TODO
 * @Author 麻瓜胡
 * @Date 2021/4/21
 */
@EnableConfigServer
@SpringBootApplication
public class MainAppCenter3344 {
    public static void main(String[] args) {
        SpringApplication.run(MainAppCenter3344.class, args);
    }
}
