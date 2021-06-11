package com.zhangds.java8.demo.defaultmethod;

public interface F extends A {

    default void hello() {
        System.out.println("Hello from F");
    }
}
