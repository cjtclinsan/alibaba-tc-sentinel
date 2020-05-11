package com.tc.sentinel.sentinelweb.thread;

public class FlagThreadDemo {
    public volatile static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                while (flag){
                    System.out.println("运行中");
                    Thread.sleep(1000);
                }
            } catch ( InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        // 3秒之后，将状态标志改为 false，代表不继续运行
        Thread.sleep(3000);
        flag = false;
        System.out.println("程序运行结束");
    }
}