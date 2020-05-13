package com.zhangds.arth.datastruction.symboltable;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * 平行数组分别存储键和值
 * Create by zhangds
 * 2020-05-12 16:09
 **/
public class BinarySearchOrderedST<K extends Comparable<K>, V> implements OrderedST<K, V> {

    private K[] keys;
    private V[] values;
    private int size = 0;
    private static final int CAPACITY = 10;

    public BinarySearchOrderedST(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    public BinarySearchOrderedST() {
        keys = (K[]) new Comparable[CAPACITY];
        values = (V[]) new Object[CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int index = rank(key);
        //存在键节点的索引，更新值节点的值
        if (index<size && keys[index].compareTo(key)==0){
            values[index] = value;
            return;
        }
        //否则插入新节点，插入位置的节点都向后移动
        for (int i=size; i>index; i--){
            keys[i] = keys[i-1];
            values[i] = values[i-1];
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }

    @Override
    public V get(K key) {
        int index = rank(key);
        if (index<size && keys[index].compareTo(key)==0){
            return values[index];
        }
        return null;
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K max() {
        return keys[size-1];
    }

    /**
     * 二分查找定位key的位置
     */
    @Override
    public int rank(K key) {
        int low = 0;
        int high = size-1;
        while (low<=high){
            int mid = (low + high)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp == 0){
                return mid;
            }else if (cmp>0){ //mid较小
                low = mid+1;
            }else { //mid较大
                high = mid-1;
            }
        }
        return low;
    }

    @Override
    public List<K> keys(K l, K h) {
        int index = rank(l);
        List<K> list = new ArrayList<>();
        while (keys[index].compareTo(h) <= 0){
            list.add(keys[index]);
            index++;
        }
        return list;
    }
}
