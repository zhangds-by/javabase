package com.zhangds.arth.leetcode;

/**
 * 从字符串数组中找出公共前缀
 */
public class L0014LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(longestCommonPrefix(new String[]{"dog","dog","dog"}));
    }

    /**
     * 以最短字符串的长度作为循环次数，直接返回字符不相等的索引长度的字符串
     * @param ss
     * @return
     */
    public static String longestCommonPrefix(String[] ss){
        int len = ss.length;
        if (len == 0){
            return "";
        }

        int minLen = Integer.MAX_VALUE;

        for (String s : ss){
            minLen = Math.min(minLen, s.length());
        }

        for (int i=0; i<minLen; i++){
            for (int j=1; j<len; j++){
                if (ss[0].charAt(i) != ss[j].charAt(i)){
                    return ss[0].substring(0, i);
                }
            }
        }
        return ss[0].substring(0, minLen);
    }
}
