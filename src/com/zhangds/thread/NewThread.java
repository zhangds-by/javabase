package com.zhangds.thread;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 创建线程的方式
 * Create by zhangds
 * 2020-05-08 09:22
 **/
public class NewThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1、继承Thread，重写run()
        new Thread(new ThreadDemo()).start();

        //2、实现Runnable，重写run()
        new Thread(new RunnableDemo()).start();

        //3、静态内部类
        new Thread(new Runnable() { //相当于implements
            @Override
            public void run() {
                System.out.println("parent");
            }
        }) {
            @Override
            public void run() { //相当于Thread子类
                System.out.println("sub");
            }
        }.start();

        //4、带返回值的
        FutureTask<Integer> task = new FutureTask<>(new CallableDemo());
        new Thread(task).start();
        System.out.println(task.get());

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> futureTask = service.submit(new CallableDemo());
        System.out.println(futureTask.get());

        //5、定时器
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 0, 1000);

        //6、线程池实现
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new RunnableDemo());

        //7、lamba表达式，并行流实现
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.parallelStream().mapToInt(i -> i*2).sum();

    }

    public static class ThreadDemo extends Thread {
        @Override
        public void run() {
        }
    }

    public static class RunnableDemo implements Runnable {

        @Override
        public void run() {

        }
    }

    public static class CallableDemo implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return null;
        }
    }
}
