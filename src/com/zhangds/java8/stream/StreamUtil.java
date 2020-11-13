package com.zhangds.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StreamUtil {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.stream().map(Either.lift(t -> {
            return testException(t);
        }))
        .forEach(System.out::println); // 打印异常信息
//        list.stream().map(wrap(t -> {
//            return testException(t);
//        }))
//        .forEach(System.out::println);
    }

    public static <T,R> Function<T,R> wrap(CheckedFunction<T,R> checkedFunction) {
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                System.out.println("抛出异常");
                throw new RuntimeException(e);
            }
        };
    }

    public static Integer testException(Integer t) throws Exception{
        return t/0;
    }

}
