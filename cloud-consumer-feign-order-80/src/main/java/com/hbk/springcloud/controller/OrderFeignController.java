package com.hbk.springcloud.controller;

import com.hbk.springcloud.entities.CommonResult;
import com.hbk.springcloud.entities.Payment;
import com.hbk.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡冰宽
 * @date 2021/3/17
 **/
@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/comsumer/payment/feign/timeout")
    public String timeout() {
        /*
         * 默认Feign客户端只等待一秒钟，但是服务端处理超过了1秒钟，导致Feign
         * 客户端不想等待了，直接返回报错
         * 为了避免这样的情况，有时候我们需要设置Feign客户端的超时控制
         * 在yml中开启配置
         * */
        return paymentFeignService.paymentFeignTimeout();
    }

}
