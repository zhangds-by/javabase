package com.zhangds.thread.comstomerandproducer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock和 Condition实现生产消费
 * Create by zhangds
 * 2020-05-08 14:41
 **/
public class Market {

    private int count;
    private int MAX_COUNT = 10;
    private Lock lock = new ReentrantLock();

    Condition push = lock.newCondition();
    Condition pop = lock.newCondition();

    public void push() {
        lock.lock();
        while (count >= MAX_COUNT){
            try {
                push.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        pop.signal();
        lock.unlock();
    }

    public void pop() {
        lock.lock();
        while (count <= 0){
            try {
                pop.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        push.signal();
        lock.unlock();
    }
}
