package com.zhangds.owncollection;

import java.util.LinkedList;

/**
 * 双向链表
 * Create by zhangds
 * 2020-05-06 09:25
 **/
public class OwnLinkedList<E> {

    private Node head;
    private Node tail;
    private int size;

    private static class Node<E> {
        Node prev;
        Node next;
        E element;

        public Node(Node prev, E element, Node next) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }

        public Node(E element) {
            super();
            this.element = element;
        }
    }

    public void add(int index, E element) {
        //加入尾节点
        if (index == size){
            Node<E> newNode = new Node<E>(tail, element, null);
            if (tail == null){
                head = newNode;
            }else {
                tail.next = newNode;
            }
        }else {
            Node<E> temp = getNode(index);
            Node<E> up = temp.prev;
            Node<E> newNode = new Node<E>(up, element, temp);
            if (up == null){
                head = newNode;
            }else {
                up.next = newNode;
            }
            /*if (temp!=null){
                Node<E> upNode = temp.prev;
                upNode.next = newNode;
                newNode.prev = upNode;
                temp.prev = newNode;
                newNode.next = temp;
            }*/
        }
        size++;
    }

    private Node<E> getNode(int index) {
        checkIndex(index);
        //二分查找
        Node<E> temp = null;
        if (index > (size >> 1)) {
            temp = head;
            for (int i=0; i<index; i++){
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i=size-1; i>index; i--){
                temp = temp.prev;
            }
        }
        return temp;
    }

    public void  remove(int index){
        checkIndex(index);
        Node<E> temp = getNode(index);
        if (temp != null){
            Node<E> up = temp.prev;
            Node<E> down = temp.next;
            //头节点处理
            if (up == null){
                head = down;
            }else {
                up.next = down;
                temp.prev = null;
            }
            //尾节点处理
            if (down == null){
                tail = up;
            } else {
                down.prev = up;
                temp.next = null;
            }
            temp.element = null;
            size --;
        }
    }

    public void checkIndex(int index){
        if (!(index<0 || index>size)){
            throw new IndexOutOfBoundsException("索引不合法");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node temp = head;
        while (temp!= null){
            sb.append(temp.element + ",");
            temp = temp.next;
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }
}
