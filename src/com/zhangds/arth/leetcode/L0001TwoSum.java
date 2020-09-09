package com.zhangds.arth.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * @author zhangds
 * @date 2020/8/5 9:01
 */
public class L0001TwoSum {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 7, 11, 23, 7, 2};
        int[] res = new int[]{0, 0};
//        res = addTwoNum2(arr, 18);
//        res = addTwoNum3(arr, 18);
        res = addTwoNum(arr, 9);
        System.out.println("index1=" + res[0] + ",index2=" + res[1]);
    }

    /**
     * 获取所有的下标
     * @author zhangds
     * @date 2020/8/5 12:03
     */
    public static int[] addTwoNum(int[] nums, int target){
        /*
        设置一个 map 容器 record 用来记录元素的值与索引，然后遍历数组 nums。
        每次遍历时使用临时变量 complement 用来保存目标值与当前值的差值
        在此次遍历中查找 record ，查看是否有与 complement 一致的值，如果查找成功则返回查找值的索引值与当前变量的值 i
        如果未找到，则在 record 保存该元素与索引值 i
         */
        Map<Integer, List<Integer>> map = new HashMap<>(nums.length);

        //收集所有相同值的下标
        for (int i=0; i<nums.length; i++){
            if (map.containsKey(nums[i])){
                map.get(nums[i]).add(i);
            }else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                map.put(nums[i], indexList);
            }
        }

        int[] result = new int[]{0, 0};
        for (int i=0; i<nums.length; i++){
            int temp = target - nums[i];
            if (map.containsKey(temp)){
                if (temp == nums[i]){
                    result[0] = Math.min(map.get(temp).get(0), map.get(map).get(1));
                    result[1] = Math.max(map.get(temp).get(0), map.get(map).get(1));
                }else{
                    result[0] = Math.min(map.get(nums[i]).get(0), map.get(temp).get(0));
                    result[1] = Math.max(map.get(nums[i]).get(0), map.get(temp).get(0));
                }
            }
        }
        return result;
    }

    public static int[] addTwoNum2(int[] nums, int target){
        /*
        循环两次查找索引
         */
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length; i++){
                if (nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] addTwoNum3(int[] nums, int target){
        /*
        利用 HashMap 作为存储，键为目标值减去当前元素值，索引为值，比如 i = 0 时，此时首先要判断 nums[0] = 2 是否在 map 中，
        如果不存在，那么插入键值对 key = 9 - 2 = 7, value = 0，之后当 i = 1 时，此时判断 nums[1] = 7 已存在于 map 中，
        那么取出该 value = 0 作为第一个返回值，当前 i 作为第二个返回值
         */
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i=0; i<nums.length; i++){
            Integer index = map.get(nums[i]);
            if (!map.containsKey(nums[i])){
                map.put(target-nums[i], i);
            }else {
                return new int[]{index, i};
            }
        }
        return null;
    }

}
