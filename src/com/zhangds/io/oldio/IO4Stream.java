package com.zhangds.io.oldio;

import java.io.*;

/**
 * 输入输出流以程序为中心
 * Create by zhangds
 * 2020-05-07 09:55
 **/
public class IO4Stream {
    public static void main(String[] args) {

    }

    /**
     * 文件字节流
     * @Author zhangds
     * @Date 2020/5/7 10:13
     * @Return
     */
    public void FileByteStreamMethod() throws IOException {
        //创建源、选择流
        File file = new File("./IOBase.java");
        InputStream is = new FileInputStream(file);

        //逐个字符读取
        int temp;
        while ((temp = is.read()) != -1) {
            System.out.println((char) temp);
        }

        //分段读取
        int len = -1; //接收长度
        byte[] flush = new byte[1024];
        while ((len=is.read(flush))!=-1){
            String str = new String(flush, 0, len); //解码
        }

        OutputStream os = new FileOutputStream(file, true);
        String msg = "write in content";
        byte[] datas = msg.getBytes();
        os.write(datas, 0, datas.length);
        os.flush(); //flush才能写入成功

    }

    /**
     * 文件字符流
     * @Author zhangds
     * @Date 2020/5/7 10:20
     * @Return
     */
    public void FileStringStreamMethod() throws IOException {
        //创建源、选择流
        File file = new File("./IOBase.java");
        Reader reader = new FileReader(file);

        //分段读取
        int len = -1; //接收长度
        char[] flush = new char[1024];
        while ((len=reader.read(flush))!=-1){
            String str = new String(flush, 0, len); //解码
        }

        Writer writer = new FileWriter(file, true);
        String msg = "write in content";
        char[] datas = msg.toCharArray();
        writer.write(datas, 0, datas.length);
        writer.append("hello ").append("world");
        writer.flush(); //flush才能写入成功

    }

    /**
     * 字节数组流
     * @Author zhangds
     * @Date 2020/5/7 10:32
     * @Return
     */
    public void ByteArrayStream() throws IOException {
        byte[] src = "source string".getBytes();
        InputStream is = new ByteArrayInputStream(src);
        byte[] flush = new byte[10];
        int len = -1;
        while ((len=is.read(flush))!=-1){
            String str = new String(flush, 0, len);
        }

        OutputStream os = new ByteArrayOutputStream(); //内部维护源，不需要关闭
        String msg = "write in content";
        byte[] datas = msg.getBytes();
        os.write(datas, 0, datas.length);
        os.flush();
    }

    public byte[] fileToByteArray(String filePath) throws IOException {
        File file = new File(filePath);
        //图片到程序  FileInputStream
	    //程序到字节数组	ByteArrayOutputStream
        InputStream is = new FileInputStream(file);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] flush = new byte[1024];
        int len = -1;
        while ((len=is.read())!=-1){
            os.write(flush, 0, len);
        }
        os.flush();
        return os.toByteArray();
    }

    public void byteArrayToFile(byte[] src,String filePath) throws IOException {
        //字节数组到程序 ByteArrayInputStream
	    //程序到文件 FileOutputStream
        File file = new File(filePath);
        InputStream  is = is =new ByteArrayInputStream(src);
        OutputStream os = new FileOutputStream(file);
            byte[] flush = new byte[5]; //缓冲容器
            int len = -1; //接收长度
            while((len=is.read(flush))!=-1) {
                os.write(flush,0,len);			//写出到文件
            }
            os.flush();
    }

}
