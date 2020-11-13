package com.zhangds.io.newio.bio.copy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

public class CopyClient {

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost", 6666);

        InputStream in = new FileInputStream("D:/IdeaProjects/javabase/read.text");

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        long readCount = 0;
        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = in.read(buffer)) > 0){
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("总字节数" + total + "总耗时" + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        in.close();
    }
}
