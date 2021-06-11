package com.zhangds.java8.demo.smallcase;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeekDemo {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
                .map(x -> x + 17) // {19, 20, 21, 22}
                .filter(x -> x % 2 == 0) // {20, 22}
                .limit(3)
                .forEach(System.out::println); // 执行forEach()，整个流恢复运行

        // peek() 在每个元素恢复运行之前，插入一个执行动作
        numbers.stream()
                .peek(t -> System.out.println("after stream：" + t)) // 输出流数据源中的元素值
                .map(t ->  t+17)
                .peek(t -> System.out.println("after map：" + t)) // 输出map的结果
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)) // filter后流中元素
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x)) // 限制操作后元素
                .collect(Collectors.toList());
    }
}
