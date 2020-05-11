package com.tc.sentinel.sentinelweb.thread;

import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();

        //休眠1s，保证i赋值成功
        TimeUnit.SECONDS.sleep(1);


        thread.interrupt();

        while (thread.isAlive()){
            //确保线程已经终止
        }
        thread.print();
    }
}