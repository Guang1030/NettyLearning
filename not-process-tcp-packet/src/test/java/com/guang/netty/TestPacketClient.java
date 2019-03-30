package com.guang.netty;

import com.guang.netty.client.PacketClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by guang on 2019/3/30.
 */
public class TestPacketClient {
    private PacketClient packetClient;

    @Before
    public void init() {
        packetClient = new PacketClient("127.0.0.1", 7878);
    }

    @Test
    public void run() {
        packetClient.start();
    }

    @After
    public void cleanUp() {
        packetClient.cleanUp();
    }
}
