package com.zhangds.thread.threadlocal;

/**
 * Create by zhangds
 * 2020-05-08 16:33
 **/
public class ThreadLocalDemo {

    private ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return new Integer(0);
        }
    };

    public int getNext(){
        Integer value = count.get();
        value++;
        count.set(value);
        return value;
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++){
                    System.out.println(Thread.currentThread() + " ----- " + demo.getNext());
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++){
                    System.out.println(Thread.currentThread() + " ----- " + demo.getNext());
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
