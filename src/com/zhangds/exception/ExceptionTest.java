package com.zhangds.exception;

// javap -c ExceptionTest.class
// 插件 https://github.com/ingokegel/jclasslib

public class ExceptionTest {

    public void tryFinally() {
        try {
            tryItOut();
        } finally {
            wrapItUp();
        }
    }
    /*
    在try-finally中，try块中抛出的异常会首先保存在local variable中，然后执行finally块，执行完毕后重新抛出异常。
    public void tryFinally();
        Code:
           0: aload_0
           1: invokevirtual #2                  // Method tryItOut:()V
           4: aload_0
           5: invokevirtual #3                  // Method wrapItUp:()V
           8: goto          18
          11: astore_1      // astore_1会把抛出的异常对象保存到local variable数组的第二个元素
          12: aload_0
          13: invokevirtual #3                  // Method wrapItUp:()V
          16: aload_1
          17: athrow
          18: return
        Exception table:    // 如果是位于0到4字节之间的命令抛出了任何类型（any type）的异常，会跳转到11字节处继续运行
           from    to  target type
               0     4    11   any
     */

    public void tryFinally2() {
        try {
            tryItOut();
            return;
        } finally {
            wrapItUp();
        }
    }
    /*
    public void tryFinally2();
        Code:
           0: aload_0
           1: invokevirtual #2                  // Method tryItOut:()V
           4: aload_0
           5: invokevirtual #3                  // Method wrapItUp:()V
           8: return       //finally 执行在return前
           9: astore_1
          10: aload_0
          11: invokevirtual #3                  // Method wrapItUp:()V
          14: aload_1
          15: athrow
        Exception table:
           from    to  target type
               0     4     9   any
     */

    public void tryFinally3() {
        try {
            tryItOut();
        } catch (Exception e) {
            handleExc(e);
        } finally {
            wrapItUp();
        }
    }
    /*
     public void tryFinally3();
        Code:
           0: aload_0
           1: invokevirtual #2                  // Method tryItOut:()V
           4: aload_0
           5: invokevirtual #3                  // Method wrapItUp:()V
           8: goto          31
          11: astore_1
          12: aload_0
          13: aload_1
          14: invokevirtual #5                  // Method handleExc:(Ljava/lang/Exception;)V
          17: aload_0
          18: invokevirtual #3                  // Method wrapItUp:()V
          21: goto          31
          24: astore_2
          25: aload_0
          26: invokevirtual #3                  // Method wrapItUp:()V
          29: aload_2
          30: athrow
          31: return
        Exception table:
           from    to  target type
               0     4    11   Class java/lang/Exception  // catch监听 0 ~ 4 字节类型为TextExc的异常。
               0     4    24   any
              11    17    24   any      // finally为 0 ~ 4 以及 11 ~ 17 字节任何类型的异常。
     */


    public void tryItOut() {
        int i = 10/0;
    }

    public void wrapItUp() {}

    public void handleExc(Exception e) {}
}
