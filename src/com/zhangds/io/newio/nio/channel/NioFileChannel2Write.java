package com.zhangds.io.newio.nio.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileChannel2Write {

    public static void main(String[] args) throws Exception {

        String str = "hello，NioFileChannel";

        // 创建输出流，从流中获取对应的FileChannel，FileChannel的真实类型是FileChannelImpl
        FileOutputStream fileOutputStream = new FileOutputStream("/io.md");
        FileChannel channel = fileOutputStream.getChannel();

        // 创建缓冲区并向buffer写入数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        // 对buffer进行flip，这步很重要
        buffer.flip();

        // 将buffer写入channel
        channel.write(buffer);
        fileOutputStream.close();
    }
}
