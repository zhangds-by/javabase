package com.zhangds.io.frame;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import javax.naming.Context;

/**
 * 业务处理类
 */
public class ConnectHandler extends IoHandlerAdapter {
    private Context context;
    private ConnectManage connectionManage;
    private static final String BROADCAST_ACTION = "mina";
    private static final String MESSAGE = "Message";

    ConnectHandler(Context context, ConnectManage connectionManage) {
        this.context = context;
        this.connectionManage = connectionManage;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        System.out.println("---创建连接---");
    }

    /**
     * 将session保存到session manage中，从而可以发送消息到服务器端
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // 当与服务器连接成功时,将我们的session保存到我们的session manager类中,从而可以发送消息到服务器
        SessionManage.getInstance().setIoSession(session);
        System.out.println("---打开连接---");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("---关闭连接---");
        if (!SessionManage.getInstance().isCloseSession()) {// 关闭后自动重连
            if (connectionManage != null && connectionManage.isCloseSession())
                connectionManage.connect();
        } else {
            if (connectionManage != null) connectionManage.disConnect();
            connectionManage = null;
        }
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        System.out.println("---空闲---");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
//        if (context != null) {
//            //通过Android局部广播LocalBroadcastManager机制发送消息
//            Intent intent = new Intent(BROADCAST_ACTION);
//            intent.putExtra(MESSAGE, message.toString());
//            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
//        }
//        System.out.println("---接收消息--- " + message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        System.out.println("---发送消息--- " + message);
    }
}
