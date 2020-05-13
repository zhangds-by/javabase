package com.zhangds.arth.datastruction.stack;

import java.util.Iterator;

/**
 * Create by zhangds
 * 2020-05-12 14:22
 **/
public class ArrayStack<Item> implements OwnStack<Item> {

    private static final int DEFAULT_SIZE = 10;

    private Item[] arr = (Item[]) new Object[DEFAULT_SIZE];

    private int size = 0; //记录元素最大索引

    @Override
    public OwnStack<Item> push(Item item) {
        check();
        arr[size++] = item;
        return this;
    }

    @Override
    public Item pop() throws Exception {
        //数组不能为空
        if (isEmpty()){
            throw new Exception("stack is empty");
        }
        //获取对象并检查
        Item item = arr[--size];
        check();
        //避免对象游离,将弹出的对象置空
        arr[size] = null;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 逆序迭代器
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private int i=size;
            @Override
            public boolean hasNext() {
                return i>0;
            }

            @Override
            public Item next() {
                return arr[--i];
            }
        };
    }

    private void check() {
        if (size >= arr.length) {
            resize(2 * arr.length);
        } else if (size > 0 && size <= arr.length / 4) {
            resize(arr.length / 2);
        }
    }

    private void resize(int size) {

        Item[] tmp = (Item[]) new Object[size];

        for (int i=0; i<size; i++){
            tmp[i] = arr[i];
        }
        arr = tmp;
    }
}
