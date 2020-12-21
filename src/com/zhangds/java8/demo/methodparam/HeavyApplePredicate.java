package com.zhangds.java8.demo.methodparam;

import com.zhangds.java8.demo.entities.Apple;

public class HeavyApplePredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 10;
    }
}
