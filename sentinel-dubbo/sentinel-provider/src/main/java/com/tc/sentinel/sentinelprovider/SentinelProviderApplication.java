package com.tc.sentinel.sentinelprovider;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
public class SentinelProviderApplication {

    public static void main(String[] args) throws IOException {
        //表示当前的节点是集群客户端
//        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
        //初始化限流规则
        initFlowRole();
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DubboConfig.class);
//        ((AnnotationConfigApplicationContext) applicationContext).start();
        SpringApplication.run(SentinelProviderApplication.class, args);
        System.in.read();
    }

    //限流规则
    private static void initFlowRole(){
        FlowRule flowRule = new FlowRule();
        //针对具体方法限流
        flowRule.setResource("com.tc.sentinel.SentinelService:sayHelloWorld(java.lang.String)");
        //限流阈值 qps=10
        flowRule.setCount(5);
        //限流阈值类型（QPS或者并发线程数）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //流控针对的调用来源，若为 default 则不区分调 用来源
        flowRule.setLimitApp("sentinel-web");
        //流量控制手段（直接拒绝、Warm Up、匀速排队）
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }
}
