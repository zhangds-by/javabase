package com.zhangds.io.frame;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import javax.naming.Context;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;

public class ConnectManage {

    private ConnectConfig mConfig;
    private WeakReference<Context> mContext;
    private NioSocketConnector mConnector;
    private IoSession mSession;
    private InetSocketAddress mAddress;
    private ConnectFuture mConnectFuture;

    public ConnectManage(ConnectConfig config) {
        this.mConfig = config;
        mContext = new WeakReference<>(mConfig.getContext());
        init();
    }
    /**
     * 初始化连接配置
     */
    private void init() {
        // 创建连接对象
        mConnector = new NioSocketConnector();
        // 设置连接地址
        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        mConnector.setDefaultRemoteAddress(mAddress);
        // 设置缓冲大小
        mConnector.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
        // 设置过滤
        mConnector.getFilterChain().addLast("logger", new LoggingFilter());
        mConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        // 设置心跳包，断线重连
        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
        KeepAliveRequestTimeoutHandler heartBeatHandler = new KeepAliveRequestTimeoutHandlerImpl();
        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory, IdleStatus.BOTH_IDLE, heartBeatHandler);
        // 是否回发
        heartBeat.setForwardEvent(true);
        // 设置当连接的读取通道空闲的时候，心跳包请求时间间隔
        heartBeat.setRequestInterval(mConfig.getHeartInterval());
        // 设置心跳包请求后，若超过该时间后则调用KeepAliveRequestTimeoutHandler.CLOSE_NOW
        heartBeat.setRequestTimeout(mConfig.getHeartTimeOut());
        mConnector.getFilterChain().addLast("heartbeat", heartBeat);
        mConnector.getSessionConfig().setUseReadOperation(true);
        // 设置连接监听
        mConnector.setHandler(new ConnectHandler(mContext.get(), this));
        // 超时设置
        mConnector.setConnectTimeoutMillis(mConfig.getConnectionTimeout());
    }
    /**
     * 是否关闭Session
     *
     * @return
     */
    public boolean isCloseSession() {
        if (mSession != null) return mSession.isClosing();
        return false;
    }
    /**
     * 与服务器建立连接
     *
     * @return
     */
    public boolean connect() {
        try {
            mConnectFuture = mConnector.connect(mAddress);
            //一直等到连接为止
            mConnectFuture.awaitUninterruptibly();
            //获取session对象
            mSession = mConnectFuture.getSession();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return mSession != null;
    }
    /**
     * 断开与服务器的连接
     */
    public void disConnect() {
        if (mConnector != null) mConnector.dispose();
        if (mConnectFuture != null) mConnectFuture.cancel();
        mConnector = null;
        mSession = null;
        mAddress = null;
        mContext = null;
        mConnectFuture = null;
    }

}
