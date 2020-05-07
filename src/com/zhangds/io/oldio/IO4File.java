package com.zhangds.io.oldio;

import java.io.File;

/**
 * Create by zhangds
 * 2020-05-07 09:14
 **/
public class IO4File {

    public static void main(String[] args) {
        //创建File对象
        File src = new File(new File("F:/ideaworkplace/javabase/src"), "com.zhangds");

        //File方法, 上层目录存在创建成功
        src.mkdir();
        //不存在统一创建
        src.mkdirs();

    }

    /**
     * 打印目录和文件名
     * @Author zhangds
     * @Date 2020/5/7 9:47
     * @Return
     */
    public void printSrc(File src, int deep) {
        for (int i=0; i<deep; i++){
            System.out.print("-");
        }

        System.out.println(src.getName());
        if (src == null || !src.exists()) { //递归头
            return;
        } else if (src.isDirectory()) {
            for (File file : src.listFiles()) {
                printSrc(file, deep+1); //递归体
            }
        }
    }

    private static long length = 0;

    /**
     * 统计文件大小
     * @Author zhangds
     * @Date 2020/5/7 9:53
     * @Return
     */
    public void count(File src){
        if (src!=null && src.exists()){
            if (src.isFile()){
                length += src.length();
            }else {
                for (File file : src.listFiles()){
                    count(src);
                }
            }
        }
    }
}
