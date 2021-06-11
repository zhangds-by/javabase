package com.zhangds.thread.concurrent;

import java.util.concurrent.*;

/**
 * Future:
 *      异步计算合并为一个，计算之间相互独立，前一计算依赖后一计算
 *      当Future的完成事件发生时会收到通知，并能使用Future计算的结果进行下一步的操作
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool(); //通过 ExecutorService 向线程提交任务，这里是提交一个Callable任务
        Future<Double> future = executor.submit(new Callable<Double>() {
            public Double call() {
                return doSomeLongComputation(); // 异步执行耗时操作
            }});
        doSomethingElse();
        try {
            Double result = future.get(1, TimeUnit.SECONDS); // 获取异步操作，被阻塞无法获取，1秒钟退出
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
        } catch (TimeoutException te) {
            // 在Future对象完成之前超过已过期
        }
    }

    public static void doSomethingElse(){

    }

    public static Double doSomeLongComputation(){
        return new Double(1d);
    }
}
