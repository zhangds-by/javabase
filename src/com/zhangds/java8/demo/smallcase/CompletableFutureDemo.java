package com.zhangds.java8.demo.smallcase;

import com.zhangds.common.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        List<User> userList = Arrays.asList(new User("1", "zhangds", 23),
                new User("1", "lisi", 24),
                new User("3", "wangwu", 24),
                new User("4", "zhangsan", 24)
        );

        Map<String, User> result = userList.parallelStream().collect(
                Collectors.groupingBy(User::getId, Collectors.collectingAndThen(
                        Collectors.reducing((t1, t2) -> t1.getAge() > t2.getAge() ? t1 : t2), Optional::get
                ))
        );

        userList.parallelStream().collect(
                Collectors.reducing((t1,t2) -> t1.getAge()>t2.getAge() ? t1 : t2)
        );

        System.out.println(result);
    }
}
