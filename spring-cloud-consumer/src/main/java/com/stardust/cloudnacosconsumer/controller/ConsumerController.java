package com.stardust.cloudnacosconsumer.controller;

import com.stardust.cloudnacosconsumer.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2021/6/7
 * @desc :
 */
@RestController
public class ConsumerController {


    @Autowired
    private IConsumerService consumerService;

    @GetMapping("/test")
    public String consumer() {
        return consumerService.consumer();
    }
}
