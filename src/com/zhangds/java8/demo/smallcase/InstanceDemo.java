package com.zhangds.java8.demo.smallcase;

import com.zhangds.java8.demo.entities.Trader;
import com.zhangds.java8.demo.entities.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InstanceDemo {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

//        (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        transactions.stream().filter(t -> 2011 == t.getYear())
                .sorted(Comparator.comparing(Transaction::getValue)) //排序不能直接使用方法引用，需要调用comparing
                .collect(Collectors.toList());
//        (2) 交易员都在哪些不同的城市工作过？
        transactions.stream().
                map(t -> t.getTrader().getCity())
                .distinct() // 需要去重
                .collect(Collectors.toList());

        transactions.stream().
                map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet()); //或者转换为set集合
//        (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        transactions.stream()
                .map(Transaction::getTrader) //提取交易员
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName)) //不提取交易员，无法使用交易员的方法引用
                .collect(Collectors.toList());

//        (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1+n2); // 得到一个拼接所有名字的字符串

        //效率不高，使用StringBuilder
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining()); // joining内部使用StringBuilder
//        (5) 有没有交易员是在米兰工作的？
        transactions.stream()
                .anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
//        (6) 打印生活在剑桥的交易员的所有交易额。
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(t -> t.getValue())
                .forEach(System.out::println);
//        (7) 所有交易中，最高的交易额是多少？
        int i = transactions.stream()
                .mapToInt(t -> t.getValue())
                .max()
                .orElse(0);

        Integer integer = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElse(0);

        System.out.println("第一种" + i + "第二种" + integer.intValue());
//        (8) 找到交易额最小的交易。
        transactions.stream()
                .reduce((t1, t2) -> t1.getValue()<t2.getValue() ? t1 : t2);

        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        transactions.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Transaction::getValue)));

        transactions.stream()
                .collect(Collectors.summingInt(Transaction::getValue));

    }
}
