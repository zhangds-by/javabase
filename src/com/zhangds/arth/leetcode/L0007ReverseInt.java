package com.zhangds.arth.leetcode;

/**
 * 逆序整数，如果逆出返回0
 *
 * 123      321
 * -123     -321
 * 120      21
 */
public class L0007ReverseInt {

    public static void main(String[] args) {
        System.out.println(reverseInt(1234));
        System.out.println(reverseInt(-1234));
        System.out.println(reverseInt(120));
        System.out.println(reverseInt(12345678111111L));
    }

    public static int reverseInt(long num){
        long res = 0L;
        while(num!=0){
            res = res*10 + num%10;
            num /= 10;
        }
        return res >Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int)res;
    }
}
