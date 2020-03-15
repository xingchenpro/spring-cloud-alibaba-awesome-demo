package com.javahly.springcloudalibabasentinel.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :hly
 * @github :https://github.com/huangliangyun
 * @blog :http://www.javahly.com/
 * @CSDN :blog.csdn.net/Sirius_hly
 * @date :2020/3/15
 * @QQ :1136513099
 * @desc :
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 如果没有引用 @SentinelResource 注解,限流名称就是接口名称
     * fallback 服务降级(超时，异常)执行方法
     * blockHandler 限流/熔断执行方法
     * @return
     */
    @SentinelResource(value = "getIndexConsole",blockHandler = "getOrderQpsEx")
    @RequestMapping("/getIndexConsole")
    public String getIndexConsole() {
        return "getIndexConsole";
    }

    /**
     * RT：如果接口执行时间超过10秒，触发熔断
     * 时间窗口：在几秒内不能访问
     * @return
     */
    @SentinelResource(value = "getOrderConsole",fallback = "getFallBackEx")
    @RequestMapping("/getOrderConsole")
    public String getOrderConsole() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "getOrderConsole";
    }

    public String getOrderQpsEx(BlockException e) {
        return "接口已经被限流";
    }

    public String getFallBackEx() {
        return "接口已经被熔断";
    }
}
