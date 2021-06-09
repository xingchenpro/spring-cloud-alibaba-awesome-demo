package com.stardust.cloudalibabasentinel.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @SentinelResource(value = "testLimit",blockHandler = "testLimitHandler")
    @RequestMapping("/testLimit")
    public String testLimit() {
        return "接口正常访问";
    }

    public String testLimitHandler(BlockException e) {
        return "接口已经被限流";
    }

    @SentinelResource(value = "testFallBack",fallback = "testFallBackHandler")
    @RequestMapping("/testFallBack")
    public String testFallBack() throws Exception {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        //throw new Exception("xxx");
        return "接口正常访问";
    }

    public String testFallBackHandler() {
        return "接口已经被熔断降级";
    }


}
