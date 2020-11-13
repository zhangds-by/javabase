package com.zhangds.io.newio.nio.base;

import com.zhangds.java8.stream.StreamUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws Exception {

        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //获取Selector对象
        Selector selector = Selector.open();

        //通过Channel获取ServerSocket，并绑定服务器监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(9527));

        //配置通道
        //非阻塞
        serverSocketChannel.configureBlocking(false);
        //注册Seletor监听事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while(true){

            //等待1s，没有事件发生
            if (selector.select(1000) == 0){
                System.out.println("没有客户端连接");
                continue;
            }

            //获取相应的 SelectionKey 集合，获取到关注的事件集合，通过SelectionKey 获取 Channel 读取数据
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()){ //OP_ACCEPT，新客户端连接
                    // 为连接的客户端生成一个 SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端连接成功，生成SocketChannel" + socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接注册的selectionKey" + selector.keys().size());
                }
                if (key.isReadable()){ // OP_READ
                    // 通过key反向获取channel
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 获取channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socketChannel.read(buffer);
                    System.out.println("客户端：" + new String(buffer.array()));
                }
                //移除当前的selectionKey， 防止重复操作
                keyIterator.remove();
            }


        }
    }

}
