package com.tc.sentinel.sentinelweb.thread;

public class NewThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new NewThread2());
        thread.start();
    }
}