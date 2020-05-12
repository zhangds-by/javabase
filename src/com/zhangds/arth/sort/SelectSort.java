package com.zhangds.arth.sort;

/**
 * 选择出数组中的最小元素，将它与数组的第一个元素交换位置。
 * 再从剩下的元素中选择出最小的元素，将它与数组的第二个元素交换位置。
 * 不断进行这样的操作，直到将整个数组排序。
 * Create by zhangds
 * 2020-05-12 10:22
 **/
public class SelectSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        int n = nums.length;
        for (int i=0; i<n; i++){
            int min = i;
            for (int j=i+1; j<n; j++){
                if (less(nums[j], nums[min])){ //nums[j] < nums[i]
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }
}
