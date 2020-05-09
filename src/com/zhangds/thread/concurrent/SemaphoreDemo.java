package com.zhangds.thread.concurrent;

import java.util.concurrent.Semaphore;

/**
 * Create by zhangds
 * 2020-05-09 17:24
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo demo = new SemaphoreDemo();
        Semaphore semaphore = new Semaphore(10);

        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.method(semaphore);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void method(Semaphore semaphore){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "is running =====" + semaphore.availablePermits());

        semaphore.release();
    }
}
