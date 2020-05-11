package com.tc.sentinel.sentinelweb.thread;

import java.util.concurrent.TimeUnit;

public class StopThread extends Thread{
    private int i = 0;
    private int j = 0;

    @Override
    public void run(){
        synchronized (this){
            //增加同步锁，确保线程安全
            ++i;
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ++j;
        }
    }

    public void print(){
        System.out.println(i+"   "+j);
    }
}