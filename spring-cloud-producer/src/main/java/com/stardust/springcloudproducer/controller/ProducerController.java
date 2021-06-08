package com.stardust.springcloudproducer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2021/6/7
 * @desc :
 */
@RestController
public class ProducerController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.cloud.client.ip-address}")
    private String ip;


    @GetMapping("/index")
    public String index() {
        return "ip:" + ip + ",port:" + port;
    }

    @SentinelResource(value = "testLimit",blockHandler = "testLimitEx")
    @RequestMapping("/testLimit")
    public String testLimit() {
        return "testLimit";
    }

    public String testLimitEx(BlockException e) {
        return "接口已经被限流";
    }


    @SentinelResource(value = "testFallBack",fallback = "testFallBackEx")
    @RequestMapping("/testFallBack")
    public String testFallBack() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testFallBack";
    }

    public String testFallBackEx() {
        return "接口已经被熔断";
    }


}
