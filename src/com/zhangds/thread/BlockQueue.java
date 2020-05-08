package com.zhangds.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Create by zhangds
 * 2020-05-08 11:45
 **/
public class BlockQueue<T> {

    List<T> list = new ArrayList();

    public synchronized T pop(){
        while (list.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (list.size() > 0){
            return list.remove(0);
        }else {
            return null;
        }
    }

    public synchronized void push(T t){
        push(t);
        this.notify();
    }

}
