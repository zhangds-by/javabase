package com.zhangds.annoandreflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Create by zhangds
 * 2020-05-11 16:39
 **/
@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OwnFieldAnno {
    String value();
}
