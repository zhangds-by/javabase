package com.zhangds.java8.demo.defaultmethod;

public interface E {
    default void hello() {
        System.out.println("Hello from E");
    }
}
