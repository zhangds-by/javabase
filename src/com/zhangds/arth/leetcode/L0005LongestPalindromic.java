package com.zhangds.arth.leetcode;

/**
 * 字符串中最长的回文串
 *
 * 1、分别求得每个字符的最长回文
 * 2、动态规划：（[i,j] 是回文串）
 *      i == j
 *      i+1 == j    s[i] == s[j]
 *      i+1 < j     [i+1, j-1] 是回文串 && s[i] == s[j]
 * 3、通过回文长度分析
 *
 * 4、马拉车算法
 */
public class L0005LongestPalindromic {

    public static void main(String[] args) {
//        System.out.println(longestPalindromic("abccbaab"));
        System.out.println(longestPalindromic("lollool"));

        System.out.println(longestPalindromic2("lollool"));
    }

    public static String longestPalindromic(String source){
        if (source == null || source.length()<2){
            return source;
        }

        int len = source.length();
        int maxLen = 0;
        String pStr = null;
        boolean[][] table = new boolean[len][len];

        // 单个字符回文
        for (int i=0; i<len; i++){ //计算table[i][i] 是否回文
            table[i][i] = true;
            pStr = source.substring(i, i+1);
            maxLen = 1;
        }

        // 两个字符回文
        for (int i=0; i<len-1; i++){ //计算table[i][i+1] 是否回文
            if (source.charAt(i) == source.charAt(i+1)){
                table[i][i+1] = true;
                pStr = source.substring(i, i+2);
                maxLen = 2;
            }
        }

        //回文字符串长度大于2
        for (int l=3; l<=len; l++){ //检验不同长度的字符串是否回文
            for (int i=0, j; (j=i+l-1) <= len-1; i++){
                if (source.charAt(i) == source.charAt(j)){
                    table[i][j] = table[i+1][j-1]; //table[i][j] && table[i+1][j-1]
                    if (table[i][j] && maxLen<l){
                        pStr = source.substring(i, j+1);
                        maxLen = l;
                    }
                }else {
                    table[i][j] = false;
                }
            }
        }

        printTable(table, len);

        return pStr;
    }

    public static String longestPalindromic2(String source){
        int len = source.length();
        boolean[][] table = new boolean[len][len];
        char[] chars = source.toCharArray();
        if (len<=1){
            return source;
        }

        int start = 0;
        int end = 0;

        for (int i=0; i<len; i++){
            for (int j=0; j<=i; j++){ //处理i行j列
                if (j==i){
                    table[i][i] = true;
                }else if (j==i-1){
                    table[i][j] = source.charAt(i) == source.charAt(j);
                } else {
                    table[i][j] = table[i-1][j+1] && source.charAt(i)==source.charAt(j);
                }
                if (table[i][j] && i-j > end-start){
                    start = j;
                    end = i;
                }
            }
        }
        /*for (int i=0; i<len; i++){
            table[i][i] = true;
            for (int j=0; j<i; j++){  // 对每列的j行处理
                if (i == j+1){
                    table[j][i] = chars[j] == chars[i];
                }else {
                    table[j][i] = table[j+1][i-1] && chars[j] == chars[i];
                }
                if (table[j][i] && i-j > end-start){
                    start = j;
                    end = i;
                }
            }
        }*/

        printTable(table, len);

        return source.substring(start, end+1);
    }

    public static void printTable(boolean[][] table, int len){
        //打印回文table
        for (int i=0; i<len; i++){
            for (int j=0; j<len; j++){
                if (table[i][j]){
                    System.out.print(1 + " ");
                }else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }
}
