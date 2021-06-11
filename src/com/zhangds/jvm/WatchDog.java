package com.zhangds.jvm;

import com.zhangds.jvm.watchfile.ITest;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

public class WatchDog implements Runnable {

    private Map<String, FileDefine> fileDefineMap;

    public WatchDog(Map<String, FileDefine> fileDefineMap){
        this.fileDefineMap = fileDefineMap;
    }

    @Override
    public void run() {
        File file = new File(FileDefine.WATCH_PACKAGE);
        File[] files = file.listFiles();
        for (File f : files){
            long newTime = f.lastModified();
            FileDefine fileDefine = fileDefineMap.get(f.getName());
            Long oldTime = fileDefine.getLastDefine();
            //如果文件被修改了,那么重新生成累加载器加载替换新文件
            if (newTime != oldTime){
                fileDefine.setLastDefine(newTime);
                loadMyClass2();
            }
        }
    }

    public void loadMyClass(){
        try {
            CustomClassLoader customClassLoader = new CustomClassLoader();
            Class<?> cls = customClassLoader.loadClass("com.zhangds.jvm.watchfile.Test",false);
            Object test = cls.newInstance();
            Method method = cls.getMethod("test");
            method.invoke(test);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void loadMyClass2(){
        try {
            CustomClassLoader customClassLoader = new CustomClassLoader();
            Class<?> cls = customClassLoader.loadClass("com.zhangds.jvm.watchfile.Test",false);
            ITest test = (ITest) cls.newInstance();  // ClassCastException 通过接口实现实例的类加载器相同
            test.test();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
