package com.hbk.springcloud.controller;

import com.hbk.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡冰宽
 * @date 2021/3/18
 **/
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_ok(id);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_timeout(id);
        return result;
    }

    //====服务熔断
    /*
     * 涉及到断路器的三个重要参数：快照时间窗、请求总数阈值、错误百分比阈值
     *
     * 快照请求窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10s
     *
     * 请求总数阈值：在快照时间窗口内，必须满足请求总数阈值才有资格熔断，默认为20，也就是在10s内请求总数超过20才会有熔断
     * 如果请求总数没有达到20的话，即使所有的请求都超时或其他原因失败，断路器都不会打开。
     *
     * 错误百分比阈值：当请求总数在快照时间窗内超过了阈值，比如发送了30次调用，如果在这30次调用中，
     * 有15次发送了50%的错误百分比，这时候就会将断路器打开。默认设定为50%
     *
     * 当断路器打开后，即使当请求响应为正确的，系统也会调用fallback。一段时间过后（默认5秒），这个时候断路器是半开状态，
     * 会让其中一个请求进行转发，如果成功，断路器会关闭，若失败，继续开启，休眠时间(5s)重新计时
     *
     * */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        return result;
    }
}
