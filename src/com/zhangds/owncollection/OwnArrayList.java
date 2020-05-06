package com.zhangds.owncollection;

import java.util.ArrayList;

/**
 * Create by zhangds
 * 2020-04-30 14:43
 **/
public class OwnArrayList<T> {

    private Object[] elementData;
    private int size;
    private static final int DEFALT_CAPACITY = 10;

    public OwnArrayList() {
        elementData = new Object[DEFALT_CAPACITY];
    }

    public OwnArrayList(int capacity){
        if (capacity<0){
            throw new RuntimeException("容器容量不为负数");
        }else if (capacity == 0){
            elementData = new Object[DEFALT_CAPACITY];
        }else{
            elementData = new Object[capacity];
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0 ? true : false;
    }

    public void add(T element){
        //扩容
        if (size == elementData.length){
            Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }

        elementData[size++] = element;
    }

    public T get(int index){
        checkIndex(index);
        return (T) elementData[index];
    }

    public void set(int index, T element){
        checkIndex(index);
        elementData[index] = element;
    }

    public void remove(T element){
        for (int i=0; i<size; i++){
            if (element.equals(get(i))){
                remove(i);
            }
        }
    }

    public void remove(int index){
        int numMove =elementData.length-index-1;
        if (numMove > 0){
            System.arraycopy(elementData, index+1, elementData, index, numMove);
        }
        elementData[size--] = null;
    }

    public void checkIndex(int index){
        if (index < 0 || index > size-1){
            throw new RuntimeException("索引不合法" + index);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i=0; i<size; i++){
            stringBuilder.append(elementData[i] + ",");
        }
        stringBuilder.setCharAt(stringBuilder.length()-1, ']');
        return stringBuilder.toString();
    }
}
