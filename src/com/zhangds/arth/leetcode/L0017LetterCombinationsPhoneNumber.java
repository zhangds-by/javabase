package com.zhangds.arth.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  电话按键，组合出所有不同结果
 * "23" => ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class L0017LetterCombinationsPhoneNumber {

    static String[] map = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));   //回溯法
        System.out.println(letterCombinations2("23"));  //迭代法
        System.out.println(letterCombinations3("23"));   //回溯法

    }

    /**
     * 回溯，回溯的终点是结果 字符串长度 和 按键长度
     * <p>
     * 1、定义一个解空间，在解空间中搜索
     * 解空间：向量[a1,a2,....,an]，向量的每个元素都是问题的部分解，当数组的每一个元素都填满(得到全部解)时，问题得到了解答
     * void backtrack(int i,int n,other parameters){
     * if( i == n){    //解空间上的所有位置的解都已经求出
     * record answer; //get one answer
     * return;
     * }
     * for(next ans in position i of solution space){
     * backtrack(i+1,n,other parameters);  //解空间第i个位置上的解
     * }
     * }
     */
    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        helper(list, digits, "");   //开始求出第一位的结果
        return list;
    }

    private static void helper(List<String> list, String digits, String ans) {
        if (ans.length() == digits.length()) {  //数字长度 等于 按键长度 求解结束
            list.add(ans);
            return;
        }

        for (char c : map[digits.charAt(ans.length()) - '2'].toCharArray()) {
            helper(list, digits, ans + c);
        }

    }

    public static List<String> letterCombinations3(String digits) {
        List<String> result = new LinkedList<>();

        if (digits == null || digits.length() < 1) {
            return result;
        }

        StringBuilder sb = new StringBuilder();
        letterCombinations3(digits, 0, sb, result);
        return result;
    }

    public static void letterCombinations3(String digits, int index, StringBuilder builder, List<String> result) {
        if (digits.length() == index){
            result.add(builder.toString());
            return ;
        }

        String enStr = map[digits.charAt(index) - '2'];

        //迭代重点在于 循环体规则的实现
        for (int i=0; i<enStr.length(); i++){
            builder.append(enStr.charAt(i));
            letterCombinations3(digits, index+1, builder, result);
            builder.deleteCharAt(builder.length() - 1); //去掉末尾字符
        }
        //ad ae af
    }

    /**
     * 队列迭代：根据上一次队列中的值，该值拼接当前可选值来不断迭代其结果
     *  递归 = 递归头 + 递归体
     *      树结构，重复 递推 和 回归，递推到底部就回归
     *
     *  迭代 = 循环变量 + 循环体（变量更新规则） + 环终止条件
     *      环结构，从初始状态开始，每次迭代都遍历这个环，并更新状态，多次迭代直到到达结束状态
     */
    public static List<String> letterCombinations2(String digits){
        if (digits.length() == 0){
            Collections.emptyList();
        }
        LinkedList<String> list = new LinkedList<>();
        list.add("");
        char[] chars = digits.toCharArray();
        for (int i=0; i<chars.length; i++){ //初始状态 length=0 终止条件 i
            char ch = chars[i];
            while (list.getFirst().length() == i){  //循环体
                String pop = list.removeFirst();
                for (char c : map[ch-'2'].toCharArray()){
                    list.addLast(pop + c);
                }
            }
        }
        return list;
    }

}


