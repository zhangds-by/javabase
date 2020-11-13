package com.zhangds.io.newio.bio.copy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CopyServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            byte[] buffer = new byte[4096];
            while (true){
                int readCount = dataInputStream.read(buffer, 0, buffer.length);
                if (readCount == -1){
                    break;
                }
            }
            System.out.println("读取客户端上传文件内容：" + new String(buffer));
        }
    }
}
