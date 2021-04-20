package com.hbk.springcloud.service;

import com.hbk.springcloud.entities.CommonResult;
import com.hbk.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 胡冰宽
 * @date 2021/3/17
 **/
@Component
// 注解来指定提供者的名字
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    // 这里需要使用的是提供者那端的请求相对路径 这里相当于映射
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
