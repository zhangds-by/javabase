package com.zhangds.jvm.classanalyze;

// out/production/javabase/com/zhangds/jvm/classanalyze/StackStruct.class
// D:\IdeaProjects\javabase\out\production\javabase\com\zhangds\jvm\classanalyze\StackStruct.class
// StackStruct.class

/**
 * 分析字节码文件
 * 1、javap -c -verbose out/production/javabase/com/zhangds/jvm/classanalyze/StackStruct.class
 * 2、选中java文件，view -> show bytecode with jclasslib
 *
 * @author zhangds
 * @date 2022/1/11 8:55
 */
public class StackStruct {

    public static void main(String[] args) {
        //int i = 2 + 3;
        int i = 2;
        int j = 3;
        int k = i + j;

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello");
    }

}
