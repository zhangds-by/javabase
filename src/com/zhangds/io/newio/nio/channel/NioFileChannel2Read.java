package com.zhangds.io.newio.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileChannel2Read {

    public static void main(String[] args) throws Exception {

        File file = new File("d:/temp/iotest.md");
        FileInputStream fileInputStream = new FileInputStream(file);

        // 创建输出流，从流中获取对应的FileChannel，FileChannel的真实类型是FileChannelImpl
        FileChannel channel = fileInputStream.getChannel();

        // 创建缓冲区并向buffer写入数据
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        // 从channel读数据到buffer
        channel.read(buffer);

        System.out.println(new String(buffer.array()));
        fileInputStream.close();
    }
}
