package com.zhangds.thread.comstomerandproducer;

/**
 * synchronized实现生产者和消费者
 * Create by zhangds
 * 2020-05-08 14:40
 **/
public class Store {

    private int count;

    private int MAX_COUNT = 10;

    public synchronized void push(){
        //库存膨胀，生产等待
        while (count > MAX_COUNT){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;    //库存有余，开始生产
        notifyAll(); //有货唤醒消费
    }

    public synchronized void pop(){
        //没有商品，消费等待
        while (count <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        notifyAll(); //有商品被消费，唤醒生产
    }
}
