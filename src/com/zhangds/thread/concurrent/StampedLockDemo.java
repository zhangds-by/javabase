package com.zhangds.thread.concurrent;

import java.util.concurrent.locks.StampedLock;

/**
 * Create by zhangds
 * 2020-05-11 11:46
 **/
public class StampedLockDemo {

    private int balance;

    private StampedLock lock = new StampedLock();

    //条件读写，判断balance的值是否符合更新条件
    public void conditionReadWrite(int value) {
        long stamp = lock.readLock();
        while (balance > 0) {
            long writeStamp = lock.tryConvertToWriteLock(stamp);
            if (writeStamp != 0) { //成功转换为写锁
                stamp = writeStamp;
                balance += value;
                break;
            } else { //先释放读锁，再获取写锁
                lock.unlockRead(stamp);
                stamp = lock.writeLock();
            }
        }
        lock.unlock(stamp);
    }

    //乐观读锁
    public void optimisticRead() {
        long stamp = lock.tryOptimisticRead();
        int b = balance;
        if (!lock.validate(stamp)){ //是否写操作改变了值
            //重新读值
            long readStamp = lock.readLock();
            b = balance;
            stamp = readStamp;
        }
        lock.unlockRead(stamp);
    }
}
