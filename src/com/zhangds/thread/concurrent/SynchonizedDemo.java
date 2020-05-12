package com.zhangds.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by zhangds
 * 2020-05-12 09:10
 **/
public class SynchonizedDemo {

    public void func() {
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    //作用于方法
    public synchronized void func1() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    }

    //作用于整个类
    public void func2() {
        synchronized (SynchonizedDemo.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    //修饰静态方法，作用于整个类
    public synchronized static void func3() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        SynchonizedDemo demo = new SynchonizedDemo();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute( () -> demo.func());
    }
}
