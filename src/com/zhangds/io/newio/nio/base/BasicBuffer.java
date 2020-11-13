package com.zhangds.io.newio.nio.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class BasicBuffer {

    public static void main(String[] args) throws Exception {

//        testIntBuffer();

//        testMappedByteBuffer();

//        testOutFileChannel();

//        testInFileChannel();

//        testOtherChannel();

        testReadOnlyBuffer();

    }

    public static void testIntBuffer(){
        IntBuffer buffer = IntBuffer.allocate(5);
        for (int i=0; i<buffer.capacity(); i++){
            buffer.put(i*2);
        }

        buffer.flip();

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }

    public static void testMappedByteBuffer() throws Exception{

        RandomAccessFile accessFile = new RandomAccessFile("read.text", "rw");
        FileChannel channel = accessFile.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        System.out.println((char) buffer.get(0));
        accessFile.close();
    }

    //写入
    public static void testOutFileChannel() throws Exception{

        //读取文件不能被占用
        FileOutputStream out = new FileOutputStream("D:/IdeaProjects/javabase/write.text");
        FileChannel channel = out.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String content = "hello, put content";
        byteBuffer.put(content.getBytes());
        byteBuffer.flip();

        channel.write(byteBuffer);
        out.close();
    }

    //读取
    public static void testInFileChannel() throws Exception{

        File file = new File("D:/IdeaProjects/javabase/read.text");
        FileInputStream in = new FileInputStream(file);
        FileChannel channel = in.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        in.close();
    }

    public static void testOtherChannel() throws Exception{

        FileInputStream in = new FileInputStream("D:/IdeaProjects/javabase/read.text");
        FileOutputStream out = new FileOutputStream("D:/IdeaProjects/javabase/copy.text");
        FileChannel sourceChannel = in.getChannel();
        FileChannel destChannel = out.getChannel();

        //使用通道拷贝
        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        in.close();
        out.close();
        sourceChannel.close();
        destChannel.close();
    }

    public static void testReadOnlyBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(64);
        for (int i=0; i<64; i++){
            buffer.put((byte) i);
        }
        buffer.flip();

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

//        readOnlyBuffer.put((byte) 100); // 仅读，ReadOnlyBufferException
    }
}
