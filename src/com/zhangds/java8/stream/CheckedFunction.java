package com.zhangds.java8.stream;


/**
 * 处理异常并终止 Stream
 * @author: zhangds
 * @date: 2020/11/12 9:07
 */
@FunctionalInterface
public interface CheckedFunction <T,R>{

    R apply(T t) throws Exception;

}
