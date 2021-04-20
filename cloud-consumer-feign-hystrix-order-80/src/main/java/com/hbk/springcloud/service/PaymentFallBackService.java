package com.hbk.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author 胡冰宽
 * @date 2021/4/14
 **/
@Component
public class PaymentFallBackService implements paymentHYSTRIXService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
