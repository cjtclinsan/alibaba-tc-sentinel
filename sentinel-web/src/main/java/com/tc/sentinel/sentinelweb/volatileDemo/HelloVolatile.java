package com.tc.sentinel.sentinelweb.volatileDemo;

import java.util.concurrent.TimeUnit;

public class HelloVolatile {
    volatile boolean running = true;

    void m(){
        System.out.println("m start");
        while (running){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        HelloVolatile t = new HelloVolatile();

        new Thread(t::m,  "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }
}