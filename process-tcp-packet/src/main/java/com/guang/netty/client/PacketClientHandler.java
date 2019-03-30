package com.guang.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by guang on 2019/3/30.
 */
public class PacketClientHandler extends SimpleChannelInboundHandler<String> {
    private int counter = 0;
    private static final Logger logger = LoggerFactory.getLogger(PacketClientHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf;
        String msgStr = "QUERY TIME ORDER" + System.getProperty("line.separator");
        for (int i = 0; i < 100; i++) {
            buf = Unpooled.buffer(msgStr.getBytes().length);
            buf.writeBytes(msgStr.getBytes());
            ctx.writeAndFlush(buf);
        }
        super.channelActive(ctx);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        counter++;
        logger.info("Server response is " + msg + "; counter is " + counter);
    }
}
