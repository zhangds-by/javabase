package com.zhangds.jvm;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestClassLoader {

    public static Map<String, FileDefine> fileDefineMap = new HashMap<>();

    public static void main(String[] args){
//        testSystem();
        System.out.println(FileDefine.WATCH_PACKAGE);
        initMap();
        // 使用ScheduledThreadPoolExecutor来进行周期性的监控文件是否修改
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new WatchDog(fileDefineMap), 0,500, TimeUnit.MICROSECONDS);
    }

    /**
     * System 系统属性
     * @author: zhangds
     * @date: 2021/6/9 11:46
     */
    public static void testSystem(){
        //获取指定环境变量的值
        System.out.println( System.getenv("JAVA_HOME") );
        System.out.println( System.getProperties().getProperty("user.home") );
        System.out.println( System.getProperties().getProperty("user.dir") );
        System.out.println( System.getProperties().getProperty("java.ext.dirs") );
        //获取所有系统属性
        Properties props = System.getProperties();
        System.out.println(props);
    }

    public static void initMap(){
        File file = new File(FileDefine.WATCH_PACKAGE);
        File[] files = file.listFiles();
        for (File watchFile : files){
            long l = watchFile.lastModified();
            String name = watchFile.getName();
            FileDefine fileDefine = new FileDefine(name,l);
            fileDefineMap.put(name,fileDefine);
        }
    }
}
