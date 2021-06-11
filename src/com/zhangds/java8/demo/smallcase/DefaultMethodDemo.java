package com.zhangds.java8.demo.smallcase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DefaultMethodDemo {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        // sort 是list接口的一个默认方法
        // naturalOrder() 是接口的一个静态方法
        numbers.sort(Comparator.naturalOrder());

        // stream 是Collection接口的一个默认方法
        numbers.stream().forEach(System.out::println);
    }
}
