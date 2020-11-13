package com.zhangds.io.newio.nio.copy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class CopyNioServer {

    public static void main(String[] args) throws Exception{

        InetSocketAddress address = new InetSocketAddress(6666);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(address);

        ByteBuffer buffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount = 0;
            while (readCount != -1){
                try{
                    readCount = socketChannel.read(buffer);
                }catch (Exception e){
                    break;
                }
            }
            buffer.rewind();
        }
    }
}
