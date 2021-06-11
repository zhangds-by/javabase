package com.zhangds.io.frame;


import javax.naming.Context;

public class ConnectConfig {
    private Context context;       //内容
    private String ip;             //IP地址
    private int port;              //端口
    private int readBufferSize;    // 缓存大小
    private int connectionTimeout; // 连接超时时间
    private int heartInterval;     // 心跳间隔时间
    private int heartTimeOut;      // 心跳请求超时时间

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public void setReadBufferSize(int readBufferSize) {
        this.readBufferSize = readBufferSize;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getHeartInterval() {
        return heartInterval;
    }

    public void setHeartInterval(int heartInterval) {
        this.heartInterval = heartInterval;
    }

    public int getHeartTimeOut() {
        return heartTimeOut;
    }

    public void setHeartTimeOut(int heartTimeOut) {
        this.heartTimeOut = heartTimeOut;
    }
}

