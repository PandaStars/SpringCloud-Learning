package com.study.springcloud.payment.controller;

import com.study.springcloud.commons.entities.CommonResult;
import com.study.springcloud.commons.entities.Payment;
import com.study.springcloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Desc
 * @Author lgl
 * @Date 2021/3/20
 **/
@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    /**
     * 插入记录
     * @param payment
     * @return
     */
    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：{}", result);
        if (result > 0) {  //成功
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：{}", payment);
        if (payment != null) {  //说明有数据，能查询成功
            return new CommonResult(200, "查询成功, port:" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID：" + id + " port:" + serverPort, null);
        }
    }
}

