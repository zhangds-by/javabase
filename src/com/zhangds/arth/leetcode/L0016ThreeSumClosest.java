package com.zhangds.arth.leetcode;

import java.util.Arrays;

public class L0016ThreeSumClosest {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));

    }

    public static int threeSumClosest(int[] nums, int target){
        int len = nums.length;
        int closestNum = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        for (int i=0; i<len-2; i++){
            int l = i+1;
            int r = len-1;
            int sum = nums[i] + nums[l] + nums[r];
            int diff = Math.abs(sum - target);
            if (diff == 0){
                return sum;
            }
            if (diff < closestNum) { //差值比之前记录的还小，更新 差值 和 结果
                closestNum = diff;
                res = sum;
            }
            if (sum > target){
                l++;
            }else {
                r--;
            }
        }
        return res;
    }
}
