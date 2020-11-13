package com.zhangds.io.newio.nio.group;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class GroupNioClient {

    private final String HOST = "localhost";
    private final int PORT = 6666;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupNioClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + "出场");
    }

    public static void main(String[] args) throws Exception{

        GroupNioClient client = new GroupNioClient();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    client.read();
                    try {
                        Thread.currentThread().sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String msg = scanner.nextLine();
            client.send(msg);
        }
    }

    public void send(String msg){
        msg = username + " : " + msg;
        try{
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读取其他用户的信息相当于自己是一个服务器
     */
    public void read(){
        try{
            int readCount = selector.select();
            if (readCount > 0){ //有可用通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        socketChannel.read(buffer);
                        System.out.println(new String(buffer.array()).trim());
                    }
                    iterator.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
