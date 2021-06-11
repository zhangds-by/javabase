package com.zhangds.java8.demo.defaultmethod;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
