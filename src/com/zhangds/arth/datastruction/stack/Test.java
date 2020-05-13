package com.zhangds.arth.datastruction.stack;

import java.util.Iterator;

/**
 * Create by zhangds
 * 2020-05-12 14:47
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        //ArrayStack<Integer> stack = new ArrayStack<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        stack.push(1).push(2).push(3);
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.isEmpty());

        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }

    }
}
