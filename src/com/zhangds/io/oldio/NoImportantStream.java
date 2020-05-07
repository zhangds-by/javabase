package com.zhangds.io.oldio;

import java.io.*;

/**
 * Create by zhangds
 * 2020-05-07 11:00
 **/
public class NoImportantStream {

    /**
     * 数据流：读写顺序一致
     * @Author zhangds
     * @Date 2020/5/7 11:04
     * @Return
     */
    public void dataStreamMethod() throws IOException {
        //写入
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        DataOutputStream dos =new DataOutputStream(new BufferedOutputStream(baos));
        dos.writeUTF("编码辛酸泪");
        dos.writeInt(18);
        byte[] datas =baos.toByteArray();

        //读取
        DataInputStream dis =new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        String msg = dis.readUTF();
        int age = dis.readInt();
    }

    /**
     * 对象流：读写顺序一致，写入对象需要序列化
     * @Author zhangds
     * @Date 2020/5/7 11:07
     * @Return
     */
    public void ObjectStreamMethod() throws IOException {
        //写入
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        ObjectOutputStream dos =new ObjectOutputStream(new BufferedOutputStream(baos));
        dos.writeUTF("编码辛酸泪");
        dos.writeInt(18);
        byte[] datas =baos.toByteArray();

        //读取
        ObjectInputStream dis =new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
        String msg = dis.readUTF();
        int age = dis.readInt();

    }
}
