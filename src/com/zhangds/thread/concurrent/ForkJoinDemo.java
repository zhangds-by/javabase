package com.zhangds.thread.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 并行计算(条件拆分 -> 并行求值 -> 结果合并)
 * fork/join框架，大任务分解为独立的小任务，多线程处理并将处理结果合并
 * 以递归的方式将并行的任务分成更小的任务，然后将每个子任务的结果合并起来生成整体结果
 *
 * ExecutorService接口的一个实现，把子任务分配给线程池张
 *
 * RecursiveTask<R> 并行化任务（以及所有子任务）产生的结果类型
 * 任务不返回结果，则是RecursiveAction类型
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

    /**
     * 定义了将任务拆分成子任务的逻辑
     * 以及无法再拆分或不方便再拆分时，生成单个子任务结果的逻辑
     * if (任务足够小或不可分) {
     *      顺序计算该任务
     * } else {
             将任务分成两个子任务
     *       递归调用本方法，拆分每个子任务，等待所有子任务完成
     *       合并每个子任务的结果
     * }
     * @return
     */
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
