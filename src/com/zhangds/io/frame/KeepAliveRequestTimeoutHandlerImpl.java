package com.zhangds.io.frame;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

/**
 * 当心跳超时时的处理
 */
class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler {
    @Override
    public void keepAliveRequestTimedOut(KeepAliveFilter keepAliveFilter, IoSession ioSession) throws Exception {
        ioSession.closeNow();
    }
}
