package com.zhangds.java8.designpattern.stategy;

public class IsAllLowerCase implements ValidationStrategy {
    @Override
    public boolean execute(String s){
        return s.matches("[a-z]+");
    }
}
