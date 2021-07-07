package com.zhangds.interview;

public class EqualsTest {

    public static void main(String[] args) {
        Integer a = 127;
        Integer a2 = 127;
        Integer b = 128;
        Integer b2 = 128;

        // 输出内存地址
        System.out.println(System.identityHashCode(a) + " ==== " + System.identityHashCode(a2));
        System.out.println(System.identityHashCode(b) + " ==== " + System.identityHashCode(b2));

        // 自动装箱拆箱
        int i = 127;
        int i2 = Integer.valueOf(i);
    }
}
