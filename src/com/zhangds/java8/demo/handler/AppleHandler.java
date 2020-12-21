package com.zhangds.java8.demo.handler;

import com.zhangds.java8.demo.component.Predicate;
import com.zhangds.java8.demo.entities.Apple;

import java.util.ArrayList;
import java.util.List;

public class AppleHandler {

    public static void main(String[] args) {
        List<Apple> list = new ArrayList<>();
        list.add(new Apple("苹果", "green", 1));
        list.add(new Apple("哈密瓜", "yellow", 10));
        list.add(new Apple("樱桃", "red", 0.1));

        System.out.println(filterApple(list, AppleHandler::isHeavyApple));
        System.out.println(filterApple(list, AppleHandler::isGreenApple));

        filterApple(list, (Apple a) -> "green".equals(a.getColor()));

        absFilter(list, (Apple apple) -> "green".equals(apple.getColor()));
    }

    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight() > 8;
    }

    static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory){
            if (predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> absFilter(List<T> inventory, Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T t: inventory){
            if(predicate.test(t)){
                result.add(t);
            }
        }
        return result;
    }
}
