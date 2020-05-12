package com.zhangds.thread.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。
 *
 * 通过维护计数器来实现的。线程执行 await() 方法
 * 之后计数器会减 1，并进行等待，直到计数器为 0，所有调用 awati() 方法而在等待
 * 的线程才能继续执行。
 *
 *
 * Create by zhangds
 * 2020-05-09 16:26
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrierDemo demo = new CyclicBarrierDemo();

        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("开始开会");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.meeting(barrier);
                }
            }).start();
        }


    }

    public void meeting(CyclicBarrier barrier){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 到达会议室，等待开会..");

        if(Thread.currentThread().getName().equals("Thread-0")) {
            System.out.println("当前等待屏障的同事" + barrier.getNumberWaiting());
            barrier.reset(); //被中断，初始化：唤醒等待的任务，重置线程数，循环使用
        }

        try {
            barrier.await(); //在此屏障上调用await()等待，计数器减1，直到所有线程到达屏障
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
