package com.zhangds.arth.leetcode;

import com.zhangds.arth.leetcode.base.ListNode;

import java.util.*;

/**
 * 计算出字符串中，连续不重复的最大长度的字符串
 *
 *  abcbbb -> abc 3
 *  bbbb -> b 1
 *
 *  统计不重复的字符串和长度 获取最大的长度
 */
public class L0003LongNoRepeatChar {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcbb"));
        System.out.println(lengthOfLongestSubstring("bbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkl"));
        System.out.println(lengthOfLongestSubstring("abcabcdabcdefbb"));

        System.out.println(lengthOfLongestSubstring2("abcbb"));
        System.out.println(lengthOfLongestSubstring2("bbbb"));
        System.out.println(lengthOfLongestSubstring2("pwwkl"));
        System.out.println(lengthOfLongestSubstring2("abcabcdabcdefbb"));
    }

    /**
     * 最长不重复子串
     */
    public static int lengthOfLongestSubstring(String source){

        //遍历字符串，字符是否出现过，如果出现过就重新计算
        int count = 0;
        int end = 0;
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int i=0; i<source.length(); i++){
            char ch = source.charAt(i);
            if (set.contains(ch)){
                res = Math.max(res, count);
                end = i;
                count = 1;
                set = new HashSet<>();
            } else {
                end++;
                count++;
            }
            set.add(ch);
        }
        res = Math.max(res, count);
        int start = end - res;
//        System.out.println("最大子串的起始位" + start);
        return res;
    }

    public static int lengthOfLongestSubstring2(String source){

        Map<Character, Integer> map = new HashMap<>(source.length());
        /**
         * a b c    a b c d     a b c  d  e  f
         * 0 1 2    3 4 5 6     7 8 9 10 11 12
         */

        //遍历字符串，字符是否出现过，如果出现过就重新计算
        int start = 0; //为了最大长度和当前计算子串的长度比较
        int end = 0;
        int res = 0;
        for (int i=0; i<source.length(); i++){
            char ch = source.charAt(i);
            if (map.containsKey(ch) && map.get(ch)>=start){
                start =  i;
            } else {
                res = Math.max(res, i - start + 1); //
            }
            map.put(ch, i);
        }
//        System.out.println("最大非重复子串的起始位置" + start);
        return res;
    }

}
