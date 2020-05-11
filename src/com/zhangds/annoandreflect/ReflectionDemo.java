package com.zhangds.annoandreflect;

/**
 * Create by zhangds
 * 2020-05-11 16:52
 **/
public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String classpath = "com.zhangds.annoandreflect.User";

        Class clazz = Class.forName(classpath);

        Class clazz1 = User.class;

        User user = new User();
        Class clazz2 = classpath.getClass();
        Class clazz3 = user.getClass();

    }
}
