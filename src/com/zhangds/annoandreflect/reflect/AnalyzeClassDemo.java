package com.zhangds.annoandreflect.reflect;

import com.zhangds.annoandreflect.entities.ParamDefaultValue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 通过反射配合注解来为其设置默认值
 */
public class AnalyzeClassDemo {

    public static void applyDefaultValue(Object o) {
        Class sourceClass = o.getClass();
        //获取对象所有字段 包括父类
        ArrayList<Field> fields = new ArrayList<>();
        while (sourceClass != null){
            fields.addAll(Arrays.asList(sourceClass.getDeclaredFields()));
            sourceClass = sourceClass.getSuperclass();
        }

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ParamDefaultValue.class)) {
                try {
                    Object val = field.get(o);
                    if (val != null) {
                        continue;
                    }
                    Class type = field.getType();
                    if (type.isPrimitive()) {
                        continue;
                    }
                    String defVal = field.getAnnotation(ParamDefaultValue.class).value();

                    if (String.class.isAssignableFrom(type)) { // isAssignableFrom()方法是判断是否为某个类的父类
                        field.set(o, defVal);
                    } else if (Number.class.isAssignableFrom(type)) {
                        if (Byte.class.isAssignableFrom(type)) {
                            field.set(o, Byte.valueOf(defVal));
                        } else if (Float.class.isAssignableFrom(type)) {
                            field.set(o, Float.valueOf(defVal));
                        } else if (Short.class.isAssignableFrom(type)) {
                            field.set(o, Short.valueOf(defVal));
                        } else if (Integer.class.isAssignableFrom(type)) {
                            field.set(o, Integer.valueOf(defVal));
                        } else if (Double.class.isAssignableFrom(type)) {
                            field.set(o, Double.valueOf(defVal));
                        } else if (Long.class.isAssignableFrom(type)) {
                            field.set(o, Long.valueOf(defVal));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
