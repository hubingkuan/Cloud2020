package com.hbk.springcloud.controller;

import com.hbk.springcloud.entities.CommonResult;
import com.hbk.springcloud.entities.Payment;
import com.hbk.springcloud.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 胡冰宽
 * @date 2021/3/5
 **/
@Api("订单接口")
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @ApiOperation("插入订单信息")
    @PostMapping(value = "/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        System.out.println(payment);
        int result = paymentService.create(payment);
        log.info("插入结果:" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @ApiOperation("查询订单信息")
    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录,查询ID:" + id, null);
        }
    }

    @GetMapping("/lb")
    public String getLb() {
        return serverPort;
    }

}
