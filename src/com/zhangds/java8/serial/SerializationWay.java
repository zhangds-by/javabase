package com.zhangds.java8.serial;

import java.io.*;
import java.util.Date;

/**
 * 序列化和反序列化
 * @author: zhangds
 * @date: 2021/6/18 11:13
 */
public class SerializationWay {

    public static void main(String[] args) throws Exception {

        //Java Object Serialization 会使用对象中的 serialVersionUID 常量属性作为该对象的版本号，
        // 进行反序列化时会校验该版本号是否一致，如果不一致会导致序列化失败，抛出InvalidClassException异常

        // Serialization
        FileOutputStream f = new FileOutputStream("tmp");
        ObjectOutput oop = new ObjectOutputStream(f);
        oop.writeObject("today");
        oop.writeObject(new Date());
        oop.flush();

        // Deserialization
        FileInputStream in = new FileInputStream("tmp");
        ObjectInputStream s = new ObjectInputStream(in);
        String today = (String)s.readObject();
        Date date = (Date)s.readObject();
    }
}
