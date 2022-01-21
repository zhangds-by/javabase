package com.zhangds.thread.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {

    public static void main(String[] args) {
        CopyOnWriteThread thread = new CopyOnWriteThread();

        for (int i = 0; i < 10; i++) {
            new Thread(thread).start();
        }
    }
}

class CopyOnWriteThread implements Runnable{

    // java.util.ConcurrentModificationException 并发读写异常
//    private static List<String> list = Collections.synchronizedList(new ArrayList<String>());

    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static{
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
            list.add("AA");
        }
    }
}
