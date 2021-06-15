package com.stardust.cloudalibabasentinel.web;

import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InterfaceExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ParamFlowException.class)
    public String businessInterfaceException(ParamFlowException e) {
        return "您当前访问的频率过高，请稍后重试!";
    }
}
