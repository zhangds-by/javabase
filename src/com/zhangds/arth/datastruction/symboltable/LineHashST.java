package com.zhangds.arth.datastruction.symboltable;

/**
 * 散列表
 *  拉链法散列表：HashMap
 *  线性探测法散列表：线性探测法使用空位来解决冲突，当冲突发生时，向前探测一个空位来存储冲突的键
 *
 *  无序优先选择散列表，有序选择红黑树
 * Create by zhangds
 * 2020-05-12 15:39
 **/
public class LineHashST<K, V> implements UnorderedST<K, V> {

    private int size = 16; //数组大小
    private int keySize = 0; //键的个数 实际应当size>keySize
    private K[] keys;
    private V[] values;

    public LineHashST(int size) {
        this.size = size;
        init();
    }

    public LineHashST() {
        init();
    }

    private void init() {
        keys = (K[]) new Object[size];
        values = (V[]) new Object[size];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % size; //除留余数法
    }

    private void resize() {
        if (keySize >= size / 2)
            resize(2 * size); //扩容数组大小是键数量2倍
        else if (keySize <= size / 8)
            resize(size / 2); //缩减数组大小不超过键数量4倍
    }

    private void resize(int cap) {
        LineHashST<K, V> t = new LineHashST<K, V>(cap);
        for (int i=0; i<size; i++){
            if (keys[i] != null){
                t.putInternal(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
        size = t.size;
    }

    private void putInternal(K key, V value) {
        int i;
        for (i=hash(key); keys[i]!=null; i=(i+1)%size){ //发生冲突时，向前探测一个空位存储冲突的键
            if (keys[i].equals(key)){
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        keySize++;
    }

    @Override
    public int size() {
        return keySize;
    }

    @Override
    public V get(K key) {
        for (int i=hash(key); keys[i]!=null; i=(i+1)%size){
            if (keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        resize();
        putInternal(key, value);
    }

    @Override
    public void delete(K key) {
        int hash = hash(key);
        while (keys[hash]!=null && key.equals(keys[hash])){
            hash = (hash+1) % size;
        }
        if (keys[hash] == null){
            return;
        }

        keys[hash] = null;
        values[hash] = null;

        //之后的键值对重新插入
        hash = (hash+1) % size;
        while (keys[hash] != null){
            K keyToRedo = keys[hash];
            V valToRedo = values[hash];
            keys[hash] = null;
            values[hash] = null;
            keySize--;
            putInternal(keyToRedo, valToRedo);
            hash = (hash + 1) % size;
        }
        keySize--;
        resize();
    }
}
