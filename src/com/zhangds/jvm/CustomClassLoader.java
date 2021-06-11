package com.zhangds.jvm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义类加载器，继承ClassLoader类，重写loadClass方法
 * @author: zhangds
 * @date: 2021/6/8 19:01
 */
public class CustomClassLoader extends ClassLoader {

    private static final String CLASS_FILE_SUFFIX = ".class";
    //AppClassLoader的父类加载器
    private ClassLoader extClassLoader;

    public CustomClassLoader(){
        ClassLoader cl = String.class.getClassLoader();
        if (cl == null){
            cl = getSystemClassLoader();
            while (cl.getParent() != null){
                cl = cl.getParent();
            }
        }
        this.extClassLoader = cl;
    }

    protected Class<?> loadClass(String name, boolean resolve){
        Class cls = findLoadedClass(name);
        if (cls != null){
            return cls;
        }
        if ("com.zhangds.jvm.watchfile.ITest".equals(name)){ // 指定接口的类加载器由AppClassLoader加载
            try {
                cls = getSystemClassLoader().loadClass(name);
            } catch (ClassNotFoundException e) {

            }
            return cls;
        }

        //获取ExtClassLoader
        ClassLoader extClassLoader = getExtClassLoader() ;

        try {
            cls = extClassLoader.loadClass(name);
            if (cls != null){
                return cls;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cls = findClass(name);
        return cls;
    }

    public ClassLoader getExtClassLoader(){
        return extClassLoader;
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] bt = loadClassData(name);
        return defineClass(name, bt, 0, bt.length);
    }

    private byte[] loadClassData(String className) {
        // 读取Class文件呢
        InputStream is = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/")+CLASS_FILE_SUFFIX);
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        // 写入byteStream
        int len =0;
        try {
            while((len=is.read())!=-1){
                byteSt.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 转换为数组
        return byteSt.toByteArray();
    }
}
