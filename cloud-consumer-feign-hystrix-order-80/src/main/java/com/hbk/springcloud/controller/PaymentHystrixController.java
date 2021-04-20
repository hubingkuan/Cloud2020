package com.hbk.springcloud.controller;

import com.hbk.springcloud.service.paymentHYSTRIXService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡冰宽
 * @date 2021/3/18
 **/
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentHystrixController {
    @Autowired
    private paymentHYSTRIXService paymentService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    // 这里如果加上@HystrixCommand 系统超时或者出异常的话 返回全局fallback方法
    // 如果不加上@HystrixCommand 那么返回PaymentFallBackService中的paymentInfo_ok()方法
    // 这样的话做到了解耦合
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_ok(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    // 特别定制的fallback
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
//@HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        String result = paymentService.paymentInfo_timeout(id);
        return result;
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }


}
