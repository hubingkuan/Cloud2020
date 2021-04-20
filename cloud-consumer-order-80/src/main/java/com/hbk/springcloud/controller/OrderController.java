package com.hbk.springcloud.controller;

import com.hbk.springcloud.entities.CommonResult;
import com.hbk.springcloud.entities.Payment;
import com.hbk.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author 胡冰宽
 * @date 2021/3/5
 **/
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {
    // 如果是集群的话 服务地址不能写死
    //private static final String PAYMENT_URL="http://localhost:8001";
    // 这里url使用的是在Eureka上注册过的微服务名称调用
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    /*
     *postForObject() ：
     * 参数1：请求的url地址
     * 参数2：传递过去的参数
     * 参数3:返回的对象类型
     * */
    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLb() {
        // 获取到8001  8002
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
/*      获取所有的服务 CLOUD-ORDER-SERVICE  CLOUD-PAYMENT-SERVICE application.yml里面的实例名称
        List<String> services = discoveryClient.getServices();
        System.out.println("服务："+services);*/
        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);

    }
}
