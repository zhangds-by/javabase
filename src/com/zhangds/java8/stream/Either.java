package com.zhangds.java8.stream;


import java.util.Optional;
import java.util.function.Function;

/**
 * 处理 Stream 异常，但不终止Stream
 *  Either<L, R> 结果可以是左值也可以是右值，一般左值 Exception 右值正常处理的数据
 *  用 Optional 类型来包装左右节点返回的值
 * @author: zhangds
 * @date: 2020/11/12 8:43
 */
public class Either<L, R> {
    private final L left;
    private final R right;
    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }
    public static <L,R> Either<L,R> Left( L value) {
        return new Either(value, null);
    }
    public static <L,R> Either<L,R> Right( R value) {
        return new Either(null, value);
    }
    public Optional<L> getLeft() {
        return Optional.ofNullable(left);
    }
    public Optional<R> getRight() {
        return Optional.ofNullable(right);
    }
    public boolean isLeft() {
        return left != null;
    }
    public boolean isRight() {
        return right != null;
    }
    public <T> Optional<T> mapLeft(Function<? super L, T> mapper) {
        if (isLeft()) {
            return Optional.of(mapper.apply(left));
        }
        return Optional.empty();
    }
    public <T> Optional<T> mapRight(Function<? super R, T> mapper) {
        if (isRight()) {
            return Optional.of(mapper.apply(right));
        }
        return Optional.empty();
    }

    /**
     * 处理左边异常
     */
    public static <T,R> Function<T, Either> lift(CheckedFunction<T,R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(ex);
            }
        };
    }

    /**
     * 返回异常包含异常信息和原始值
     */
    public static <T,R> Function<T, Either> liftWithValue(CheckedFunction<T,R> function) {
        return t -> {
            try {
                return Either.Right(function.apply(t));
            } catch (Exception ex) {
                return Either.Left(Pair.of(ex, t));
            }
        };
    }

    public String toString() {
        if (isLeft()) {
            return "Left(" + left +")";
        }
        return "Right(" + right +")";
    }


}
