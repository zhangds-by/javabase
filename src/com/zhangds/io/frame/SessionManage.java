package com.zhangds.io.frame;

import org.apache.mina.core.session.IoSession;

/**
 * 会话管理类
 */
class SessionManage {
    private static SessionManage mSessionManage = null;
    private IoSession mSession;
    private boolean isCloseSession;

    //单例加锁机制
    public static SessionManage getInstance() {
        if (mSessionManage == null) {
            synchronized (SessionManage.class) {
                if (mSessionManage == null)
                    mSessionManage = new SessionManage();
            }
        }
        return mSessionManage;
    }

    public void setIoSession(IoSession ioSession) {
        this.mSession = ioSession;
    }

    public boolean isCloseSession() {
        return isCloseSession;
    }

    private void setCloseSession(boolean closeSession) {
        isCloseSession = closeSession;
    }

    /**
     * 将对象写到服务器
     */
    public void writeToServer(Object msg) {
        if (mSession != null) {
            mSession.write(msg.toString());
        }
    }

    /**
     * 关闭连接
     */
    public void closeSession() {
        if (mSession != null) {
            mSession.closeNow();
            mSession.closeOnFlush();
            mSession.getService().dispose();
        }
        mSession = null;
        setCloseSession(false);
    }
}
