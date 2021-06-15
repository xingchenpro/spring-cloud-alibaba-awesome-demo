package com.stardust.cloudalibabasentinel.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    /**
     * 限流测试
     * blockHandler：限流出现异常执行的方法
     * @return
     */
    @SentinelResource(value = "testLimit",blockHandler = "testLimitHandler")
    @RequestMapping("/testLimit")
    public String testLimit() {
        return "接口正常访问";
    }

    public String testLimitHandler(BlockException e) {
        return "接口已经被限流";
    }


    /**
     * 熔断降级测试
     * fallback：降级执行的方法
     * @throws Exception
     */
    @SentinelResource(value = "testFallBack",fallback = "testFallBackHandler")
    @RequestMapping("/testFallBack")
    public String testFallBack() throws Exception {
        try {
            Thread.sleep(200);
        } catch (Exception e) {

        }
        return "接口正常访问";
    }

    public String testFallBackHandler() {
        return "接口已经被熔断降级";
    }


    @RequestMapping("/seckill")
    @SentinelResource(value ="seckill", fallback = "seckillFallback", blockHandler = "seckillBlockHandler")
    public String seckill(Long userId, Long orderId) {
        return "秒杀成功";
    }


}
