package com.zhangds.io.oldio;

import java.io.UnsupportedEncodingException;

/**
 * Create by zhangds
 * 2020-05-07 09:00
 **/
public class IoBase {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String msg = "信息";

        //编码：字节数组
        byte[] datas = msg.getBytes();
        //解码：字符串
        msg = new String(datas, 0, datas.length, "utf-8");
    }
}
