package com.zhangds.java8.demo.defaultmethod;

import com.sun.org.apache.xpath.internal.functions.Function;

import java.util.List;

public interface Resizable {
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);
//    void setRelativeSize(int wFactor, int hFactor); // 新添方法
    default void setRelativeSize(int wFactor, int hFactor){
        setAbsoluteSize(getWidth() / wFactor, getHeight() / hFactor);
    }

    public static void paint(List<Resizable> l){
        l.forEach(r -> {
            r.setAbsoluteSize(42, 42);
        });
    }
}
