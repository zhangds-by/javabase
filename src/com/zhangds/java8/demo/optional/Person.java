package com.zhangds.java8.demo.optional;

import java.util.Optional;

public class Person {
    // car为null时，Optional.empty()返回一个缺失值的Optional对象
    private Optional<Car> car;
    private int age;
    public Optional<Car> getCar() { return car; }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
