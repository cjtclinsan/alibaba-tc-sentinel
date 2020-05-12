package com.tc.sentinel.sentinelprovider;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;

import java.util.ArrayList;
import java.util.List;

public class NacosDataSourceHystrixFunc implements InitFunc {
    @Override
    public void init() throws Exception {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("com.tc.sentinel.SentinelService");

        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setCount(10);
        rule.setTimeWindow(5);
        rules.add(rule);

        DegradeRuleManager.loadRules(rules);
    }
}