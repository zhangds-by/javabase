package com.zhangds.java8.demo.smallcase;


import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流：
 *      在于Stream在内部分成了几块。因此可以对不同的块独立并行进行归纳操作
 *      同一个归纳操作会将各个子流的部分归纳结果合并起来
 */
public class ParallelDemo {

    public static void main(String[] args) {
        Long startTime = System.nanoTime();

        sequentialSum(1_000_000);

//        parallelSum(1_000_000);
        long duration = (System.nanoTime() - startTime) / 1_000_000;

        System.out.println("耗费时间" + duration);

    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1) //生成自然数无限流，生成的是装箱对象，求和时需要拆箱
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /**
     * 并行流内部使用了默认的ForkJoinPool（分支/合并框架）
     *      获取：Runtime.getRuntime().available-Processors()
     *      修改系统属性，改变线程池大小：System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * LongStream.rangeClosed 产生原始类型的long数字，没有装箱拆箱的开销
     * LongStream.rangeClosed会生成数字范围，很容易拆分为独立的小块
     * @param n
     * @return
     */
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    /**
     * 选择正确的数据结构进行并行操作
     * 并行化的代价：
     *  对流递归划分
     *  每个子流的归纳操作分配到不同的线程
     *  把这些操作的结果合并成一个值
     * @param n
     * @return
     */
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
