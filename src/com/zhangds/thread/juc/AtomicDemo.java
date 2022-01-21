package com.zhangds.thread.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    public static void main(String[] args) {
        AtomicThread thread = new AtomicThread();
        for (int i=0; i<10; i++){
            new Thread(thread).start();
        }
    }
}

class AtomicThread implements Runnable{

    AtomicInteger atomicNum = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getNum());
    }

    public int getNum() {
        return atomicNum.getAndIncrement();
    }
}
