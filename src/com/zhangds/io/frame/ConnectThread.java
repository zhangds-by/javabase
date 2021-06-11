//package com.zhangds.io.frame;
//
///**
// * 创建线程类来调用connection manage完成于服务器的连接
// */
//public class ConnectThread extends HandlerThread {
//
//    private ConnectManage mConnectManage;
//    private boolean isConnection;
//
//    ConnectThread(String name) {
//        super(name);
//        ConnectConfig connectConfig = new ConnectConfig.ConnectConfigBuilder()
//                .ip("192.168.1.55")
//                .port(55555)
//                .readBufferSize(10240)
//                .connectionTimeout(10000)
//                .heartInterval(20)
//                .heartTimeOut(5).build();
//
//        mConnectManage = new ConnectManage(connectConfig);
//    }
//
//    @Override
//    protected void onLooperPrepared() {
//        // 利用循环请求连接
//        while (!isConnection) {
//            if (mConnectManage == null) break;
//            //是否于服务器连接成功
//            boolean isConnection = mConnectManage.connect();
//            if (isConnection) {
//                // 当请求成功的时候,跳出循环
//                System.out.println("----------连接成功");
//                break;
//            }
//            System.out.println("---------连接失败");
//            try {
//                Thread.sleep(2500);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 销毁服务器的连接
//     */
//    public void disConnection() {
//        isConnection = true;
//        if (getLooper() != null) getLooper().quit();
//        if (mConnectManage != null) {
//            mConnectManage.disConnect();
//            mConnectManage = null;
//        }
//    }
//}
