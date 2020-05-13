package com.zhangds.arth.datastruction.symboltable;

/**
 * 链表实现无序符号表
 * Create by zhangds
 * 2020-05-12 15:39
 **/
public class LinkedUnorderedST<K, V> implements UnorderedST<K, V> {

    private Node first;

    private class Node{
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    @Override
    public int size() {
        Node node = first;
        int size = 0;
        while (node!=null){
            size++;
            node = node.next;
        }
        return size;
    }

    @Override
    public V get(K key) {
        Node cur = first;
        while (cur!=null){
            if (cur.key.equals(key)){
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        //重复的key，更新value
        Node cur = first;
        while (cur!=null){
            if (cur.key.equals(key)){
                cur.value = value;
                return;
            }
            cur = cur.next;
        }
        //不重复的key，使用头插入添加
        first = new Node(key, value, first);
    }

    @Override
    public void delete(K key) {
        //表为空
        if (first==null){
            return;
        }
        //删除的是头节点
        if (first.key.equals(key)){
            first = first.next;
        }
        //删除的是其他节点
        Node pre = first; //记录当前节点的上一节点
        Node cur = first.next;
        while (cur!=null){
            if (cur.key.equals(key)){
                pre.next = cur.next;
                return;
            }
            pre = cur;
            cur = cur.next;
        }
    }
}
