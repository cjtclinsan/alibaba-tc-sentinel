package com.tc.sentinel.sentinelweb.thread;

public class Newthread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Newthread1 newthread1 = new Newthread1();
        newthread1.run();
    }
}