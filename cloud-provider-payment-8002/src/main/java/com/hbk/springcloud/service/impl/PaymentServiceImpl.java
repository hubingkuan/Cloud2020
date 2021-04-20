package com.hbk.springcloud.service.impl;


import com.hbk.springcloud.dao.PaymentDao;
import com.hbk.springcloud.entities.Payment;
import com.hbk.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 胡冰宽
 * @date 2021/3/5
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
