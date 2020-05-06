package com.zhangds.owncollection;

import java.util.HashMap;

/**
 * 数组（key的hash值索引） + 单向链表
 * Create by zhangds
 * 2020-05-06 14:43
 **/
public class OwnHashMap<K, V> {


    private Node[] table;
    private int size;

    private static class Node<K, V>{
        int  hash;
        K  key;
        V  value;
        Node  next;

        public Node(int hash, K key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node() {
        }
    }

    public V get(K key){
        int hash = myHash(key.hashCode(), table.length);
        V value = null;
        if (table[hash] != null){
            Node<K, V> temp =table[hash];
            while (temp != null){
                if (temp.key.equals(key)){
                    value = temp.value;
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }
        return value;

    }

    public void put(K key, V value){
        Node<K, V> newNode = new Node(myHash(key.hashCode(), table.length), key, value, null);
        //放进数组
        Node<K, V> temp = table[newNode.hash];
        Node iterLast = null; //记录遍历节点
        boolean  keyRepeat = false; //是否又重复的key
        if (temp == null){
            table[newNode.hash] = newNode;
            size++;
        } else { //放进数组链表
            while (temp != null){
                //key重复，覆盖，不存在，向下遍历
                if (temp.key.equals(key)){
                    keyRepeat = true;
                    temp.value = value;
                    break;
                }else {
                    iterLast = temp;
                    temp = temp.next;
                }
            }
            if (!keyRepeat){
                iterLast.next = newNode;
                size++;
            }
        }


    }

    public static  int  myHash(int  v, int length){
//		System.out.println("hash in myHash:"+(v%(length-1)));		//取模运算，效率低
        return  v&(length-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i=0; i<table.length; i++){
            Node temp = table[i];
            while (temp!=null){
                sb.append(temp.key + ":" + temp.value);
                temp = temp.next;
            }
        }
        sb.setCharAt(table.length-1, '}');
        return sb.toString();
    }
}
