package com.zhangds.arth.leetcode;

/**
 * 整型数转罗马数字，范围从 1 到 3999
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 1、相连的数相加所得  Ⅲ = 3
 * 2、小的标识在右，相加所得    Ⅵ = 6
 * 3、小(Ⅰ、Ⅹ、C)的标识在大的标识左边，大数减小数所得
 * 4、多位数可以将每一位分离出来处理
 */
public class L0012Integer2Roman {

    public static void main(String[] args) {
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));

        System.out.println(intToRoman2(3));
        System.out.println(intToRoman2(4));
        System.out.println(intToRoman2(9));
        System.out.println(intToRoman2(58));
        System.out.println(intToRoman2(1994));

        System.out.println(intToRoman3(3));
        System.out.println(intToRoman3(4));
        System.out.println(intToRoman3(9));
        System.out.println(intToRoman3(58));
        System.out.println(intToRoman3(1994));
    }

    public static String intToRoman(int x){
        String M[] = {"", "M", "MM", "MMM"}; //千位
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; //百位
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; //十位
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; //个位
        return M[x/1000] + C[x/100%10] + X[x/10%10] + I[x%10];
    }

    public static String intToRoman2(int x){
        final int[] radix = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String res = "";
        for (int i=0; x>0; i++){
            int size = x/radix[i];
            for (; size>0; size--){
                res += symbol[i];
            }
            x %= radix[i];
        }
        return res;
    }

    public static String intToRoman3(int x){
        String[][] base = new String[][]{
                {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 个位的表示
                {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 十位的表示
                {"C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 百倍的表示
                {"M", "MM", "MMM", "", "", "", "", "", ""}}; // 千位的表示
        String res = "";
        for (int i=0; x!=0; i++){
            if (x%10 != 0){
                res = base[i][x%10 - 1] + res;
            }
            x /= 10;
        }
        return res;
    }
}
