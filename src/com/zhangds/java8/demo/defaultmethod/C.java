package com.zhangds.java8.demo.defaultmethod;

/**
 * 相同函数签名的情况：
 *      类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
 *      子接口的优先级更高: 函数签名相同时，优先选择拥有最具体实现的默认方法的接口，即如果B继承了A，那么B就比A更加具体。
 *      继承了多个接口的类必须通过显式覆盖和调用期望的方法
 *
*  类/父类 > 子接口
 *
 *  A,B,F ： 菱形继承问题
 *
 *  解决冲突的三大原则：
 *      类或父类中显式声明的方法，其优先级高于所有的默认方法.
 *      选择提供最具体实现的默认方法的接口。
 *      只能在你的类中覆盖该默认方法，显式地指定在你的类中使用哪一个接口中的方法。
 */
public class C extends D implements A, E {
    public static void main(String... args) {
        new C().hello();
    }

    @Override
    public void hello() {
//        System.out.println("Hello from C");
        // A和E没有互相继承，出现了冲突，需要显示调用
        E.super.hello();
    }
}
