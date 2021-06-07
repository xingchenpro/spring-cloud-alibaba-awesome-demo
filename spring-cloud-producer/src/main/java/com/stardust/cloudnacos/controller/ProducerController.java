package com.stardust.cloudnacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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

}
