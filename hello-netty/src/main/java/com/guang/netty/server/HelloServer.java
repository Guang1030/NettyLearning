package com.guang.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by guang on 2019/3/25.
 */
public class HelloServer {
    private int portNumber;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;


    public HelloServer(int portNumber) {
        this.portNumber = portNumber;
    }

    public void start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new HelloServerInitializer());

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(portNumber).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
