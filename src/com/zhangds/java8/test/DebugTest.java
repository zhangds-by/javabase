package com.zhangds.java8.test;

import com.zhangds.common.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DebugTest {

    public static void main(String[] args) {
        List<Optional<User>> customers = Arrays.asList(
                Optional.of(new User("1", "日拱一兵", 18)),
                Optional.of(new User("2", "卑微的小开发", 22)),
                Optional.empty(),
                Optional.of(new User("3", "OOT", 21)),
                Optional.empty(),
                Optional.of(new User("4", "温柔一刀", 23)),
                Optional.empty()
        );

        long numberOf65PlusCustomers = customers
                .stream()
                .flatMap(c -> c
                        .map(Stream::of)
                        .orElseGet(Stream::empty))
                .filter(c -> c.getAge() > 18)
                .count();

        System.out.println(numberOf65PlusCustomers);
    }
}
