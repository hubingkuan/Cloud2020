package com.hbk.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author 胡冰宽
 * @date 2021/3/15
 **/
public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
