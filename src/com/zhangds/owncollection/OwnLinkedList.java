package com.zhangds.owncollection;

import java.util.LinkedList;

/**
 * 双向链表
 * Create by zhangds
 * 2020-05-06 09:25
 **/
public class OwnLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    private static class Node<E> {
        Node<E> prev;
        Node<E> next;
        E element;

        public Node(Node<E> prev, E element, Node<E> next) {
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
            addLast(element);
        }else {
            Node<E> temp = getNode(index);
            Node<E> up = temp.prev;
            Node<E> newNode = new Node<E>(up, element, temp);
            temp.prev = newNode;
            if (up == null){ //获取的节点是首节点
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

    private void addHead(E e){
        Node<E> f = head;
        Node<E> newNode = new Node<>(null, e, f);
        head = newNode; //头节点移动
        if (f == null){ //头为空说明链表没有元素,尾节点也要指向插入节点
            tail = newNode;
        }else {
            f.prev = newNode;
        }
        size++;
    }

    private void addLast(E e){
        Node<E> l = tail;
        Node<E> newNode = new Node<E>(l, e, null);
        tail = newNode; //尾节点移动
        if (l == null){ //最后一个节点为空，链表中没有元素
            head = newNode;
        }else {
            l.next = newNode;
        }
    }
    public int size(){
        return size;
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

    public E get(int index){
        checkIndex(index);
        return getNode(index).element;
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
        if (index<0 || index>size){
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
