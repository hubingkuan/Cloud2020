package com.hbk.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 胡冰宽
 * @date 2021/3/14
 **/
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        return new RandomRule();// 改为随机访问 默认是轮询
    }
}
