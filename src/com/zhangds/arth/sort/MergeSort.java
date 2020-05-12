package com.zhangds.arth.sort;

/**
 * 归并排序将数组分为两个子数组分别排序，并将有序的子数组归并使得整个数组排序
 * 分组 + 排序
 *
 * Create by zhangds
 * 2020-05-12 10:22
 **/
public class MergeSort<T extends Comparable<T>> extends Sort<T> {

    protected T[] aux;
    protected void merge(T[] nums, int l, int m, int h) {
        int i = l, j = m + 1;
        for (int k = l; k <= h; k++) {
            aux[k] = nums[k]; // 将数据复制到辅助数组
        }
        for (int k = l; k <= h; k++) {
            if (i > m) {
                nums[k] = aux[j++];
            } else if (j > h) {
                nums[k] = aux[i++];
            } else if (aux[i].compareTo(nums[j]) <= 0) {
                nums[k] = aux[i++]; // 先进行这一步，保证稳定性
            } else {
                nums[k] = aux[j++];
            }
        }
    }

    @Override
    public void sort(T[] nums) {
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);
    }
    private void sort(T[] nums, int l, int h) {
        if (h <= l) {
            return;
        }
        int mid = l + (h - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, h);
        merge(nums, l, mid, h);
    }
}
