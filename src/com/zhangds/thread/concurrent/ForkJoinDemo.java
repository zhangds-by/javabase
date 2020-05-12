package com.zhangds.thread.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 并行计算
 * fork/join框架，大任务分解为独立的小任务，多线程处理并将处理结果合并
 * Create by zhangds
 * 2020-05-11 09:28
 **/
public class ForkJoinDemo extends RecursiveTask<Integer> {

    private int begin;
    private int end;
    private static final int threshold = 2; //分段处理数据个数

    public ForkJoinDemo(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(end - begin <= threshold) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return begin + end;
        }
        int middle = (begin + end)/2;
        ForkJoinDemo d1 = new ForkJoinDemo(begin, middle);
        ForkJoinDemo d2 = new ForkJoinDemo(middle+1, end);

        //执行子任务
        d1.fork();
        d2.fork();

        //得到处理结果并合并
        int a = d1.join();
        int b = d2.join();
        return a+b;
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool(20); //线程数量取决于CPU核数
        ForkJoinDemo demo = new ForkJoinDemo(1, 100);
        Future<Integer> future = pool.submit(demo);

        System.out.println("合并处理结果为：" + future.get());
    }
}
