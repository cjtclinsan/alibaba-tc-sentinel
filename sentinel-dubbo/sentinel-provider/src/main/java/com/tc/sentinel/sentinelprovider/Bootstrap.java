package com.tc.sentinel.sentinelprovider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Bootstrap {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DubboConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).start();
        System.in.read();
    }
}