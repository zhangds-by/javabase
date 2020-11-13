package com.zhangds.io.newio.nio.group;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupNioServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6666;

    GroupNioServer(){
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GroupNioServer().listen();
    }

    public void listen(){
        try{
            while (true){
                int count = selector.select();
                if (count > 0){ //有客户端连接
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()){
                        SelectionKey key = keyIterator.next();
                        if (key.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + "上线");
                        }
                        
                        if (key.isReadable()){
                            readData(key);
                        }
                        keyIterator.remove();
                    }
                }else {
                    System.out.println("等待客户端连接......");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey key) {

        SocketChannel channel = null;

        try{
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int readCount = channel.read(buffer);
            if (readCount > 0){
                String msg = new String(buffer.array());
                System.out.println("客户端：" + msg);
                sendToOtherClients(msg, channel);
            }
        }catch (Exception e){
            try{
                System.out.println(channel.getRemoteAddress() + "下线了");
                key.cancel();
                channel.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void sendToOtherClients(String msg, SocketChannel selfChannel) throws IOException {
        System.out.println("服务器转发客户端线程" + Thread.currentThread().getName());
        for (SelectionKey key : selector.keys()){
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != selfChannel){
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(buffer);
            }
        }
    }
}
