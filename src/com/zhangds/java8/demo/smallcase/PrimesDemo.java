package com.zhangds.java8.demo.smallcase;

import com.zhangds.java8.demo.handler.PrimeNumbersCollector;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * n个自然数按质数/非质数分区
 *
 * 分割数字增强可读性，只能在数字之间加 '_'
 * 以下不能加
 *      在数字的开头或结尾
 *      浮点文字中的小数点附近
 *      之前F或L后缀
 *      在需要一串数字的位置
 */
public class PrimesDemo {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
//            partitionPrimes(1_000_000);
//            PrimeNumbersCollector.primeNumbersCollector(1_000_000);
            PrimeNumbersCollector.primeNumbersCollector2(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println(
                "Fastest execution done in " + fastest + " msecs");
    }

    public static boolean isPrime(int candidate) { //限制除数不超过被测试数的平方根
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    //可以使用filter(p -> p <= candidateRoot)来筛选出小于被测数平方根的质数
//
//    public static boolean isPrime(List<Integer> primes, int candidate){
//        int candidateRoot = (int) Math.sqrt((double) candidate);
//        return takeWhile(primes, i -> i <= candidateRoot)
//                .stream()
//                .noneMatch(p -> candidate % p == 0);
//    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }
}
