package com.zhangds.java8.demo.defaultmethod;

public interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}
