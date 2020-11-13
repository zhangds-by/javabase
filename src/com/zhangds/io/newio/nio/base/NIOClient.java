package com.zhangds.io.newio.nio.base;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws Exception {
        //网络通道
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        //连接服务器
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9527);
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("正在连接中......");
            }
        }

        //发送数据，将buffer写入Channel
        String msg = "服务器，接好了";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
