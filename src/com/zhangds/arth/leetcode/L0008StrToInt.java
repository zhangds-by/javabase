package com.zhangds.arth.leetcode;

/**
 * 实现一个atoi函数，将字符串转成整形
 *
 * 1、空格
 * 2、以"+" , "-", 数字开头，只返回数字部分，需要将符号去掉，否则返回0
 * 3、如果超过最大范围，则返回Integer.MAX_VALUE/Integer.MIN_VALUE
 *
 */
public class L0008StrToInt {

    public static void main(String[] args) {
        System.out.println(atoi("   +1234"));
        System.out.println(atoi("4193 with words"));
        System.out.println(atoi("   -42"));
        System.out.println(atoi("   -91283472332"));
        System.out.println(atoi("words and 987"));
    }

    public static int atoi(String source){

        int len = source.length();
        int res = 0;
        int sign = 1; //标识正负

        char[] chars = source.toCharArray();

        int i=0;
        while (i<len && chars[i] == ' '){ //去除空格
            i++;
        }
        while (i<len && (chars[i] == '-' || chars[i] == '+')){ //去除加减号，并标识符号
            sign = chars[i++] == '-'? -1 : 1;
        }

        while(i<len){
            if (chars[i] > '9' || chars[i] < '0'){ //不是数字不读取
                break;
            }
            if (sign==1 ? res > Integer.MAX_VALUE : res < Integer.MIN_VALUE){ //超过范围
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            int tmp = chars[i] -'0';
            res = res*10 + tmp;
            i++;
        }

        return sign*res;

    }
}
