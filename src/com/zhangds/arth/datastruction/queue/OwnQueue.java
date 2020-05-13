package com.zhangds.arth.datastruction.queue;

/**
 * Create by zhangds
 * 2020-05-12 15:12
 **/
public interface OwnQueue<Item> extends Iterable<Item> {

    int size();

    boolean isEmpty();

    OwnQueue<Item> add(Item item);

    Item remove() throws Exception;
}
