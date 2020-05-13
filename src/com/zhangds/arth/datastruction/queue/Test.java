package com.zhangds.arth.datastruction.queue;


import java.util.Iterator;

/**
 * Create by zhangds
 * 2020-05-12 14:47
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        LinkedQueue<Integer> stack = new LinkedQueue<>();

        stack.add(1).add(2).add(3);
        System.out.println(stack.remove());
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());

        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }

    }
}
