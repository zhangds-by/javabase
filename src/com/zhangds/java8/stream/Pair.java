package com.zhangds.java8.stream;

public class Pair<F,S> {
    public final F fst;
    public final S snd;
    private Pair(F fst, S snd) {
        this.fst = fst;
        this.snd = snd;
    }
    public static <F,S> Pair<F,S> of(F fst, S snd) {
        return new Pair<>(fst,snd);
    }
}
