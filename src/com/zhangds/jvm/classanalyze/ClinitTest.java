package com.zhangds.jvm.classanalyze;

public class ClinitTest {

    private int a = 1;
    private static int c = 3; // clinit静态变量，静态代码块的执行

    public static void main(String[] args) {
        int b = 2;
        ClinitTest clinitTest = new ClinitTest();
        clinitTest.test();
    }

    public void test(){
        int d = 3;
    }

    public ClinitTest(){
        a = 10;
        int d = 20;
    }
}
