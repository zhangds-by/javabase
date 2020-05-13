package com.zhangds.io.newio.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 异步非阻塞
 * 对于读操作而言，当有流可读取时，操作系统会将可读的流传入read方法的缓冲区，并通知应用程序；
 * 对于写操作而言，当操作系统将write方法传递的流写入完毕时，操作系统主动通知应用程序。
 * 即可以理解为，read/write方法都是异步的，完成后会主动调用回调函数。
 *
 * 服务器实现模式为一个有效请求一个线程，客户端的I/O 请求都是由OS先完成了再通知服务器应用去启动线程进行处理。
 * @Author zhangds
 * @Date 2020/5/7 14:28
 * @Return
 */
public class AIOServer {

	// 线程池， 提高服务端效率。
	private ExecutorService service;
	// 线程组
	// private AsynchronousChannelGroup group;
	// 服务端通道， 针对服务器端定义的通道。
	private AsynchronousServerSocketChannel serverChannel;
	
	public AIOServer(int port){
		init(9999);
	}
	
	private void init(int port){
		try {
			System.out.println("server starting at port : " + port + " ...");
			// 定长线程池
			service = Executors.newFixedThreadPool(4);
			/* 使用线程组
			group = AsynchronousChannelGroup.withThreadPool(service);
			serverChannel = AsynchronousServerSocketChannel.open(group);
			*/
			// 开启服务端通道， 通过静态方法创建的。
			serverChannel = AsynchronousServerSocketChannel.open();
			// 绑定监听端口， 服务器启动成功，但是未监听请求。
			serverChannel.bind(new InetSocketAddress(port));
			System.out.println("server started.");
			// 开始监听 
			// accept(T attachment, CompletionHandler<AsynchronousSocketChannel, ? super T>)
			// AIO开发中，监听是一个类似递归的监听操作。每次监听到客户端请求后，都需要处理逻辑开启下一次的监听。
			// 下一次的监听，需要服务器的资源继续支持。
			serverChannel.accept(this, new AIOServerHandler());
			try {
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new AIOServer(9999);
	}

	public ExecutorService getService() {
		return service;
	}

	public void setService(ExecutorService service) {
		this.service = service;
	}

	public AsynchronousServerSocketChannel getServerChannel() {
		return serverChannel;
	}

	public void setServerChannel(AsynchronousServerSocketChannel serverChannel) {
		this.serverChannel = serverChannel;
	}
	
}
