package com.zhangds.annoandreflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 *
 * Create by zhangds
 * 2020-05-11 17:58
 **/
public class ClassDemo {


    public static void main(String[] args) throws Exception {

        String classpath = "com.zhangds.annoandreflect.Demo";

        Class clazz = Demo.class;
        //获取指定无返回值方法参数泛型信息
        Method method = clazz.getMethod("test01", Map.class, List.class);
        Type[] types = method.getGenericParameterTypes();
        for (Type paramType : types){
            if (paramType instanceof ParameterizedType){
                Type[] actualTypeArguments = ((ParameterizedType) paramType).getActualTypeArguments();
                for (Type actualType : actualTypeArguments){
                    System.out.println("泛型类型" + actualType);
                }
            }
        }
        /**
         * 应用场景：获取表名和字段注解可以拼接SQL语句，生成或查询表
         */

        //获取返回值
        Type returnType = method.getGenericReturnType();

        //获取类所有注解
        Annotation[] annotations = clazz.getAnnotations();

        //获取类的指定注解
        OwnAnno ownAnno = (OwnAnno) clazz.getAnnotation(OwnAnno.class);
        ownAnno.name();

        //获取属性上的注解
        Field field = clazz.getField("name");
        OwnFieldAnno annotation = field.getAnnotation(OwnFieldAnno.class);

        Class<Demo> demoClass = (Class<Demo>) Class.forName(classpath);
        //获取指定构造器
        Constructor<Demo> constructor = clazz.getDeclaredConstructor(String.class);

        //获取实例,默认无参构造
        Demo demo = demoClass.newInstance();
        //获取实例，指定构造器
        Demo demo1 = constructor.newInstance("zhangds");


        /**
         * 有返回值的方法参数泛型
         */
        Method method2 = Demo.class.getMethod("test02", null);
        Type returnGenericParameterTypes = method2.getGenericReturnType();

        // 遍历打印全部泛型
        if(returnGenericParameterTypes instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) returnGenericParameterTypes).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }


    }
}
