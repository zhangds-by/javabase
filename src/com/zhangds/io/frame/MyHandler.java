package com.zhangds.io.frame;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * session事件的监听，消息收发监听，异常监听等
 * IoSession类：通信管理类
 * @author: zhangds
 * @date: 2021/3/5 10:58
 */
public class MyHandler extends IoHandlerAdapter {
    @Override
    public void sessionCreated(IoSession session) {
        System.out.println("session被创建,sessionId: " + session.getId());
    }

    @Override
    public void sessionOpened(IoSession session) {
        String address = session.getRemoteAddress().toString();
        System.out.println("session被打开,address is : " + address);
    }

    @Override
    public void sessionClosed(IoSession session) {
        System.out.println("session客户端关闭,");
    }

    /**
     * 消息的接收
     *
     * @param session
     * @param message
     */
    @Override
    public void messageReceived(IoSession session, Object message) {
        //假如是string类型消息
        String strMsg = message.toString();
        //向客户端返回消息
        session.write("服务端已收到消息： " + new Date().toString());
        System.out.println("接收到消息：" + strMsg);
    }

    /**
     * 消息的发送
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageSent(IoSession session, Object message) {
    }
}
