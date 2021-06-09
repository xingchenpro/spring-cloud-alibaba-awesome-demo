package com.stardust.cloudconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author :hly
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2021/6/8
 * @desc :
 */
@FeignClient(value = "producer-service")
public interface OpenFeignClient {

    @GetMapping("/index")
    String openFeignTest();

}
