package com.zhangds.arth.datastruction.symboltable;

import java.util.List;

/**
 * Create by zhangds
 * 2020-05-12 15:37
 **/
public interface OrderedST<K extends Comparable<K>, V> {
    int size();
    void put(K key, V value);
    V get(K key);
    K min();
    K max();
    int rank(K key);
    List<K> keys(K l, K h);
}
