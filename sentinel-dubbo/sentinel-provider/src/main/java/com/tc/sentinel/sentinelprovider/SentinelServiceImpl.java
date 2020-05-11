package com.tc.sentinel.sentinelprovider;

import com.tc.sentinel.SentinelService;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;

/**把当前服务发布成dubbo服务*/
@Service
public class SentinelServiceImpl implements SentinelService {

    @Override
    public String sayHelloWorld(String txt) {
        System.out.println("begin execute sayHello:" + txt);
        return "Hello World :" + LocalDateTime.now();
    }
}
