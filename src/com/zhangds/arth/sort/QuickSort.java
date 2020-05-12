package com.zhangds.arth.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 快速排序通过一个切分元素将数组分为两个子数组，左子数组小于等于切分元
 * 素，右子数组大于等于切分元素，将这两个子数组排序也就将整个数组排序了。
 * 切分 + 排序
 *
 * Create by zhangds
 * 2020-05-12 10:22
 **/
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }
    private void sort(T[] nums, int l, int h) {
        if (h <= l)
            return;
        int j = partition(nums, l, h);
        sort(nums, l, j - 1);
        sort(nums, j + 1, h);
    }
    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    /**
     * 取 a[l] 作为切分元素，然后从数组的左端向右扫描直到找到第一个大于等于它的元
     * 素，再从数组的右端向左扫描找到第一个小于等于它的元素，交换这两个元素。
     * 不断进行这个过程，就可以保证左指针 i 的左侧元素都不大于切分元素，右指针 j 的
     * 右侧元素都不小于切分元素。当两个指针相遇时，将切分元素 a[l] 和 a[j] 交换位置。
     *
     */
    private int partition(T[] nums, int l, int h) {
        int i = l, j = h + 1;
        T v = nums[l];
        while (true) {
            while (less(nums[++i], v) && i != h) ;
            while (less(v, nums[--j]) && j != l) ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }
}
