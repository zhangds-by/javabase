package com.zhangds.arth.datastruction.symboltable;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉查找树
 * Create by zhangds
 * 2020-05-12 16:09
 **/
public class BST<K extends Comparable<K>, V> implements OrderedST<K, V> {


    @Override
    public int size() {
        return 0;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public K min() {
        return null;
    }

    @Override
    public K max() {
        return null;
    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public List<K> keys(K l, K h) {
        return null;
    }
}
