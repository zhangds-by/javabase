package com.zhangds.java8.designpattern.stategy;

public class Test {

    public static void main(String[] args) {
        Validator validator = new Validator((String s) -> s.matches("[a-z]+"));
        boolean b1 = validator.validate("aaaa");
    }
}
