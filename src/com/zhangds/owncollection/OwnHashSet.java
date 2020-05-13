package com.zhangds.owncollection;

import java.util.HashMap;

/**
 * HashMap实现
 * Create by zhangds
 * 2020-05-06 17:54
 **/
public class OwnHashSet<T> {

    private HashMap map;

    private static final Object PRESENT = new Object();

    public OwnHashSet() {
        map = new HashMap();
    }

    public int size(){
        return map.size();
    }

    public void put(T obj){
        map.put(obj, PRESENT);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Object object : map.keySet()){
            sb.append(object + ",");
        }
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }
}
