package com.zhangds.thread.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 一个线程等待其他线程各自执行完毕后再执行。
 * 通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，
 * 当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
 * @author zhangds
 * @date 2022/1/21 18:01
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        // 50 7360
        // 100 13583 等待100个线程完成才输出耗时时间
        final CountDownLatch latch = new CountDownLatch(100); // 初始化计数器

        CountDownLatchThread td = new CountDownLatchThread(latch);

        long start = System.currentTimeMillis();

        for (int i=0; i<100; i++){
            new Thread(td).start();
        }
        try {
            latch.await(); //等待计数器为0，否则进行执行
        } catch (InterruptedException e) {
        }

        long end = System.currentTimeMillis();

        System.out.println("耗费时间为：" + (end - start));

    }
}

class CountDownLatchThread implements Runnable{

    private CountDownLatch latch;

    public CountDownLatchThread(CountDownLatch latch) {
        this.latch = latch;
    }

    public CountDownLatchThread() {
    }

    @Override
    public void run() {
        try{
            for (int i = 0; i < 50000; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        }finally {
            latch.countDown(); // 计数器-1
        }


    }
}
