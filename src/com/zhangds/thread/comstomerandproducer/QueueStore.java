package com.zhangds.thread.comstomerandproducer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Create by zhangds
 * 2020-05-11 10:45
 **/
public class QueueStore {
    private int MAX_COUNT = 10;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(MAX_COUNT);

    public void push(){
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void take(){
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
