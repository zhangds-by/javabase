package com.zhangds.java8.demo.component;

/**
 * 函数式接口：接口只定义了一个抽象方法，可以有具体实现的方法，会用注解@FunctionalInterface标识
 * public interface Comparator<T> {
 *      int compare(T o1, T o2);
 * }
 *
 * public interface Runnable{
 *      void run();
 * }
 *
 * public interface Callable<V>{
 *      V call();
 * }
 * @author: zhangds
 * @date: 2020/12/8 16:12Predicate
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
