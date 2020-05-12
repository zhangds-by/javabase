package com.zhangds.arth.sort;

/**
 * 每次都将当前元素插入到左侧已经排序的数组中，使得插入之后左侧数组依然有序。
 *
 * 插入排序需要交换的次数为逆序数量。
 *
 * 1 3 4 2 5
 * 1 3 2 4 5
 * 1 2 3 4 5
 *
 * Create by zhangds
 * 2020-05-12 10:22
 **/
public class InsertSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int n = nums.length;
        for (int i=1; i<n; i++){
            for (int j=i; j>0; j--){ //左边已排序，当前元素插入左边
                if (less(nums[j], nums[j-1])){
                    swap(nums, j, j-1);
                }
            }
        }
    }
}
