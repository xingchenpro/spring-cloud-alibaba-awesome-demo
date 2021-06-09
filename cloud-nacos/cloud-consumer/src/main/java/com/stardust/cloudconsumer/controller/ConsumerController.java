package com.stardust.cloudconsumer.controller;

import com.stardust.cloudconsumer.feign.OpenFeignClient;
import com.stardust.cloudconsumer.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author :hly
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2021/6/7
 * @desc :
 */
@RefreshScope
@RestController
public class ConsumerController {

    @Value("${stardust.username}")
    private String userName;

    @Autowired
    private IConsumerService consumerService;

    @Resource
    private OpenFeignClient openFeignClient;


    @GetMapping("/test1")
    public String test1() {
        return consumerService.consumer1();
    }


    @GetMapping("/test2")
    public String test2() {
        return openFeignClient.openFeignTest();
    }

    @GetMapping("/test3")
    public String test3() {
        return userName;
    }


}
