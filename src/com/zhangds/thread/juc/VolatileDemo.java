package com.zhangds.thread.juc;

import com.zhangds.java8.demo.entities.Transaction;

/**
 * 注意：
 *   1. volatile 不具备“互斥性”
 *   2. volatile 不能保证变量的“原子性”
 * @author zhangds
 * @date 2022/1/21 17:34
 */
public class VolatileDemo {

    public static void main(String[] args) {
//        VolatileThread thread = new VolatileThread();
//        new Thread(thread).start();
//
//        while(true){
//            if(thread.isFlag()){ // 内存可见性
//                System.out.println("------------------");
//                break;
//            }
//        }
        VolatileThread2 thread2 = new VolatileThread2();
        for (int i=0; i<100; i++){
            new Thread(thread2).start(); // 传入的线程必须是同一个，局部变量是线程私有
        }
    }
}


class VolatileThread implements Runnable {

    private volatile boolean flag = false;
//    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class VolatileThread2 implements Runnable {

    private volatile int num = 0;
//    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }

        num++;
        System.out.println("num == " + num);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}