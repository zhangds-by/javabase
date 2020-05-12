package com.zhangds.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * AQS是 JUC的核心
 * Create by zhangds
 * 2020-05-08 13:55
 **/
public class AQSLock implements Lock {

    private Helper helper = new Helper();

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }

    private class Helper extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            //判断进来的线程是否为新线程
            int state = getState();
            Thread currentThread = Thread.currentThread();
            if (state == 0){ //线程第一次进来
                if (compareAndSetState(0, arg)){
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            }else if(getExclusiveOwnerThread() == currentThread) { //进来的线程是否是重入线程
                setState(state + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //锁的获取和释放一一对应，释放锁必须时当前线程
            if (Thread.currentThread() != getExclusiveOwnerThread()){
                throw new RuntimeException();
            }
            int state = getState() - arg;
            boolean flag = false;
            if (state == 0){
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(state);
            return flag;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }
}
