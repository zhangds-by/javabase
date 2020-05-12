package com.zhangds.arth.sort;

/**
 * Create by zhangds
 * 2020-05-12 10:24
 **/
public abstract class Sort<T extends Comparable<T>> {

    public abstract void sort(T[] nums);

    protected boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public void swap(T[] nums, int i, int j){
        T temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
