package com.zhangds.arth.sort;

import java.util.Arrays;

/**
 * 选择排序
 * Create by zhangds
 * 2020-05-12 10:36
 **/
public class Test {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3,12,1,7,6};

        //new SelectSort<Integer>().sort(arr);
        //new BubbleSort<Integer>().sort(arr);
        //new InsertSort<Integer>().sort(arr);
        new ShellSort<Integer>().sort(arr);
        System.out.println("排序结果" + Arrays.toString(arr));
    }
}
