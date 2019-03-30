package com.guang.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by guang on 2019/3/25.
 */
public class HelloClient {

    private String host;
    private int portNumber;
    private EventLoopGroup eventLoopGroup;


    public HelloClient(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
    }

    public void start() {
        eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new HelloClientInitializer());
        try {
            Channel channel = bootstrap.connect(host, portNumber).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = in.readLine();
                if (line.equals("break")) {
                    break;
                }
                channel.writeAndFlush(line + "\r\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() {
        eventLoopGroup.shutdownGracefully();
    }
}
