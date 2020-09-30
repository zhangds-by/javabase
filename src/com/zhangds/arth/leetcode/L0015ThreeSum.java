package com.zhangds.arth.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 从数组中找出所有三个和为 0 的元素构成的非重复序列
 * 1、排序
 * 2、循环：当前元素、 指针指向下一元素 和 最后一个元素，根据排序的三个数据相加与 0 的对比，移动指针
 * 3、优化 和 去重
 */
public class L0015ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -3}));

        System.out.println(threeSum2(new int[]{-1, 0, 1, 2, -1, -3}));
        System.out.println(threeSum3(new int[]{-1, 0, 1, 2, -1, -3}));
    }

    public static List<List<Integer>> threeSum2(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3){
            return lists;
        }
        int len = nums.length;

        Arrays.sort(nums);
        for (int i=0; i<len-2; i++){
            int l = i+1;
            int r = len-1;
            while (l<r){
                if (nums[i] + nums[l] + nums[r] > 0){
                    r--;
                }else if (nums[i] + nums[l] + nums[r] < 0) {
                    l++;
                }else {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[l]);
                    res.add(nums[r]);
                    if (!lists.contains(res)){
                        lists.add(res);
                    }
                    l++;
                    r--;
                }
            }
        }
        return lists;
    }

    public static List<List<Integer>> threeSum3(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 3){
            return lists;
        }
        int len = nums.length;
        Arrays.sort(nums);

        int i = 0;
        while (i<len-2){
            int l = i+1;
            int r = len-1;
            while (l<r){
                if (nums[i] + nums[l] + nums[r] > 0){
                    do{ r--; }while (l<r && nums[r] == nums[r+1]); //去重
                }else if (nums[i] + nums[l] + nums[r] < 0) {
                    do{ l++; }while (l<r && nums[l] == nums[l-1]); //去重
                }else {
                    List<Integer> res = new ArrayList<>();
                    res.add(nums[i]);
                    res.add(nums[l]);
                    res.add(nums[r]);
                    do{ r--; }while (l<r && nums[r] == nums[r+1]); //去重
                    do{ l++; }while (l<r && nums[l] == nums[l-1]); //去重
                    lists.add(res);
                }
            }
            do { i++; }while (i<len-2 && nums[i-1] == nums[i]);
        }
        return lists;
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> lists = new ArrayList<>();

        //三次循环没有体现排序的用意，而且需要单独的去重操作
        for (int i=0; i<len-2; i++){ // 指针指向当前元素
            for (int j=i+1; j<len-1; j++){
                for (int k=len-1; k>j; k--){
                    if (0 == (nums[i] + nums[j] + nums[k])){
                        List resList = new ArrayList();
                        resList.add(nums[i]);
                        resList.add(nums[j]);
                        resList.add(nums[k]);
                        if (!lists.contains(resList)){
                            lists.add(resList);
                        }
                    }
                }
            }
        }
        return lists;
    }
}
