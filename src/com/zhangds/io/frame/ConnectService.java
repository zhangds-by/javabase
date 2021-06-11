//package com.zhangds.io.frame;
//
//
///**
// * 服务管理类
// */
//public class ConnectService extends Service {
//
//    //负责调用connect manage完成于服务器的连接
//    private ConnectThread connectThread;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        //初始化线程并启动
//        connectThread = new ConnectThread("mina");
//        connectThread.start();
//
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //断开连接并释放线程
//        connectThread.disConnection();
//        connectThread = null;
//    }
//}
