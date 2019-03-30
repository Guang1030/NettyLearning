package com.guang.netty;

import com.guang.netty.server.PacketServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by guang on 2019/3/30.
 */
public class TestPacketServer {
    private PacketServer packetServer;

    @Before
    public void init() {
        packetServer = new PacketServer(7878);
    }

    @Test
    public void run() {
        packetServer.start();
    }

    @After
    public void cleanUp() {
        packetServer.cleanUp();
    }
}
