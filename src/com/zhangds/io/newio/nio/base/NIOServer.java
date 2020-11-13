package com.zhangds.io.newio.nio.base;

import java.net.InetSocketAddress;
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
                if (key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                }
            }

        }
    }
}
