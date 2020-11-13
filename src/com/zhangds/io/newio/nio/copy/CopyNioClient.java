package com.zhangds.io.newio.nio.copy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class CopyNioClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 6666));

        FileChannel channel = new FileInputStream("D:/IdeaProjects/javabase/read.text").getChannel();
        long startTime = System.currentTimeMillis();
        //transferTo 底层使用到零拷贝
        long transferCount = channel.transferTo(0, channel.size(), socketChannel);
        System.out.println("发送的总的字节数: " + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));

        channel.close();
    }
}
