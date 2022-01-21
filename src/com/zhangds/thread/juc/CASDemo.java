package com.zhangds.thread.juc;

/**
 * CAS算法模拟
 * CAS（Compare-And-Swap） 算法保证数据变量的原子性
 *  * 			CAS 算法是硬件对于并发操作的支持
 *  * 			CAS 包含了三个操作数：
 *  * 			①内存值  V
 *  * 			②预估值  A
 *  * 			③更新值  B
 *  * 			当且仅当 V == A 时， V = B; 否则，不会执行任何操作。
 * @author zhangds
 * @date 2022/1/21 13:02
 */
public class CASDemo {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int expectedValue = cas.get();
                    int newValue = (int)(Math.random() * 101);
                    boolean isUpdate = cas.compareAndSet(expectedValue, newValue);
                    System.out.println("内存值：" + expectedValue + "更新值："
                            + newValue + "是否更新" + isUpdate + "更新后的值" + cas.get());
                }
            }).start();
        }

    }
}

class CompareAndSwap{

    // 本地内存变量
    private int value;

    // 获取内存值
    public synchronized int get(){
        return value;
    }

    // 比较，只有当修改值之前，内存的值没有被更改过，才进行更新
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            value = newValue;
        }
        return oldValue;
    }

    // 设值
    public synchronized boolean compareAndSet(int expectedValue, int newValue){
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }

}
