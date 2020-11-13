package com.zhangds.annoandreflect;

public class ClassLoaderDemo {
    public static void main(String[] args) {

        //当前类的类加载器
        ClassLoader curCL = ClassLoaderDemo.class.getClassLoader();

        //系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

        //系统加载器的父类加载器 -> 扩展加载器
        ClassLoader parent = systemClassLoader.getParent();

        //扩展加载器的父类加载器 -> 根加载器
        ClassLoader root = parent.getParent();

        //JDK内置类加载器
        ClassLoader classLoader = Object.class.getClassLoader();

        System.out.println("当前类加载器" + curCL);
        System.out.println("系统类加载器" + curCL);
        System.out.println("扩展类加载器" + curCL);
        System.out.println("根类加载器" + curCL);
        System.out.println("JDK内置类加载器" + curCL);

        //类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
