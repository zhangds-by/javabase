package com.zhangds.io.oldio;

import java.io.*;

/**
 * 装饰缓冲流
 * Create by zhangds
 * 2020-05-07 09:55
 **/
public class IO4DecoStream {
    public static void main(String[] args) {

    }

    /**
     * 文件字节缓冲流
     * @Author zhangds
     * @Date 2020/5/7 10:13
     * @Return
     */
    public void FileByteStreamMethod() throws IOException {
        //创建源、选择流
        File file = new File("./IOBase.java");
        InputStream is = new BufferedInputStream(new FileInputStream(file));

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

        OutputStream os = new BufferedOutputStream(new FileOutputStream(file, true));
        String msg = "write in content";
        byte[] datas = msg.getBytes();
        os.write(datas, 0, datas.length);
        os.flush(); //flush才能写入成功

    }

    /**
     * 文件字符缓冲流
     * @Author zhangds
     * @Date 2020/5/7 10:20
     * @Return
     */
    public void FileStringStreamMethod() throws IOException {
        //创建源、选择流
        File file = new File("./IOBase.java");
        Reader reader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = bufferedReader.readLine())!=null){
            String outStr = line;
        }


        Writer writer = new BufferedWriter(new FileWriter(file, true));
        String msg = "write in content";
        char[] datas = msg.toCharArray();
        writer.write(datas, 0, datas.length);
        writer.append("hello ").append("world");
        writer.flush(); //flush才能写入成功

    }

    public void receviceConsole(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(System.out));) {
            String msg = "";
            while (msg != "exit"){
                msg = reader.readLine();
                writer.write(msg);
                writer.newLine();
                writer.flush();
            }
        }catch (IOException e){
            e.getStackTrace();
        }
    }


}
