package com.zhangds.arth.leetcode;

/**
 * *a1*, *a2*, ..., *an* 这 *n* 个数，代表 (*i*, *ai*) 坐标，让你从中找两个点与 x 轴围成的容器可以容纳最多的水。
 *  1 8 6 2 5 4 8 3 7
 *  start   end   max
 *  1       7     8 * 1
 *  8       7     7 * 7
 */
public class L0011ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,1,6,2,5,4,8,3,7}));
    }

    public static int maxArea(int[] height) {
        int max= 0;     //记录最大结果
        int len = height.length;

        //左右索引
        int left = 0;
        int right = len-1;

        //最小高度
        int minH = 0;
        while (left<right){
            minH = Math.min(height[left], height[right]);
            max = Math.max(max, (right-left) * minH);
//            while (height[left] <= minH && left<right) left++;
//            while (height[right] <= minH && left<right) right--;
            if (height[left] < height[right] && left<right)
                left++;
            else
                right--;
        }
        return max;
    }

}
