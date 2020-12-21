package com.zhangds.collectiondemo;

import java.util.*;

/**
 * 去重
 * @author: zhangds
 * @date: 2020/12/10 16:51
 */
public class WithoutDuplicates {
    public static void main(String[] args) {

    }

    public void removeDuplicateOne(){
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

        LinkedHashSet<Integer> numbersSet = new LinkedHashSet<>(numbersList);

        ArrayList<Integer> newList = new ArrayList<>(numbersList);
    }

    private static void removeDuplicateTwo(List<String> list) {
        HashSet<String> set = new HashSet<String>(list.size());
        List<String> result = new ArrayList<String>(list.size());

        for (String str : list){
            if (set.add(str)){
                result.add(str);
            }
        }
        list.clear();
        list.addAll(result);
    }
}
