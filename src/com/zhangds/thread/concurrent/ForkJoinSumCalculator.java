package com.zhangds.thread.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    public static void main(String[] args) {
        System.out.println(forkJoinSum(10_000_000));
    }

    // 子任务 求和数组 起始位置 终止位置
    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000; // 分解子任务数组大小

    public ForkJoinSumCalculator(long[] numbers) { //创建主任务
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) { //递归
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        // 创建一个子任务为数组的前一半求和
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
        leftTask.fork();    //利用另一个 ForkJoinPool 线程异步执行新创建的子任务
        //创建子任务后一半数组求和
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);
        // 同步执行第二个子任务，可能继续递归划分
        Long rightResult = rightTask.compute();
        //读取第一个任务的结果，未完成等待
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
}
