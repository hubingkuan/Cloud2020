package com.hbk.springcloud;

import com.hbk.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author 胡冰宽
 * @date 2021/3/5
 **/
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(configuration = MySelfRule.class, name = "CLOUD-PAYMENT-SERVICE")
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}

