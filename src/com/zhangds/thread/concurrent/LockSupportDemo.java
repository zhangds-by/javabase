package com.zhangds.thread.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            LockSupport.park();
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);
    }
}
