package com.zhangds.java8.demo.smallcase;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamCreateDemo {

    public static void main(String[] args) {

        //文件创建流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("copy.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }catch (Exception e){
            e.getStackTrace();
        }

        //函数创建流：用iterate方法生成斐波纳契元组序列中的前20个元素
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

        //生成斐波纳契元组序列
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
}
