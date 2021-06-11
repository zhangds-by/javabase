package com.zhangds.java8.demo.handler;

import com.zhangds.java8.demo.smallcase.PrimesDemo;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/**
 * Collector<T, A, R>
 *     T : 流中元素的类型
 *     A : 累积部分结果的对象类型 Map<Boolean, List<Integer>> 接收质数/非质数的list
 *     R : collect操作最终结果的类型
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>,
        Map<Boolean, List<Integer>>> {

    /**
     * 创建累加器，初始化空列表
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){
            {
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }
        };
    }

    /**
     * 定义了如何收集流中元素的逻辑，可以访问收集过程的部分结果
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get(PrimesDemo.isPrime(acc.get(true), candidate)).add(candidate);
        };
    }

    /**
     * 收集器合并累加结果
     * @return
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
        };
    }

    /**
     * 收集过程最后无需转换，用identity函数收尾
     * @return
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    /**
     * 自定义收集器
     * @param n
     * @return
     */
    public static Map<Boolean, List<Integer>> primeNumbersCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    public static Map<Boolean, List<Integer>> primeNumbersCollector2(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        () -> new HashMap<Boolean, List<Integer>>() {{
                            put(true, new ArrayList<Integer>());
                            put(false, new ArrayList<Integer>());
                        }},
                        (acc, candidate) -> {
                            acc.get( PrimesDemo.isPrime(acc.get(true), candidate) )
                                    .add(candidate);
                        },
                        (map1, map2) -> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        });
    }
}
