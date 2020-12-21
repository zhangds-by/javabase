package com.zhangds.java8.demo.methodparam;

import com.zhangds.java8.demo.entities.Apple;

public class GreenApplePredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
