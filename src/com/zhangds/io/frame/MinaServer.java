package com.zhangds.io.frame;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * IOServer接口相关接口 :监听和session管理
 * @author: zhangds
 * @date: 2021/3/5 10:46
 */
public class MinaServer {

    public static final Logger log = LoggerFactory.getLogger(MinaServer.class);

    static final int PORT = 8888;

    public static void main(String[] args) {
        //IoAcceptor 继承于IoService接口，实现TCP或UDP协议的监听器
        IoAcceptor acceptor = new NioSocketAcceptor();

        //设置过滤器,主要设置协议的编码解码
        //ProtocolCodecFilter 数据转化过滤器
        // CompressionFilter 数据压缩过滤器
        // SSLFilter 数据加密与解密传输过滤器
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(StandardCharsets.UTF_8, LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())
        ));

        //添加日志过滤器，LoggingFilter记录mina的所有日志
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());

        //设置读缓存大小
        acceptor.getSessionConfig().setReadBufferSize(2048);

        //设置读写空闲时间相同，都为10s
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        //注册处理器
        acceptor.setHandler(new MyHandler());

        // 绑定端口开始进行监听
        try {
            acceptor.bind(new InetSocketAddress(PORT));
        } catch (IOException e) {
            log.info("监听端口失败");
        }
        System.out.println("端口 " + PORT + " 启动成功");
    }
}
