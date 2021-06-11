package com.zhangds.io.frame;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 我这里没做啥操作
 */
class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        return false;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object o) {
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        return null;
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        return null;
    }
}
