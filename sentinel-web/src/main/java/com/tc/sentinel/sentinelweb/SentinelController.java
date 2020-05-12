package com.tc.sentinel.sentinelweb;

import com.tc.sentinel.SentinelService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @Reference
    SentinelService sentinelService;

    @GetMapping("/say")
    public String sayHello() throws InterruptedException {
        RpcContext.getContext().setAttachment("dubboApplication","sentinel-web");
        return sentinelService.sayHelloWorld("test-sentinel");
    }

    @GetMapping("/say2")
    public String sayHello2() throws InterruptedException {
        return sentinelService.sayHelloWorld("test-default");
    }
}
