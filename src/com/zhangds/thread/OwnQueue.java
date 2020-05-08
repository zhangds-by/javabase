package com.zhangds.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Create by zhangds
 * 2020-05-08 15:24
 **/
public class OwnQueue<E> {

    private Object[] obj;
    private int addIndex;
    private int removeIndex;
    private int size; //队列实际长度
    private int DEFAULT_LENGTH = 10; //队列默认容量

    private Lock lock = new ReentrantLock();
    private Condition addCondition = lock.newCondition();
    private Condition removeCondition = lock.newCondition();

    public OwnQueue() {
        obj = new Object[DEFAULT_LENGTH];
    }

    public OwnQueue(int length) {
        obj = new Object[length];
    }

    public void add(E e){
        lock.lock();
        while (size == obj.length){
            try {
                addCondition.await();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        obj[addIndex] = e;
        if (++addIndex == obj.length){
            addIndex = 0;
        }
        size++;
        removeCondition.signal();
        lock.unlock();
    }

    public void remove(){
        lock.lock();
        while (size == 0){
            try {
                removeCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        obj[removeIndex] = null;
        if (++removeIndex == obj.length){
            removeIndex = 0;
        }
        size--;
        addCondition.signal();
        lock.unlock();
    }


}
