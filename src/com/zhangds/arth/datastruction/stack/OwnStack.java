package com.zhangds.arth.datastruction.stack;

/**
 * 栈
 * Create by zhangds
 * 2020-05-12 14:20
 **/
public interface OwnStack<Item> extends Iterable<Item> {

    OwnStack<Item> push(Item item);

    Item pop() throws Exception;

    boolean isEmpty();

    int size();
}
