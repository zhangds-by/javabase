package com.zhangds.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义Lock
 * Sync：
 *
 * Create by zhangds
 * 2020-05-08 11:24
 **/
public class OwnLock implements Lock {

    private boolean isLocked = false; //是否拥有锁

    private Thread lockBy = null; //拥有锁的线程

    private int lockCount = 0; //重入锁数

    @Override
    public void lock() {
        Thread currentThread = Thread.currentThread();
        while (isLocked && currentThread!=lockBy){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockBy = currentThread;
        lockCount++;

    }

    @Override
    public void unlock() {
        if (lockBy == Thread.currentThread()){
            lockCount--;
            if (lockCount == 0){
                notify();
                isLocked = false;
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
