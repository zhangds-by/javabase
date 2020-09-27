package com.zhangds.arth.leetcode;

/**
 * 判断一个有符号整型数是否是回文
 */
public class L0009PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(-1001));

        System.out.println(isPalindrome2(12321));
        System.out.println(isPalindrome2(1221));
        System.out.println(isPalindrome2(10));
        System.out.println(isPalindrome2(-1001));
        System.out.println(isPalindrome2(0));
    }

    public static boolean isPalindrome(int num){
        if (num < 0) return false;
        int res = 0;
        int x = num;
        while (x != 0){
            res = res*10 + x%10;
            x /= 10;
        }
        return num==res;
    }

    public static boolean isPalindrome2(int num){
        if (num<0 || (num!=0 && num%10==0)) return false; //0也是回文
        int res = 0;
        int x = num;
        while (x > res){
            res = res*10 + x%10;
            x /= 10;
        }
        return res == x || res/10 == x; //奇偶位数回文
    }
}
