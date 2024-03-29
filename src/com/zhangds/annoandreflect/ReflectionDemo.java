package com.zhangds.annoandreflect;

import com.zhangds.annoandreflect.entities.User;

/**
 *
 * Create by zhangds
 * 2020-05-11 16:52
 **/
public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String classpath = "com.zhangds.annoandreflect.entities.User";

        Class clazz = Class.forName(classpath); // ClassNotFoundException

        Class clazz1 = User.class;

        User user = new User();
        Class clazz2 = classpath.getClass();
        Class clazz3 = user.getClass();

        Class clazz4 = Integer.TYPE;

    }
}
