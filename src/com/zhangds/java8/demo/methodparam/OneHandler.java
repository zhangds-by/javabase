package com.zhangds.java8.demo.methodparam;

import com.zhangds.java8.demo.entities.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneHandler {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("苹果", "green", 1),
                new Apple("哈密瓜", "yellow", 10),
                new Apple("樱桃", "red", 0.1));

        System.out.println(filterApples(list, new GreenApplePredicate()));

        List<Apple> apples = filterApples(list, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
    }

    /**
     * java8 无需创建对象定制筛选条件，可以直接传递筛选条件表达式
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
