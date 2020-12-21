package com.zhangds.java8.demo.smallcase;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * [1, 2, 3]和[3, 4] -> [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<int[]> list = numbers1.stream()
                .flatMap( n1 -> numbers2.stream().map( n2 -> new int[]{n1, n2}))
                .collect(Collectors.toList());
        list.stream().forEach(System.out::println);

    }
}
