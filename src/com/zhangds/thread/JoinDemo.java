package com.zhangds.thread;

/**
 * Create by zhangds
 * 2020-05-12 09:23
 **/
public class JoinDemo extends Thread {

    public static class A extends Thread{
        @Override
        public void run() {
            System.out.println("A线程runing...");
        }
    }

    private A a;

    JoinDemo(A a){
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("JoinDemo线程running....");
    }

    public static void main(String[] args) {
        A a = new A();
        new JoinDemo(a).start();
        a.start();
    }
}
