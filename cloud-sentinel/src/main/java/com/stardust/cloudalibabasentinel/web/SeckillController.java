package com.stardust.cloudalibabasentinel.web;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

//@RestController
public class SeckillController {

    public SeckillController(){
        initSeckillRule();
    }

    private static final String SEKILL_RULE = "seckill";

    //@RequestMapping("/seckill")
    public String seckill(Long userId, Long orderId) {
        try {
            Entry entry = SphU.entry(SEKILL_RULE, EntryType.IN, 1, userId);
            return "秒杀成功";
        } catch (Exception e) {
            return "当前用户访问次数过度频繁，请稍后重试!";
        }
    }


    private void initSeckillRule() {
        ParamFlowRule rule = new ParamFlowRule(SEKILL_RULE)
                // 对我们秒杀接口第0个参数实现限流
                .setParamIdx(0)
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                // 每秒QPS最多只有1s
                .setCount(1);
        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));

    }
}
