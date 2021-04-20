package com.hbk.springcloud.service;

import com.hbk.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author 胡冰宽
 * @date 2021/3/5
 **/
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);


}
