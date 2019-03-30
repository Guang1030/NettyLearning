package com.guang.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * Created by guang on 2019/3/30.
 */
public class PacketServerHandler extends SimpleChannelInboundHandler<String> {

    private int counter = 0;
    private static final Logger logger = LoggerFactory.getLogger(PacketServerHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        logger.info("Client :" + channel.remoteAddress() + " is connected to server.");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        counter++;
        logger.info("Server received request :" + msg + "; counter is " + counter);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(msg) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }
}
