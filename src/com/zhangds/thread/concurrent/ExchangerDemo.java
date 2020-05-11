package com.zhangds.thread.concurrent;

import java.util.concurrent.Exchanger;

/**
 * 两个线程达到同步点交换数据
 * Create by zhangds
 * 2020-05-09 17:51
 **/
public class ExchangerDemo {
    public static void main(String[] args) {
        ExchangerDemo demo = new ExchangerDemo();
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.getA(exchanger);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.getAndCompare(exchanger);
            }
        }).start();
    }

    public void getA(Exchanger<String> exchanger){

        //模拟处理时间
        try {
            System.out.println("a 线程正在抓取数据...");
            Thread.sleep(2000);
            System.out.println("a 线程抓取到数据...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //模拟处理结果
        String res = "抓取的数据";

        //对结果的操作
        try {
            exchanger.exchange(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getAndCompare(Exchanger<String> exchanger){

        try {
            System.out.println("b 线程正在抓取数据...");
            Thread.sleep(2000);
            System.out.println("b 线程抓取到数据...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String res = "抓取的数据";

        try {
            String result = exchanger.exchange(res);
            System.out.println("对比结果" + res.equals(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
