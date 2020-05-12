package com.zhangds.thread;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.concurrent.*;

/**
 * Create by zhangds
 * 2020-05-11 11:01
 **/
public class ThreadPoolDemo {
    public static void main(String[] args) {

        //第一种创建方式
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10,50,10, TimeUnit.DAYS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        pool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        pool.shutdown(); //关闭线程池，并执行阻塞队列中的线程

        pool.shutdownNow(); //立即关闭线程池，不执行阻塞中的线程

        //第二种
        Executors.newCachedThreadPool(); //一个任务创建一个线程
        Executors.newSingleThreadExecutor(); // 固定大小为1
        ExecutorService service = Executors.newFixedThreadPool(10); //固定大小
        service.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        //lambda, 相当于内部匿名内部线程
        service.execute( () -> {

        });

        Future<?> submit = service.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        //中断池中的一个线程
        submit.cancel(true);

        ScheduledExecutorService service2 = Executors.newScheduledThreadPool(10);
        service2.schedule(new Runnable() {
            @Override
            public void run() {

            }
        }, 5, TimeUnit.SECONDS);




        service.shutdown();
        service2.shutdown();
    }



}
