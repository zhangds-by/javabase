package com.zhangds.arth.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1、右边比左边的数大，直接加 1
 * 2、左边比右边的数大 且就有一个数，减 1
 */

public class L0013Roman2Integer {

    public static void main(String[] args) {
        System.out.println(Roman2Int("MCMXCIV"));
        System.out.println(Roman2Int("LVIII"));
        System.out.println(Roman2Int("III"));
    }

    public static int Roman2Int(String s){
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length();
        int res = map.get(s.charAt(len-1));

        for (int j=len-2; j>=0; j--){
            if (map.get(s.charAt(j)) < map.get(s.charAt(j+1))){
                res -= map.get(s.charAt(j));
            }else {
                res += map.get(s.charAt(j));
            }
        }
        return res;
    }

}
