package com.zhangds.arth.sort;

/**
 * 希尔排序的出现就是为了改进插入排序的只能交换相邻元素局限性，它通过交换不相邻的元素,每次可以将逆序数量减少大于 1。
 * 希尔排序使用插入排序对间隔 h 的序列进行排序。通过不断减小 h，最后令 h=1，就可以使得整个数组是有序的。
 *
 * 分组进行直接插入排序，减少增量，直至增量为0
 * Create by zhangds
 * 2020-05-12 10:22
 **/
public class ShellSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int len = nums.length;
        int h = 1;
        while (h < len/2) {
            h = 2 * h;
        }
        while (h>=1) {
            for (int i=h; i<len; i++) {
                for (int j=i; j>=h; j -= h) {
                    if (less(nums[j], nums[j-h])){
                        swap(nums, j, j-h);
                    }
                }
            }
            h = h/2; //减小增量
        }
    }
}
