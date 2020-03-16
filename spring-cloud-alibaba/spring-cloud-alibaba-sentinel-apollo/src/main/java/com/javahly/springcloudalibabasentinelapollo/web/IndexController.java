package com.javahly.springcloudalibabasentinelapollo.web;

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
    @SentinelResource(value = "getIndexConsole",blockHandler = "getOrderQpsEx")
    @RequestMapping("/getIndexConsole")
    public String getIndexConsole() {
        return "getIndexConsole";
    }

    public String getOrderQpsEx(BlockException e) {
        return "接口已经被限流";
    }

}
