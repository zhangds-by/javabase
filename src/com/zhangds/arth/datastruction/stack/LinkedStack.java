package com.zhangds.arth.datastruction.stack;

import java.util.Iterator;

/**
 * Create by zhangds
 * 2020-05-12 14:57
 **/
public class LinkedStack<Item> implements OwnStack<Item> {

    private Node top = null;
    private int size = 0;

    private class Node{
        Item item;
        Node next;
    }

    @Override
    public OwnStack<Item> push(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = top;
        top = newNode;
        size++;
        return this;
    }

    @Override
    public Item pop() throws Exception {
        if (isEmpty()){
            throw new Exception("stack is empty");
        }
        Item item = top.item;
        top = top.next;
        size--;
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

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node currentNode = top;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public Item next() {
                Item item = currentNode.item;
                currentNode = currentNode.next;
                return item;
            }
        };
    }
}
