package com.zhangds.thread.concurrent;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * 多线程并发统计每行数字
 * Create by zhangds
 * 2020-05-09 14:11
 **/
public class CountDownLatchDemo {

    private static int[][] content = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{9,10,11,12},{9,10,11,12},{9,10,11,12}};

    private static int[] sums;

    public CountDownLatchDemo(int line){
        sums = new int[line];
    }

    public static void main(String[] args) {

        int len = content.length;
        CountDownLatch latch = new CountDownLatch(len);

        CountDownLatchDemo demo = new CountDownLatchDemo(len);
        for (int i=0; i<len; i++){
            final int k = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.countLine(k, latch);

                }

            }).start();

        }

        System.out.println("统计结果数组为" + Arrays.toString(sums));
    }

    public void countLine(int line, CountDownLatch latch){
        int lineTotal = 0;
        for (int j=0; j<content[line].length; j++){
            lineTotal += content[line][j];
        }
        sums[line]  = lineTotal;
        System.out.println(Thread.currentThread().getName() + "统计" + (line+1) + "行结果为" + lineTotal);
        if (latch != null){
            latch.countDown();
        }
        try {
            Thread.sleep(2000);
            latch.await(); //await方法阻塞，直到由于countDown()方法的调用而导致当前计数达到零,释放所有屏障等待的线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
