package com.zhangds.java8.designpattern.responsechain;

/**
 * 一种创建处理对象序列（比如操作序列）的通用方案。一个处理对象可能需要在完成一些工作之后，将结果传递给另一个对象，
 */
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;
    public void setSuccessor(ProcessingObject<T> successor){
        this.successor = successor;
    }
    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }
    abstract protected T handleWork(T input);

    public static class HeaderTextProcessing extends ProcessingObject<String> {
        public String handleWork(String text){
            return "From Raoul, Mario and Alan: " + text;
        }
    }
    public static class SpellCheckerProcessing extends ProcessingObject<String> {
        public String handleWork(String text){
            return text.replaceAll("labda", "lambda");
        }
    }
}
