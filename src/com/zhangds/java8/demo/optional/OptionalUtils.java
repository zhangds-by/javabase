package com.zhangds.java8.demo.optional;

import java.util.Optional;

public class OptionalUtils {

    /**
     * 将String转换为Integer，并返回一个Optional对象
     * @param s
     * @return
     */
    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
