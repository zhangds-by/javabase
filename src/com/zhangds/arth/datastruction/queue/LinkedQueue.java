package com.zhangds.arth.datastruction.queue;

import java.util.Iterator;

/**
 * Create by zhangds
 * 2020-05-12 15:14
 **/
public class LinkedQueue<Item> implements OwnQueue<Item> {

    private Node head;
    private Node tail;
    private int size = 0;

    private class Node{
        Item item;
        Node next;

        public Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }

        public Node() {
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public OwnQueue<Item> add(Item item) {
        Node newNode = new Node(item,null);
        if (isEmpty()){
            head = newNode;
            tail = newNode;
        }else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return this;
    }

    @Override
    public Item remove() throws Exception {
        if (isEmpty()){
            throw new Exception("queue is empty");
        }

        Node node = head;
        head = head.next;
        size--;

        if (isEmpty()){
            tail = null;
        }
        return node.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            private Node curentNode = head;
            @Override
            public boolean hasNext() {
                return curentNode!=null;
            }

            @Override
            public Item next() {
                Item item = curentNode.item;
                curentNode = curentNode.next;
                return item;
            }
        };
    }
}
