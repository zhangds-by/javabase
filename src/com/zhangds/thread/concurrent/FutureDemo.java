package com.zhangds.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Runnable 线程调用，run()异步处理
 * Callable的call方法，不是异步执行的，是由Future的run方法调用的
 * Create by zhangds
 * 2020-05-11 09:19
 **/
public class FutureDemo {


    public static void main(String[] args) throws Exception {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });

        new Thread(task).start();

        Integer result = task.get();

    }

}
