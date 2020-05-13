package com.zhangds.arth.datastruction.symboltable;

/**
 * 无序符号表
 * Create by zhangds
 * 2020-05-12 15:34
 **/
public interface UnorderedST<K, V> {
    int size();
    V get(K key);
    void put(K key, V value);
    void delete(K key);
}
