package com.tc.sentinel.sentinelprovider;

import com.tc.sentinel.SentinelService;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**把当前服务发布成dubbo服务*/
@Service
public class SentinelServiceImpl implements SentinelService {

    @Override
    public String sayHelloWorld(String txt) throws InterruptedException {

        TimeUnit.SECONDS.sleep(5);
        System.out.println("begin execute sayHello:" + txt);
        return "Hello World :" + LocalDateTime.now();
    }
}
